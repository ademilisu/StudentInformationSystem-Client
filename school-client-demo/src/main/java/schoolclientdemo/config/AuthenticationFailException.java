package schoolclientdemo.config;

public class AuthenticationFailException extends RuntimeException {

	public AuthenticationFailException(String message) {
		super(message);
	}

	public AuthenticationFailException(Throwable cause) {
		super(cause);
	}

	public AuthenticationFailException(String message, Throwable cause) {
		super(message, cause);
	}


}
