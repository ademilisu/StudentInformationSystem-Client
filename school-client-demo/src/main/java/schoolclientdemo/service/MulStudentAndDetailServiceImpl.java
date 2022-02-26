package schoolclientdemo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import schoolclientdemo.config.RestUrl;
import schoolclientdemo.model.StudentDetail;

@Service
public class MulStudentAndDetailServiceImpl implements MulStudentAndDetailService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RestUrl url;
	
	public MulStudentAndDetailServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public StudentDetail findStudentDetail(String userId, String studentId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<StudentDetail> response = restTemplate.exchange(url.getUrl() + 
				"students/" + studentId + "/details" , HttpMethod.GET, entity, StudentDetail.class);
		return response.getBody();
	}

	@Override
	public StudentDetail saveStudentDetail(String userId, String studentId, StudentDetail detail) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<StudentDetail> entity = new HttpEntity<>(detail, header);
		ResponseEntity<StudentDetail> response = restTemplate.exchange(url.getUrl() + 
				"students/" + studentId + "/details" , HttpMethod.POST, entity, StudentDetail.class);
		return response.getBody();
	}

	@Override
	public StudentDetail updateStudentDetail(String userId, String studentId, StudentDetail detail) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<StudentDetail> entity = new HttpEntity<>(detail, header);
		ResponseEntity<StudentDetail> response = restTemplate.exchange(url.getUrl() + 
				"students/" + studentId + "/details" , HttpMethod.PUT, entity, StudentDetail.class);
		return response.getBody();
	}

}
