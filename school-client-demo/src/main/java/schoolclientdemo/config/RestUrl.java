package schoolclientdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestUrl {
	
	@Value("${school.rest.url}")
	private String url;
	
	public RestUrl() {}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
