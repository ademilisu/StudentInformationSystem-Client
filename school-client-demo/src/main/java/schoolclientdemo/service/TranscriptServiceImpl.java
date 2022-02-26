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
import schoolclientdemo.model.Transcript;

@Service
public class TranscriptServiceImpl implements TranscriptService {
	
	@Autowired
	private RestUrl url;
	
	@Autowired
	private RestTemplate restTemplate;

	public TranscriptServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Transcript> findMyAllTranscripts(String userId, String studentId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<List<Transcript>> response = restTemplate.exchange(url.getUrl() +
				"students/" + studentId + "/transcripts",
				 HttpMethod.GET, entity, new ParameterizedTypeReference<List<Transcript>>() {});
		return response.getBody();
	}

	@Override
	public List<Lecture> findMylecturesInTranscript(String userId, String studentId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<List<Lecture>> response = restTemplate.exchange(url.getUrl() +
				"transcripts/" + studentId + "/lectures",
				 HttpMethod.GET, entity, new ParameterizedTypeReference<List<Lecture>>() {});
		return response.getBody();
	}
	
	
}
