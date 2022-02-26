package schoolclientdemo.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import schoolclientdemo.config.ManageCookies;
import schoolclientdemo.model.Instructor;
import schoolclientdemo.model.InstructorDetail;
import schoolclientdemo.model.Student;
import schoolclientdemo.model.StudentDetail;
import schoolclientdemo.service.InstructorService;
import schoolclientdemo.service.MulInstructorAndDetailService;
import schoolclientdemo.service.MulStudentAndDetailService;
import schoolclientdemo.service.StudentService;

@Controller
@RequestMapping("/")
public class AccountController {
	
	@Autowired
	private ManageCookies cookies;
	
	@Autowired
	private MulStudentAndDetailService studentDetailService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private InstructorService instructorService;
	
	@Autowired
	private MulInstructorAndDetailService instructorDetailService;
	
	@GetMapping("/showUpdateAccountForm")
	public String showUpdateAccountForm(HttpServletRequest request, 
			Model theModel) {
		String type = cookies.getType(request);
		String userId = cookies.getUserId(request);
		String typeId = cookies.getTypeId(request);
		if(type.equals("student")) {
			Student student = studentService.findStudent(userId, typeId);
			theModel.addAttribute("account", student);
		}
		if(type.equals("instructor")) {
			Instructor instructor = instructorService.findInstructor(userId, typeId);
			theModel.addAttribute("account", instructor);
		}
		return "updateAccount-form";	
	}
	
	@PostMapping("/updateAccount")
	public String updateAccount(HttpServletRequest request,
			@ModelAttribute("account") Student student,
			Model theModel) {
		String type = cookies.getType(request); 
		String userId = cookies.getUserId(request);
		String typeId = cookies.getTypeId(request);
		if(type.equals("student")) {
			student.setId(Integer.parseInt(typeId));
			studentService.updateStudent(userId, student);
			return "redirect:/showStudentDetailForm";
		}
		if(type.equals("instructor")) {
			Instructor instructor = new Instructor();
			instructor.setId(Integer.parseInt(typeId));
			instructor.setFirstName(student.getFirstName());
			instructor.setLastName(student.getLastName());
			instructorService.updateInstructor(userId, instructor);
			return "redirect:/showInstructorDetailForm";
		}
		return "redirect:/home";
	}
	@GetMapping("/showStudentDetailForm")
	public String showStudentDetailForm(HttpServletRequest request, Model theModel) {
		String userId = cookies.getUserId(request);
		String typeId = cookies.getTypeId(request);
		StudentDetail detail = studentDetailService.findStudentDetail(userId, typeId);
		if(detail == null) {
			detail = new StudentDetail();
			detail.setId(0);
		}
		theModel.addAttribute("detail", detail);
		return "studentDetail-form";
	}
	
	@GetMapping("/showInstructorDetailForm")
	public String showInstructorDetailForm(HttpServletRequest request, Model theModel) {
		String userId = cookies.getUserId(request);
		String typeId = cookies.getTypeId(request);
		InstructorDetail detail = instructorDetailService.findInstructorDetail(userId, typeId);
		if(detail == null) {
			detail = new InstructorDetail();
			detail.setId(0);
		}
		theModel.addAttribute("detail", detail);
		return "instructorDetail-form";
	}
	
	@PostMapping("/updateStudentDetail")
	public String updateStudentDetail(HttpServletRequest request, 
			@ModelAttribute("detail") StudentDetail detail) {
		String userId = cookies.getUserId(request);
		String typeId = cookies.getTypeId(request);
		int detailId = detail.getId();
		if(detailId == 0) {
			studentDetailService.saveStudentDetail(userId, typeId, detail);
		}
		else studentDetailService.updateStudentDetail(userId, typeId, detail);
		return "home";
	}
	
	@PostMapping("/updateInstructorDetail")
	public String updateInstructorDetail(HttpServletRequest request,
			@ModelAttribute("detail") InstructorDetail detail) {
		String userId = cookies.getUserId(request);
		String typeId = cookies.getTypeId(request);
		if(detail.getId() == 0) {
			instructorDetailService.saveInstructorDetail(userId, typeId, detail); 
		}
		else instructorDetailService.updateInstructorDetail(userId, typeId, detail);
		return "home";
	}
	
	@GetMapping("/accountInformation")
	public String accountInfo(HttpServletRequest request, Model theModel) {
		String userId = cookies.getUserId(request);
		String typeId = cookies.getTypeId(request);
		String type = cookies.getType(request);
		if(type.equals("student")) {
			Student student = studentService.findStudent(userId, typeId);
			StudentDetail detail = studentDetailService.findStudentDetail(userId, typeId);
			theModel.addAttribute("account", student);
			theModel.addAttribute("detail", detail);
			return "myAccount";
		}
		else if(type.equals("instructor")) {
			Instructor instructor = instructorService.findInstructor(userId, typeId);
			InstructorDetail detail = instructorDetailService.findInstructorDetail(userId, typeId);
			theModel.addAttribute("detail", detail);
			theModel.addAttribute("account", instructor);
			return "myAccount";
		}
		else return "redirect:/home";
	}
}
