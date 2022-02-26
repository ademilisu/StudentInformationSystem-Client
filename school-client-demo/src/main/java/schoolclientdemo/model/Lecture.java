package schoolclientdemo.model;

import java.util.ArrayList;
import java.util.List;


public class Lecture {
	
	private int id;
	private String lectureName;
	private String language;
	private LectureSelection lectureSelection;
	private LectureDetail lectureDetail;
	private List<Student> students;
	private List<Instructor> instructors;
	
	public Lecture() {}
	
	public Lecture(String lectureName, String language) {
		this.lectureName = lectureName;
		this.language = language;
	}

	public Lecture(String lectureName, String language, LectureSelection lectureSelection) {
		this.lectureName = lectureName;
		this.language = language;
		this.lectureSelection = lectureSelection;
	}
	public Lecture(LectureSelection lectureSelection) {
		this.lectureSelection = lectureSelection;
	}
	
	public Lecture(String lectureName) {
		this.lectureName = lectureName;
	}

	public void addStudent(Student student) {
		if(students == null) {
			students = new ArrayList<>();
		}
		students.add(student);
	}
	
	public void addInstructor(Instructor instructor) {
		if(instructors == null) {
			instructors = new ArrayList<>();
		}
		instructors.add(instructor);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}

	public LectureDetail getLectureDetail() {
		return lectureDetail;
	}

	public void setLectureDetail(LectureDetail lectureDetail) {
		this.lectureDetail = lectureDetail;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(List<Instructor> instructors) {
		this.instructors = instructors;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public LectureSelection getLectureSelection() {
		return lectureSelection;
	}

	public void setLectureSelection(LectureSelection lectureSelection) {
		this.lectureSelection = lectureSelection;
	}
	
	@Override
	public boolean equals(Object obj) {
		Lecture lecture = (Lecture) obj;
		if(lecture.getLectureName().equals(lectureName)) {
			return true;
		}
		else return false;
	}
	
}
