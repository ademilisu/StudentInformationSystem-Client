package schoolclientdemo.service;

import java.util.List;

import schoolclientdemo.model.Lecture;

public interface LectureService {

	List<Lecture> listOfLectures(String userId);

	Lecture findLecture(String userId, int lectureId);

	Lecture addLecture(String userId, Lecture lecture);

	void deleteLecture(String userId, int lectureId);

	Lecture updateLecture(String userId, Lecture lecture);

	void setActive(String userId);

	void setPassive(String userId);

	List<Lecture> searchLectures(String userId, String lectureId);

}
