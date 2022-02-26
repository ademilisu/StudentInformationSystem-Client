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
import schoolclientdemo.model.Result;

@Service
public class ResultServiceImpl implements ResultService {
	
	@Autowired
	private RestUrl url;
	
	@Autowired
	private RestTemplate restTemplate;

	public ResultServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Result> findLectureResults(String userId, String lectureId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<List<Result>> response = restTemplate.exchange(url.getUrl() +
				"lectures/" + lectureId + "/results", 
				HttpMethod.GET, entity, new ParameterizedTypeReference<List<Result>>() {});
		return response.getBody();
	}

	@Override
	public List<Result> updateAllResults(String userId, List<Result> results) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<List<Result>> entity = new HttpEntity<>(results, header);
		ResponseEntity<List<Result>> response = restTemplate.exchange(url.getUrl() +
				"results/all", 
				HttpMethod.PUT, entity, new ParameterizedTypeReference<List<Result>>() {});
		return response.getBody();
	}

	@Override
	public void deleteResult(String userId, String resultId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		restTemplate.exchange(url.getUrl() + "results/" + resultId, 
				HttpMethod.DELETE, entity, String.class);
		
	}

	@Override
	public void deleteAllResult(String userId, String lectureId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		restTemplate.exchange(url.getUrl() + "lectures/" + lectureId + "/results", 
				HttpMethod.DELETE, entity, String.class);
		
	}

	
}
