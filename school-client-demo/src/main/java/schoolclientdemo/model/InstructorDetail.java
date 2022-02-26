package schoolclientdemo.model;


public class InstructorDetail {
	
	private int id;
	private String phoneNumber;
	private Address address;
	private Instructor instructor;
	
	public InstructorDetail() {}
	
	public InstructorDetail(String phoneNumber, Address adress) {
		this.phoneNumber = phoneNumber;
		this.address = adress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
}
