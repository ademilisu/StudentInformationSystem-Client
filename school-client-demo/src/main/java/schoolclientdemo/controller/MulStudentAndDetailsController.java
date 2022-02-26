package schoolclientdemo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import schoolclientdemo.config.ManageCookies;
import schoolclientdemo.model.Student;
import schoolclientdemo.model.StudentDetail;
import schoolclientdemo.service.MulStudentAndDetailService;
import schoolclientdemo.service.StudentService;

@Controller
@RequestMapping("/")
public class MulStudentAndDetailsController {
	
	@Autowired
	private ManageCookies manageCookie;
	
	@Autowired
	private MulStudentAndDetailService studentAndDetailService;
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("studentDetails")
	public String studentDetails(HttpServletRequest request,
			@RequestParam("studentId") String studentId, 
			Model theModel) {
		String userId = manageCookie.getUserId(request);
		String type = manageCookie.getType(request);
		Student student = studentService.findStudent(userId, studentId);
		StudentDetail detail = null;
		if(type.equals("admin")) {
			detail = studentAndDetailService.findStudentDetail(userId, studentId);
		}
		theModel.addAttribute("account", student);
		theModel.addAttribute("detail", detail);
		return "accountInfo";
	}
}
