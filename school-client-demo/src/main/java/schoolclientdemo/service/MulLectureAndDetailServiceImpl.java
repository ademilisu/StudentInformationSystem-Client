package schoolclientdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import schoolclientdemo.config.RestUrl;
import schoolclientdemo.model.LectureDetail;

@Service
public class MulLectureAndDetailServiceImpl implements MulLectureAndDetailService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RestUrl url;	
	
	public MulLectureAndDetailServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public LectureDetail findLectureDetail(String userId, int lectureId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new", header);
		ResponseEntity<LectureDetail> detail = restTemplate.exchange(url.getUrl() + "lectures/" + lectureId + 
				"/details", HttpMethod.GET, entity, LectureDetail.class);
		System.out.println(detail.getBody());
		return detail.getBody();
	}
/*
	@Override
	public void addLectureDetail(String userId, LectureDetail lectureDetail) {
		int id = lectureDetail.getLecture().getId();
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<LectureDetail> entity = new HttpEntity<>(lectureDetail , header);
		ResponseEntity<LectureDetail> detail = restTemplate.exchange(url.getUrl() + "lectures/ " + id + 
				"/details", HttpMethod.POST, entity, LectureDetail.class);
		
	}
*/
	@Override
	public LectureDetail addLectureDetail(String userId, String lectureId, LectureDetail lectureDetail) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<LectureDetail> entity = new HttpEntity<>(lectureDetail , header);
		ResponseEntity<LectureDetail> detail = restTemplate.exchange(url.getUrl() + "lectures/ " + lectureId + 
				"/details", HttpMethod.POST, entity, LectureDetail.class);
		return detail.getBody();
	}

	@Override
	public LectureDetail updateLectureDetail(String userId, String lectureId, LectureDetail lectureDetail) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<LectureDetail> entity = new HttpEntity<>(lectureDetail , header);
		ResponseEntity<LectureDetail> detail = restTemplate.exchange(url.getUrl() + "lectures/ " + lectureId + 
				"/details", HttpMethod.PUT, entity, LectureDetail.class);
		return detail.getBody();
	}

}
