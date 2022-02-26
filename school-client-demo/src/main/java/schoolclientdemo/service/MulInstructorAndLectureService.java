package schoolclientdemo.service;

import java.util.List;

import schoolclientdemo.model.Instructor;
import schoolclientdemo.model.Lecture;

public interface MulInstructorAndLectureService {

	List<Instructor> allInstructorsOfLecture(String userId, int lectureId);
	
	List<Lecture> listLecturesOfInstructor(String userId, String instructorId);

	void addInstructorToLecture(String userId, int lectureId, Instructor instructor);

	void removeInstructorFromLecture(String userId, int lectureId, Instructor instructor);


}
