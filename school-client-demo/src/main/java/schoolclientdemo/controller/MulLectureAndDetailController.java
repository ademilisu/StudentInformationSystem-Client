package schoolclientdemo.controller;



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
import schoolclientdemo.model.Instructor;
import schoolclientdemo.model.Lecture;
import schoolclientdemo.model.LectureDetail;
import schoolclientdemo.service.MulInstructorAndLectureService;
import schoolclientdemo.service.MulLectureAndDetailService;

@Controller
@RequestMapping("/")
public class MulLectureAndDetailController {
	
	@Autowired
	private ManageCookies cookie;
	
	@Autowired
	private MulLectureAndDetailService detailService;
	
	@Autowired
	private MulInstructorAndLectureService instructorAndLectureService;
	
	@GetMapping("/lecturedetail")
	public String findLectureDetail(@RequestParam("lectureName") String lectureName, 
			@RequestParam("lectureId") int lectureId, 
			Model theModel, HttpServletRequest request) {
		String userId = cookie.getUserId(request);
		LectureDetail detail = detailService.findLectureDetail(userId, lectureId);
		if(detail == null) {
			detail = new  LectureDetail();
			detail.setId(0);
		}	
		Lecture lecture = new Lecture();
		lecture.setLectureName(lectureName);
		detail.setLecture(lecture);
		List<Instructor> instructors = instructorAndLectureService.allInstructorsOfLecture(userId, lectureId);
		theModel.addAttribute("instructors", instructors);
		theModel.addAttribute("detail", detail);
		request.setAttribute("lectureId", lectureId);
		return "lecture-detail";
		
	}
	@GetMapping("/showUpdateLectureForm")
	public String showUpdateLectureForm(HttpServletRequest request, Model theModel) {
		String userId = cookie.getUserId(request);
		String lectureId = request.getParameter("lectureId");
		LectureDetail detail = detailService.findLectureDetail(userId, Integer.parseInt(lectureId));
		if(detail == null) {
			detail = new LectureDetail();
			detail.setId(0);
		}
		theModel.addAttribute("detail", detail);
		theModel.addAttribute("lectureId", lectureId);
		return "lectureDetail-form";
	}
	
	@PostMapping("updateLectureDetail")
	public String addLectureDetail(@ModelAttribute("lectureDetail") LectureDetail lectureDetail, 
			HttpServletRequest request) {
		String userId = cookie.getUserId(request);
		String lectureId = request.getParameter("lectureId");
		if(lectureDetail.getId() != 0) {
			detailService.updateLectureDetail(userId, lectureId, lectureDetail);
		}
		else detailService.addLectureDetail(userId, lectureId, lectureDetail);
		return "redirect:/lectureList";
	}

}
