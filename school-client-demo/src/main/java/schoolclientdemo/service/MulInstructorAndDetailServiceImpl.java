package schoolclientdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import schoolclientdemo.config.RestUrl;
import schoolclientdemo.model.InstructorDetail;

@Service
public class MulInstructorAndDetailServiceImpl implements MulInstructorAndDetailService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RestUrl url;
	
	public MulInstructorAndDetailServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public InstructorDetail findInstructorDetail(String userId, String instructorId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<InstructorDetail> response = restTemplate.exchange(url.getUrl() + 
				"instructors/" + instructorId + "/details" , HttpMethod.GET, entity, InstructorDetail.class);
		return response.getBody();
	}

	@Override
	public InstructorDetail saveInstructorDetail(String userId, String instructorId, InstructorDetail detail) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<InstructorDetail> entity = new HttpEntity<>(detail, header);
		ResponseEntity<InstructorDetail> response = restTemplate.exchange(url.getUrl() + 
				"instructors/" + instructorId + "/details" , HttpMethod.POST, entity, InstructorDetail.class);
		return response.getBody();
	}

	@Override
	public InstructorDetail updateInstructorDetail(String userId, String instructorId, InstructorDetail detail) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<InstructorDetail> entity = new HttpEntity<>(detail, header);
		ResponseEntity<InstructorDetail> response = restTemplate.exchange(url.getUrl() + 
				"instructors/" + instructorId + "/details" , HttpMethod.PUT, entity, InstructorDetail.class);
		return response.getBody();
	}

}
