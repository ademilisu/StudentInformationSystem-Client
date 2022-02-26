 package schoolclientdemo.model;

public class LectureDetail {
	
	private int id;
	private String description;
	private int credit;
	private String semester;
	private String grade;
	private LectureStatus type;
	private Lecture lecture;
	
	public LectureDetail() {
		
	}
	
	public LectureDetail(String description, int credit, String semester, String grade, LectureStatus type) {
		this.description = description;
		this.credit = credit;
		this.semester = semester;
		this.grade = grade;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public LectureStatus getType() {
		return type;
	}

	public void setType(LectureStatus type) {
		this.type = type;
	}

	public Lecture getLecture() {
		return lecture;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}

}
