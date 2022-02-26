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
import schoolclientdemo.model.Lecture;

@Service
public class LectureServiceImpl implements LectureService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RestUrl url;


	public LectureServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Lecture> listOfLectures(String userId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<List<Lecture>> response = restTemplate.exchange(url.getUrl() +"lectures", 
				HttpMethod.GET, entity, new ParameterizedTypeReference<List<Lecture>>() {});
		List<Lecture> lectures = response.getBody();
		return lectures;
	}


	@Override
	public Lecture findLecture(String userId, int lectureId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<Lecture> response = restTemplate.exchange(url.getUrl() +"lectures/" + lectureId, 
				HttpMethod.GET, entity, Lecture.class);
		return response.getBody();
	}

	@Override
	public Lecture addLecture(String userId, Lecture lecture) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<Lecture> entity = new HttpEntity<>(lecture, header);
		ResponseEntity<Lecture> response = restTemplate.exchange(url.getUrl() +"lectures", 
				HttpMethod.POST, entity, Lecture.class);
		return response.getBody();
	}

	@Override
	public Lecture updateLecture(String userId, Lecture lecture) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<Lecture> entity = new HttpEntity<>(lecture, header);
		ResponseEntity<Lecture> response = restTemplate.exchange(url.getUrl() +"lectures", 
				HttpMethod.PUT, entity, Lecture.class);
		return response.getBody();
	}
	
	@Override
	public void deleteLecture(String userId, int lectureId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		restTemplate.exchange(url.getUrl() +"lectures/" + lectureId , 
				HttpMethod.DELETE, entity, String.class);
	}

	@Override
	public void setActive(String userId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		restTemplate.exchange(url.getUrl() +"lectures/active", 
				HttpMethod.GET, entity, String.class);
		
	}

	@Override
	public void setPassive(String userId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		restTemplate.exchange(url.getUrl() +"lectures/passive", 
				HttpMethod.GET, entity, String.class);
	}

	@Override
	public List<Lecture> searchLectures(String userId, String lectureName) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<List<Lecture>> response = restTemplate.exchange(url.getUrl() +"search/lectures/"
				+ lectureName, 
				HttpMethod.GET, entity, new ParameterizedTypeReference<List<Lecture>>() {});
		return response.getBody();
	}


}
