package schoolclientdemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import schoolclientdemo.model.Result;
import schoolclientdemo.model.ResultList;
import schoolclientdemo.model.Student;
import schoolclientdemo.service.LectureService;
import schoolclientdemo.service.MulInstructorAndLectureService;
import schoolclientdemo.service.MulStudentAndLectureService;
import schoolclientdemo.service.ResultService;

@Controller
@RequestMapping("/")
public class ResultController {
	
	@Autowired
	private ManageCookies manageCookie;
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private MulStudentAndLectureService studentAndLectureService;
	
	@Autowired
	private MulInstructorAndLectureService instructorAndLectureService;
	
	@Autowired
	private LectureService lectureService;
	
	@GetMapping("/showResultsPage")
	public String showResults(HttpServletRequest request,
			Model theModel) {
		String userId = manageCookie.getUserId(request);
		String typeId = manageCookie.getTypeId(request);
		String type = manageCookie.getType(request);
		List<Lecture> lectures = null;
		if(type.equals("student")) {
			lectures = studentAndLectureService.listMyLectures(userId, typeId);
		}
		else if(type.equals("instructor")) {
			lectures = instructorAndLectureService.listLecturesOfInstructor(userId, typeId);
		}
		else {
			lectures = lectureService.listOfLectures(userId);
		}
		theModel.addAttribute("lectures", lectures);
		return "results";
	}
	
	@GetMapping("/showUpdateResultsForm")
	public String showUpdateResultsForm(HttpServletRequest request, 
			@RequestParam("lectureId") String lectureId,
			@RequestParam("lectureName") String lectureName,
			Model theModel) {
		String userId = manageCookie.getUserId(request);
		List<Student> students = studentAndLectureService.listStudentsOfLecture(userId, lectureId);
		Map<Integer, Student> studentMap = new HashMap<>();
		for(Student temp : students) {
			studentMap.put(temp.getId(), temp);
		}
		List<Result> results = resultService.findLectureResults(userId, lectureId);
		ResultList resultList = new ResultList();
		resultList.setResults(results);
		theModel.addAttribute("lectureName", lectureName);
		theModel.addAttribute("students", studentMap);
		theModel.addAttribute("resultList", resultList);
		return "updateResults-form";
	}
	
	@GetMapping("/showResults")
	public String showResults(HttpServletRequest request, 
			@RequestParam("lectureId") String lectureId,
			@RequestParam("lectureName") String lectureName,
			Model theModel) {
		String userId = manageCookie.getUserId(request);
		List<Student> students = studentAndLectureService.listStudentsOfLecture(userId, lectureId);
		Map<Integer, Student> studentMap = new HashMap<>();
		for(Student temp : students) {
			studentMap.put(temp.getId(), temp);
		}
		List<Result> results = resultService.findLectureResults(userId, lectureId);
		theModel.addAttribute("lectureId", lectureId);
		theModel.addAttribute("lectureName", lectureName);
		theModel.addAttribute("students", studentMap);
		theModel.addAttribute("results", results);
		return "resultList";
		
	}
	
	@PostMapping("/updateResults")
	public String updateResults(HttpServletRequest request, 
			@ModelAttribute("resultList") ResultList results) {
		String userId = manageCookie.getUserId(request);
		for(Result temp: results.getResults()) {
			if(temp.getExamOne().equals("")) {
				temp.setExamOne(null);
			}
			if(temp.getExamTwo().equals("")) {
				temp.setExamTwo(null);
			}
			if(temp.getFinalExam().equals("")) {
				temp.setFinalExam(null);
			}
		}
		resultService.updateAllResults(userId, results.getResults());
		return "redirect:/showResultsPage";
	}
	
	
	@GetMapping("/deleteResult")
	public String deleteResult(HttpServletRequest request, 
			@RequestParam("resultId") int resultId) {
		System.out.println("delete result içinde");
		String userId = manageCookie.getUserId(request);
		resultService.deleteResult(userId, String.valueOf(resultId));
		return "redirect:/showResultsPage";
	}
	
	
	
	@GetMapping("/deleteAllResults")
	public String deleteAllResults(HttpServletRequest request, 
			@RequestParam("lectureId") String lectureId) {
		String userId = manageCookie.getUserId(request);
		resultService.deleteAllResult(userId, lectureId);
		return "redirect:/showResultsPage";
	}
	
}
