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
import schoolclientdemo.model.Student;
import schoolclientdemo.service.StudentService;

@Controller
@RequestMapping("/")
public class StudentController {
	
	@Autowired
	private ManageCookies manageCookie;
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/showStudentList")
	public String showStudentList(HttpServletRequest request, 
			Model theModel) {
		String userId = manageCookie.getUserId(request);
		Student student = new Student();
		List<Student> students = studentService.listOfAllStudents(userId);
		theModel.addAttribute("students", students);
		theModel.addAttribute("student", student);
		return "studentList";
	}
	
	@GetMapping("/searchStudent")
	public String searchStudent(HttpServletRequest request,
			@ModelAttribute("student") Student student,
			Model theModel) {
		String userId = manageCookie.getUserId(request);
		List<Student> students = studentService.searchStudent(userId, student.getEmail());
		theModel.addAttribute("students", students);
		theModel.addAttribute("student", student);
		return "studentList";
	}
	
	@GetMapping("deleteStudent")
	public String deleteStudent(HttpServletRequest request, 
			@RequestParam("studentId") String studentId,
			Model theModel) {
		String userId = manageCookie.getUserId(request);
		studentService.deleteStudent(userId, studentId);
		return "redirect:/showStudentList";
	}
}
