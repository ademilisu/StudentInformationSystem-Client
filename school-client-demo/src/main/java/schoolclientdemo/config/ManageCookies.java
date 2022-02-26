package schoolclientdemo.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class ManageCookies{
	
	/*This is a helper class to receive the cookie from the request on the client.*/
	
	//User Id is AppUser id
	public String getUserId(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		String userId = null;
		if(cookies != null) {
			for(Cookie tempCookie : cookies) {
				if(tempCookie.getName().equals("userId")) {
					userId = tempCookie.getValue();					
				}
			}
		}		
		return userId;
		
	}
	
	/*TypeId is studentId or instructorId*/
	public String getTypeId(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		String typeId = null;
		if(cookies != null) {
			for(Cookie tempCookie : cookies) {
				if(tempCookie.getName().equals("typeId")) {
					typeId = tempCookie.getValue();					
				}
			}
		}		
		return typeId;
		
	}
	
	/*Type is User Role*/
	public String getType(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String type = null;
		if(cookies != null) {
			for(Cookie tempCookie : cookies) {
				if(tempCookie.getName().equals("type")) {
					type = tempCookie.getValue();
				}
			}
		}
		return type;
	}

}
