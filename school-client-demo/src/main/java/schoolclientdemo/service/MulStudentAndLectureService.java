package schoolclientdemo.service;

import java.util.List;

import schoolclientdemo.model.Lecture;
import schoolclientdemo.model.Student;

public interface MulStudentAndLectureService {

	List<Lecture> listMyLectures(String userId, String studentId);
	
	List<Student> listStudentsOfLecture(String userId, String lectureId);
	
	void addToMyLectures(String userId, String typeId, Lecture lecture);

	void removeFromMyLectures(String userId, String typeId, int lectureId);


}
