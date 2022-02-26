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
import schoolclientdemo.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RestUrl url;
	
	public StudentServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public List<Student> listOfAllStudents(String userId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<List<Student>> response = restTemplate.exchange(url.getUrl() + "students",
				 HttpMethod.GET, entity, new ParameterizedTypeReference<List<Student>>() {});
		return response.getBody();
	}
	
	@Override
	public List<Student> searchStudent(String userId, String email) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<List<Student>> response = restTemplate.exchange(url.getUrl() + "search/students/" + email,
				 HttpMethod.GET, entity, new ParameterizedTypeReference<List<Student>>() {});
		return response.getBody();
	}

	
	@Override
	public Student updateStudent(String userId, Student student) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<Student> entity = new HttpEntity<>(student, header);
		ResponseEntity<Student> response = restTemplate.exchange(url.getUrl() + 
				"students" , HttpMethod.PUT, entity, Student.class);
		return response.getBody();
	}

	@Override
	public Student findStudent(String userId, String studentId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<Student> response = restTemplate.exchange(url.getUrl() + 
				"students/" + studentId , HttpMethod.GET, entity, Student.class);
		return response.getBody();
	}

	@Override
	public void deleteStudent(String userId, String studentId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		restTemplate.exchange(url.getUrl() + "remove/students/" + studentId, 
				HttpMethod.DELETE, entity, String.class);
	}

	
	

}
