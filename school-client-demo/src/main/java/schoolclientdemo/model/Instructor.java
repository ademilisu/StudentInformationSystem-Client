package schoolclientdemo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
public class Instructor {
	
	private int id;
	private String email;
	private String firstName;
	private String lastName;
	
	@Autowired
	private InstructorDetail instructorDetail;
	
	@Autowired
	private List<Lecture> lectures;
	
	public Instructor() {}

	public Instructor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Instructor(String email) {
		this.email = email;
	}

	public void addLecture(Lecture lecture) {
		if(lectures == null) {
			lectures = new ArrayList<>();
		}
		lectures.add(lecture);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}

	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail = instructorDetail;
	}

	public List<Lecture> getLecture() {
		return lectures;
	}

	public void setLecture(List<Lecture> lectures) {
		this.lectures = lectures;
	}

	@Override
	public boolean equals(Object obj) {
		Instructor instructor = (Instructor) obj;
		if(instructor.getEmail().equals(email)) {
			return true;
		}
		else return false;
	}
	
	
}
