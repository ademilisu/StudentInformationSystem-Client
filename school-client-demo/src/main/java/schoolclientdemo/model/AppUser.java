package schoolclientdemo.model;

import java.util.List;


public class AppUser {
	
	private int id;
	private String username;
	private String password;
	private List<Role> roles;
	private Instructor instructor;
	private Student student;
	
	public AppUser() {
		
	}
	
	public AppUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
