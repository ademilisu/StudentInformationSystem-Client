package schoolclientdemo.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import schoolclientdemo.config.ManageCookies;
import schoolclientdemo.model.AppUser;
import schoolclientdemo.model.UserData;
import schoolclientdemo.service.AuthenticationService;

@Controller
@RequestMapping("/")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private ManageCookies cookies;
	
	@GetMapping("/showLoginForm")
	public String showLoginForm(Model theModel) {
		UserData user = new UserData();
		theModel.addAttribute("user", user);
		return "login-form";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("user") UserData user,
			HttpServletResponse response, RedirectAttributes attribute) {
		AppUser appUser = new AppUser();
		appUser.setUsername(user.getUsername());
		appUser.setPassword(user.getPassword());
		user = authService.login(appUser);
		Cookie cookie1 = new Cookie("userId", Integer.toString(user.getUserId()));
		Cookie cookie2 = new Cookie("typeId", Integer.toString(user.getTypeId()));
		Cookie cookie3 = null;
		Cookie cookie4 = null;
		String newAccount = null;
		if(user.getType().equals("student")) {
			cookie3 = new Cookie("info", user.getFirstName() + user.getLastName());
			cookie4 = new Cookie("type", "student");
			newAccount = user.getIsNewAccount();
		}		
		if(user.getType().equals("instructor")) {
			cookie3 = new Cookie("info", user.getFirstName() + user.getLastName());
			cookie4 = new Cookie("type", "instructor");
			newAccount = user.getIsNewAccount();
		}
		
		if(user.getType().equals("admin")){
			cookie3 = new Cookie("info", user.getFirstName());
			cookie4 = new Cookie("type", "admin");
		}
		attribute.addFlashAttribute("account", newAccount);
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
		response.addCookie(cookie4);
		return "redirect:/home";
	}
	
	@GetMapping("/showNewAccountForm")
	public String showNewAccount(Model theModel) {
		UserData userData = new UserData();
		theModel.addAttribute("userData", userData);
		return "addAccount";
	}
	
	@PostMapping("/signUp")
	public String addNewAccount(@ModelAttribute("userData") UserData userData,
			 Model theModel, HttpServletRequest request) {
		String userId = cookies.getUserId(request); 
		AppUser appUser = new AppUser();
		appUser.setUsername(userData.getUsername());
		appUser.setPassword(userData.getPassword());
		String username = null;
		if(userData.getType().equals("student")) {
			username = authService.addNewStudent(userId, appUser);
		}
		if(userData.getType().equals("instructor")) {
			username = authService.addNewInstructor(userId, appUser);
		}
		if(userData.getType().equals("admin")) {
			username = authService.addNewAdmin(userId, appUser);
		}
		
		theModel.addAttribute("username", username);
		return "addAccount";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("logout endpoint içinde");
		String userId = cookies.getUserId(request);
		authService.logout(userId);
		Cookie cookie = new Cookie("typeId", "remove");
		cookie.setMaxAge(0);
		Cookie cookie2 = new Cookie("type", "remove");
		cookie2.setMaxAge(0);
		Cookie cookie3 = new Cookie("userId", "remove");
		cookie3.setMaxAge(0);
		Cookie cookie4 = new Cookie("info", "remove");
		cookie4.setMaxAge(0);
		response.addCookie(cookie);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
		response.addCookie(cookie4);
		return "redirect:/home";
	}
	
	@GetMapping("/showChangePasswordForm")
	public String showChangePasswordForm(HttpServletRequest request,
			Model theModel) {
		UserData user = new UserData();
		theModel.addAttribute("user", user);
		return "passwordChange-form";
		
	}
	
	@PostMapping("/changePassword")
	public String changePassword(@ModelAttribute("user") UserData userData,
			HttpServletRequest request) {
		String userId = cookies.getUserId(request);
		AppUser appUser = new AppUser();
		appUser.setPassword(userData.getPassword());
		authService.changePassword(userId, appUser);
		return "redirect:/home";
	}
	
	
	@GetMapping("/home")
	public String homePage() {
		return "home";
	}
	
	@GetMapping("showInfo")
	public String showInfo(){
		return "info";
	}
	
}	
