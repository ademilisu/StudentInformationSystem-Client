package schoolclientdemo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import schoolclientdemo.config.ManageCookies;
import schoolclientdemo.model.Instructor;
import schoolclientdemo.service.InstructorService;

@Controller
@RequestMapping("/")
public class InstructorController {
	
	@Autowired
	private ManageCookies manageCookie;
	
	@Autowired
	private InstructorService instructorService;
	
	@GetMapping("/showInstructorList")
	public String showInstructorList(HttpServletRequest request, 
			Model theModel) {
		String userId = manageCookie.getUserId(request);
		List<Instructor> instructors = instructorService.findAllInstructors(userId);
		Instructor instructor = new Instructor();
		theModel.addAttribute("instructors", instructors);
		theModel.addAttribute("instructor", instructor);
		return "instructorList";
	}
	
	@GetMapping("/searchInstructor")
	public String searchInstructor(HttpServletRequest request, 
			@ModelAttribute("instructor") Instructor instructor,
			Model theModel) {
		String userId = manageCookie.getUserId(request);
		List<Instructor> instructors = instructorService.searchInstructor(userId, instructor.getEmail());
		theModel.addAttribute("instructors", instructors);
		theModel.addAttribute("instructor", instructor);
		return "instructorList";
	}
	
	@GetMapping("/deleteInstructor")
	public String deleteInstructor(HttpServletRequest request,
			@RequestParam("instructorId") String instructorId) {
		String userId = manageCookie.getUserId(request);
		instructorService.deleteInstructor(userId, instructorId);
		return "redirect:/showInstructorList";
	}
}
