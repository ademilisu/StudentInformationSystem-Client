package schoolclientdemo.service;


import schoolclientdemo.model.AppUser;
import schoolclientdemo.model.UserData;

public interface AuthenticationService {

	UserData login(AppUser user);

	String addNewStudent(String userId, AppUser appUser);

	String addNewInstructor(String userId, AppUser appUser);

	String addNewAdmin(String userId, AppUser appUser);

	void logout(String userId);

	void changePassword(String userId, AppUser appUser);



}
