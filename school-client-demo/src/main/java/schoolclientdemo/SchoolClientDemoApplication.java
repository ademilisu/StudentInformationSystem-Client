package schoolclientdemo;


import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import schoolclientdemo.config.HttpInterceptor;


@SpringBootApplication
public class SchoolClientDemoApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(SchoolClientDemoApplication.class, args);
	}
	
	/*Rest Template is used to consume RESTful Web Services.
	 * RestTemplate allows us to add interceptors that implement ClientHttpRequestInterceptor. 
	 * These interceptors intercept the given request and
	 * allow us to manipulate response and request body or header.
	 */
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
	    restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new 
	    		SimpleClientHttpRequestFactory()));
	    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
	    interceptors.add(new HttpInterceptor());
	    restTemplate.setInterceptors(interceptors);
	    return restTemplate;    
	}

	
}
