package schoolclientdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import schoolclientdemo.config.ManageCookies;
import schoolclientdemo.model.Lecture;
import schoolclientdemo.model.LectureSelection;
import schoolclientdemo.service.LectureService;
import schoolclientdemo.service.MulInstructorAndLectureService;
import schoolclientdemo.service.MulStudentAndLectureService;

@Controller
@RequestMapping("/")
public class LectureController {
		
	@Autowired
	private ManageCookies manageCookie;
	
	@Autowired
	private LectureService lectureService;
	
	@Autowired
	private MulStudentAndLectureService studentAndLectureService;
	
	@Autowired 
	private MulInstructorAndLectureService instructorAndLectureService;
	
	@GetMapping("/myLectures")
	public String myLectures(HttpServletRequest request, Model theModel) {
		String userId = manageCookie.getUserId(request);
		String typeId = manageCookie.getTypeId(request);
		String type = manageCookie.getType(request);
		if(type.equals("instructor")) {
			List<Lecture> myLectures = instructorAndLectureService.listLecturesOfInstructor(userId, typeId);
			theModel.addAttribute("myLectures", myLectures);
			return "lecturesOfInstructor";
		}
		else {
			List<Lecture> myLectures = studentAndLectureService.listMyLectures(userId, typeId);
			List<Lecture> allLectures = lectureService.listOfLectures(userId);
			List<Lecture> lectureList = new ArrayList<>();
			for(Lecture temp : allLectures) {
				if(myLectures.contains(temp)) {}
				else {lectureList.add(temp);}
			}
			theModel.addAttribute("myLectures", myLectures);
			theModel.addAttribute("lectures", lectureList);
			return "myLectures";
		}
		
	}
	
	@GetMapping("/searchLecture")
	public String searchLecture(HttpServletRequest request,
			@ModelAttribute("lecture") Lecture lecture,
			Model theModel) {
		String userId = manageCookie.getUserId(request);
		List<Lecture> lectures = lectureService.searchLectures(userId, lecture.getLectureName());
		theModel.addAttribute("lectures", lectures);
		theModel.addAttribute("lecture", lecture);
		return "lectures";
	}
	
	@GetMapping("/lectureList")
	public String listOfLectures(Model theModel, HttpServletRequest request){
		String userId = manageCookie.getUserId(request);
		Lecture lecture = new Lecture();
		List<Lecture> lectures = lectureService.listOfLectures(userId);
		theModel.addAttribute("lectures", lectures);
		theModel.addAttribute("lecture", lecture);
		return "lectures";
	}
	
	@GetMapping("/showAddLectureForm")
	public String showAddLectureForm(Model theModel, HttpServletRequest request) {
		Lecture lecture = new Lecture();
		lecture.setId(0);
		theModel.addAttribute("lecture", lecture);
		return "lecture-form";
	}
	
	@PostMapping("/addLecture")
	public String addLecture(@ModelAttribute("lecture") Lecture lecture, 
			Model theModel, HttpServletRequest request) {
		String userId = manageCookie.getUserId(request);
		if(lecture.getId() == 0) {
			lectureService.addLecture(userId, lecture);
		}
		else {
			lectureService.updateLecture(userId, lecture);
		}
		return "redirect:/lectureList";
	}
	
	@GetMapping("/updateLecture")
	public String updateLecture(HttpServletRequest request,
			@RequestParam("lectureId") int lectureId, Model theModel) {
		String userId = manageCookie.getUserId(request);
		Lecture lecture = lectureService.findLecture(userId, lectureId);
		theModel.addAttribute("lecture", lecture);
		return "lecture-form";
	}
	
	@GetMapping("/deleteLecture")
	public String deleteLecture(HttpServletRequest request, 
			@RequestParam("lectureId") int lectureId) {
		String userId = manageCookie.getUserId(request);
		lectureService.deleteLecture(userId, lectureId);
		return "redirect:/lectureList";
	}
	
	@GetMapping("/showSelectionOption")
	public String showSelectionOptionForm(Model theModel) {
		Lecture lecture = new Lecture();
		theModel.addAttribute("lecture", lecture);
		return "lectureSelection-form";
	}
	
	@PostMapping("/selectionOption")
	public String selectionOption(HttpServletRequest request, 
			@ModelAttribute("lecture") Lecture lecture) {
		String userId = manageCookie.getUserId(request);
		if(lecture.getLectureSelection().equals(LectureSelection.ACTIVE)) {
			lectureService.setActive(userId);
		}
		if(lecture.getLectureSelection().equals(LectureSelection.PASSIVE)) {
			lectureService.setPassive(userId);
		}
		return "redirect:/lectureList";
	}
	
}
