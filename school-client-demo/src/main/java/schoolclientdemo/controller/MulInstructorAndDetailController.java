package schoolclientdemo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import schoolclientdemo.config.ManageCookies;
import schoolclientdemo.model.Instructor;
import schoolclientdemo.model.InstructorDetail;
import schoolclientdemo.service.InstructorService;
import schoolclientdemo.service.MulInstructorAndDetailService;

@Controller
@RequestMapping("/")
public class MulInstructorAndDetailController {
	
	@Autowired
	private ManageCookies manageCookie;
	
	@Autowired
	private MulInstructorAndDetailService instructorAndDetailService;
	
	@Autowired
	private InstructorService instructorService;
	
	@GetMapping("/instructorDetails")
	public String instructorDetail(HttpServletRequest request, 
			@RequestParam("instructorId") String instructorId, 
			Model theModel) {
		String userId = manageCookie.getUserId(request);
		String type = manageCookie.getType(request);
		Instructor instructor = instructorService.findInstructor(userId, instructorId);
		InstructorDetail detail = null;
		if(type.equals("admin")) {
			detail = instructorAndDetailService.findInstructorDetail(userId, instructorId);
		}
		theModel.addAttribute("account", instructor);
		theModel.addAttribute("detail", detail);
		return "accountInfo";
	}
}
