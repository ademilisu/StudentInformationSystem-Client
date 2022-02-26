package schoolclientdemo.service;

import java.util.List;

import schoolclientdemo.model.Instructor;

public interface InstructorService {

	Instructor updateInstructor(String userId, Instructor instructor);

	Instructor findInstructor(String userId, String instructorId);

	List<Instructor> findAllInstructors(String userId);
	
	void deleteInstructor(String userId, String instructorId);

	List<Instructor> searchInstructor(String userId, String name);

}
