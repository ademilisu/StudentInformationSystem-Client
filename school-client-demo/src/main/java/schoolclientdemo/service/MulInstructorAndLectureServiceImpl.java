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
import schoolclientdemo.model.Lecture;

@Service
public class MulInstructorAndLectureServiceImpl implements MulInstructorAndLectureService {
	
	@Autowired
	private RestUrl url;
	
	@Autowired
	private RestTemplate restTemplate;

	public MulInstructorAndLectureServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Instructor> allInstructorsOfLecture(String userId, int lectureId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<List<Instructor>> response = restTemplate.exchange(url.getUrl() +
				"lectures/" + lectureId + "/instructors", 
				HttpMethod.GET, entity, new ParameterizedTypeReference<List<Instructor>>() {});
		return response.getBody();
	}
	
	@Override
	public List<Lecture> listLecturesOfInstructor(String userId, String instructorId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<List<Lecture>> response = restTemplate.exchange(url.getUrl() +
				"instructors/" + instructorId + "/lectures", 
				HttpMethod.GET, entity, new ParameterizedTypeReference<List<Lecture>>() {});
		return response.getBody();
	}
	
	@Override
	public void addInstructorToLecture(String userId, int lectureId, Instructor instructor) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<Instructor> entity = new HttpEntity<>(instructor, header);
		restTemplate.exchange(url.getUrl() +"lectures/" + lectureId + "/instructors", 
				HttpMethod.POST, entity, Instructor.class);
	}

	@Override
	public void removeInstructorFromLecture(String userId, int lectureId, Instructor instructor) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<Instructor> entity = new HttpEntity<>(instructor, header);
		restTemplate.exchange(url.getUrl() +"lectures/" + lectureId + "/instructors", 
				HttpMethod.DELETE, entity, Instructor.class);
	}

	

	
}
