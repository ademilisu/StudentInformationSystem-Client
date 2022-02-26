package schoolclientdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import schoolclientdemo.config.RestUrl;
import schoolclientdemo.model.Instructor;

@Service
public class InstructorServiceImpl implements InstructorService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RestUrl url;
	
	public InstructorServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public Instructor updateInstructor(String userId, Instructor instructor) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<Instructor> entity = new HttpEntity<>(instructor, header);
		ResponseEntity<Instructor> response = restTemplate.exchange(url.getUrl() + 
				"instructors" , HttpMethod.PUT, entity, Instructor.class);		
		return response.getBody();
	}

	@Override
	public Instructor findInstructor(String userId, String instructorId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<Instructor> response = restTemplate.exchange(url.getUrl() + 
				"instructors/" + instructorId , HttpMethod.GET, entity, Instructor.class);		
		return response.getBody();
	}

	@Override
	public List<Instructor> findAllInstructors(String userId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<List<Instructor>> response = restTemplate.exchange(url.getUrl() + 
				"instructors", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Instructor>>() {});		
		return response.getBody();
	}
	
	@Override
	public void deleteInstructor(String userId, String instructorId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		restTemplate.exchange(url.getUrl() + "remove/instructors/" + instructorId, 
				HttpMethod.DELETE, entity, String.class);
	}

	@Override
	public List<Instructor> searchInstructor(String userId, String name) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<List<Instructor>> response = restTemplate.exchange(url.getUrl() + 
				"search/instructors/" + name, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Instructor>>() {});		
		return response.getBody();
	}

}
