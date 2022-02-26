package schoolclientdemo.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import schoolclientdemo.config.ManageCookies;
import schoolclientdemo.model.Lecture;
import schoolclientdemo.model.Student;
import schoolclientdemo.service.LectureService;
import schoolclientdemo.service.MulStudentAndLectureService;

@Controller
@RequestMapping("/")
public class MulStudentAndLectureController {
	
	@Autowired
	private ManageCookies manageCookie;
	
	@Autowired
	private MulStudentAndLectureService studentAndLectureService;
	
	@Autowired
	private LectureService lectureService;
	
	@GetMapping("/addToMyLectures")
	public String addToMyLectures(HttpServletRequest request, @RequestParam("lectureId") int lectureId) {
		String userId = manageCookie.getUserId(request);
		String typeId = manageCookie.getTypeId(request);
		Lecture lecture = lectureService.findLecture(userId, lectureId);
		studentAndLectureService.addToMyLectures(userId, typeId, lecture);
		return "redirect:/myLectures";
	}
	
	@GetMapping("/removeFromMyLectures")
	public String removeFromMyLectures(HttpServletRequest request, @RequestParam("lectureId") int lectureId) {
		String userId = manageCookie.getUserId(request);
		String typeId = manageCookie.getTypeId(request);
		studentAndLectureService.removeFromMyLectures(userId, typeId, lectureId);
		return "redirect:/myLectures";
	}
	
	@GetMapping("/studentsOfLecture")
	public String studentsOfLecture(HttpServletRequest request, 
			@RequestParam("lectureId") String lectureId,
			Model theModel) {
		String userId = manageCookie.getUserId(request);
		Student student = new Student();
		List<Student> students = studentAndLectureService.listStudentsOfLecture(userId, lectureId);
		theModel.addAttribute("students", students);
		theModel.addAttribute("student", student);
		return "studentList";
	}
}
