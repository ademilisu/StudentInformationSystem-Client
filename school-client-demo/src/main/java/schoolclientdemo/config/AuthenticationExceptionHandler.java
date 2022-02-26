package schoolclientdemo.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import schoolclientdemo.model.UserData;

@ControllerAdvice
public class AuthenticationExceptionHandler {
	/*When an AuthenticationFailedException occurs while login,
	 * this class is handled and sends the error message.
	 */
	@ExceptionHandler
	public String handleException(AuthenticationFailException exc, Model theModel){
		UserData user = new UserData();
		theModel.addAttribute("user", user);
		String error = "Username or Password is incorrect!";
		theModel.addAttribute("error", error);
		return "login-form";
	}
}
