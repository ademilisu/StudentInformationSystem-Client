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
import schoolclientdemo.model.Student;

@Service
public class MulStudentAndLectureServiceImpl implements MulStudentAndLectureService {
	
	@Autowired
	private RestUrl url;
	
	@Autowired
	private RestTemplate restTemplate;

	public MulStudentAndLectureServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Lecture> listMyLectures(String userId, String studentId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<List<Lecture>> response = restTemplate.exchange(url.getUrl() + "students/"
			+ studentId + "/lectures", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Lecture>>() {});
		return response.getBody();
	}
	
	@Override
	public List<Student> listStudentsOfLecture(String userId, String lectureId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<List<Student>> response = restTemplate.exchange(url.getUrl() + 
				"lectures/" + lectureId + "/students",
				HttpMethod.GET, entity, new ParameterizedTypeReference<List<Student>>() {});
		return response.getBody();
	}
	
	@Override
	public void addToMyLectures(String userId, String typeId, Lecture lecture) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<Lecture> entity = new HttpEntity<>(lecture, header);
		restTemplate.exchange(url.getUrl() + "students/"
			+ typeId + "/lectures", HttpMethod.POST, entity, String.class);
		
	}

	@Override
	public void removeFromMyLectures(String userId, String typeId, int lectureId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		restTemplate.exchange(url.getUrl() + "students/"
			+ typeId + "/lectures/" + lectureId, HttpMethod.DELETE, entity, String.class);
	}

	
	
	
}
