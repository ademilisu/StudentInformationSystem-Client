package schoolclientdemo.config;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;



@Component
public class HttpInterceptor implements ClientHttpRequestInterceptor {
	
	/* This interceptor class implement ClientHttpRequestInterceptor interface.
	 * This interface has a method called intercept.
	 * Main purpose to use this intercept is that interceptor allows us to manipulate
	 * request body and header or halt to response.
	 * Refresh token is stored in the cookies Map Collection.
	 * Access token is stored in the tokens Map Collection.
	 * And their key is user id that sent with the header "userId"
	 * in the request sent by the RestTemplate.
	 */
	private Map<String, String> cookies = new HashMap<>();
	private Map<String, String> tokens = new HashMap<>();
	private byte[] oldBody;
	private HttpRequest oldRequest;
	

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		String url = request.getURI().toString();
		String userId;
		String cookie;
		String token;
		ClientHttpResponse response = null;
		
		/*When the user login, the access and refresh tokens are saved in the collections.
		 * 
		 */
		if(url.equals("http://localhost:8080/login")) {
				response = execution.execute(request, body);
				if(response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
					throw new AuthenticationFailException("Authentication Failed");
				}
				else {
					cookie = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
					token = response.getHeaders().getFirst("accessToken");
					int indis = cookie.indexOf("=");
					userId = cookie.substring(0, indis);
					cookie = cookie.substring(indis+1);
					cookies.put(userId, cookie);
					tokens.put(userId, token);
				}
				
		}
		else if(url.equals("http://localhost:8080/account/password")) {		
			userId = request.getHeaders().getFirst("userId");
			cookie = cookies.get(userId);
			request.getHeaders().add(HttpHeaders.SET_COOKIE, cookie);
			response = execution.execute(request, body);
			token = response.getHeaders().getFirst("accessToken");
			tokens.replace(userId, token);
			cookie = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
			int indis = cookie.indexOf("=");
			userId = cookie.substring(0, indis);
			cookie = cookie.substring(indis+1);
			cookies.replace(userId, cookie);
			
		}
		
		/*when user logout cookie and token informations are deleted.*/
		else if(url.equals("http://localhost:8080/account/logout")) {
			userId = request.getHeaders().getFirst("userId");
			cookies.remove(userId);
			tokens.remove(userId);
			response = execution.execute(request, body);
		}
		else {
			/*Every time a request is sent from RestTemplate, 
			 * the user's access token is found and added to the header.
			 */
			userId = request.getHeaders().getFirst("userId");
			token = tokens.get(userId);
			request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
			response = execution.execute(request, body);
			
			/*If the access token expired, we will get the 401 status code. 
			 * When we receive this code, 
			 * we need to store the previous request as oldRequest, oldBody and
			 * renew the access token in the background.
			 * To do this, we make a request by finding the refresh token of the user id 
			 * from the collection and adding this refresh token to the header of the request
			 * to /token/refresh endpoint.
			 * Then replace the new access token we received with the old one and
			 * make the old request using the new token.
			 */
			if(response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				this.oldRequest = request;
				this.oldBody = body;
				ClientHttpRequestFactory req = new BufferingClientHttpRequestFactory
						(new SimpleClientHttpRequestFactory());
				URI newUrl = URI.create("http://localhost:8080/token/refresh");
				ClientHttpRequest newRequest = req.createRequest(newUrl, HttpMethod.GET);
				newRequest.getHeaders().add(HttpHeaders.SET_COOKIE, cookies.get(userId));
				HttpHeaders header = new HttpHeaders();
				HttpEntity<String> entity = new HttpEntity<>("new",header);
				response = execution.execute(newRequest, entity.getBody().getBytes());
				token = response.getHeaders().getFirst("accessToken");
				tokens.replace(userId, token);
				oldRequest.getHeaders().clear();
				oldRequest.getHeaders().setContentType(MediaType.APPLICATION_JSON);
				oldRequest.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + tokens.get(userId));
				response = execution.execute(oldRequest, oldBody);
			}
		
		}
		return response;	
	}

}
