package schoolclientdemo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import schoolclientdemo.config.RestUrl;
import schoolclientdemo.model.AppUser;
import schoolclientdemo.model.UserData;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RestUrl url;


	public AuthenticationServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public UserData login(AppUser user) {
		HttpEntity<AppUser> appUser = new HttpEntity<>(user);
		ResponseEntity<UserData> response = restTemplate.exchange(url.getUrl() + "login", HttpMethod.POST, appUser, UserData.class);
		UserData userData = response.getBody();
		return userData;
	}
	
	@Override
	public String addNewStudent(String userId, AppUser appUser) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<AppUser> entity = new HttpEntity<>(appUser ,header);
		ResponseEntity<UserData> response = restTemplate.exchange(url.getUrl() + 
				"/signup/students", HttpMethod.POST, entity, UserData.class);
		return response.getBody().getUsername();
	}

	@Override
	public String addNewInstructor(String userId, AppUser appUser) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<AppUser> entity = new HttpEntity<>(appUser ,header);
		ResponseEntity<UserData> response = restTemplate.exchange(url.getUrl() + 
				"/signup/instructors", HttpMethod.POST, entity, UserData.class);
		return response.getBody().getUsername();
	}

	@Override
	public String addNewAdmin(String userId, AppUser appUser) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<AppUser> entity = new HttpEntity<>(appUser ,header);
		ResponseEntity<UserData> response = restTemplate.exchange(url.getUrl() + 
				"/signup/admins", HttpMethod.POST, entity, UserData.class);
		return response.getBody().getUsername();
	}

	@Override
	public void logout(String userId) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<String> entity = new HttpEntity<>("new" ,header);
		restTemplate.exchange(url.getUrl() + 
				"/account/logout", HttpMethod.GET, entity, String.class);
		
	}

	@Override
	public void changePassword(String userId, AppUser appUser) {
		HttpHeaders header = new HttpHeaders();
		header.add("userId", userId);
		HttpEntity<AppUser> entity = new HttpEntity<>(appUser ,header);
		restTemplate.exchange(url.getUrl() + 
				"/account/password", HttpMethod.POST, entity, AppUser.class);
		
	}

	

	
}
