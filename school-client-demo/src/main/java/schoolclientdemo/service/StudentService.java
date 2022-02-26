package schoolclientdemo.service;

import java.util.List;

import schoolclientdemo.model.Student;

public interface StudentService {

	Student updateStudent(String userId, Student student);

	Student findStudent(String userId, String studentId);

	List<Student> listOfAllStudents(String userId);

	List<Student> searchStudent(String userId, String email);

	void deleteStudent(String userId, String studentId);

}
