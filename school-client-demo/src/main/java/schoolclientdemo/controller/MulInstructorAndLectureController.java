package schoolclientdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import schoolclientdemo.config.ManageCookies;
import schoolclientdemo.model.Instructor;
import schoolclientdemo.service.InstructorService;
import schoolclientdemo.service.MulInstructorAndLectureService;

@Controller
@RequestMapping("/")
public class MulInstructorAndLectureController {
	
	
	@Autowired
	private ManageCookies manageCookie;
	
	@Autowired
	private MulInstructorAndLectureService instructorAndLectureService;
	
	@Autowired
	private InstructorService instructorService;
	
	@GetMapping("/showAddInstructorList")
	public String showAddInstructorList(HttpServletRequest request, Model theModel, 
			@RequestParam("lectureId") int lectureId) {
		String userId = manageCookie.getUserId(request);
		List<Instructor> instructors = instructorService.findAllInstructors(userId);
		List<Instructor> instructorsOfLecture = instructorAndLectureService.allInstructorsOfLecture(userId, lectureId);
		List<Instructor> instructorsList = new ArrayList<>();
		for(Instructor temp : instructors) {
			if(instructorsOfLecture.contains(temp)) {}
			else instructorsList.add(temp);
		}
		theModel.addAttribute("instructors", instructorsList);
		theModel.addAttribute("instructorsOfLecture", instructorsOfLecture);
		theModel.addAttribute("lectureId", lectureId);
		return "instructorsOfLecture";
	}
	
	@GetMapping("/addInstructorToLecture")
	public String addInstructorToLecture(HttpServletRequest request,
			@RequestParam("lectureId") int lectureId, @RequestParam("instructorId") int instructorId,
			RedirectAttributes attribute) {
		String userId = manageCookie.getUserId(request);
		Instructor instructor = instructorService.findInstructor(userId, String.valueOf(instructorId));
		instructorAndLectureService.addInstructorToLecture(userId, lectureId, instructor);		
		attribute.addAttribute("lectureId", lectureId);
		return "redirect:/showAddInstructorList";
	}
	
	@GetMapping("/removeInstructorFromLecture")
	public String removeInstructorFromLecture(HttpServletRequest request,
			@RequestParam("lectureId") int lectureId, @RequestParam("instructorId") int instructorId,
			RedirectAttributes attribute) {
		String userId = manageCookie.getUserId(request);
		Instructor instructor = instructorService.findInstructor(userId, String.valueOf(instructorId));
		instructorAndLectureService.removeInstructorFromLecture(userId, lectureId, instructor);
		
		attribute.addAttribute("lectureId", lectureId);
		return "redirect:/showAddInstructorList";
	}
}
