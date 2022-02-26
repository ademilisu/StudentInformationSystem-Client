 package schoolclientdemo.model;

public class Result {
	
	private int id;
	private int studentId;
	private int lectureId;
	private String examOne;
	private String examTwo;
	private String finalExam;
	private Transcript transcript;
	
	public Result() {
		
	}

	public Result(String examOne, String examTwo, String finalExam) {
		this.examOne = examOne;
		this.examTwo = examTwo;
		this.finalExam = finalExam;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getLectureId() {
		return lectureId;
	}

	public void setLectureId(int lectureId) {
		this.lectureId = lectureId;
	}

	public String getExamOne() {
		return examOne;
	}

	public void setExamOne(String examOne) {
		this.examOne = examOne;
	}

	public String getExamTwo() {
		return examTwo;
	}

	public void setExamTwo(String examTwo) {
		this.examTwo = examTwo;
	}

	public String getFinalExam() {
		return finalExam;
	}

	public void setFinalExam(String finalExam) {
		this.finalExam = finalExam;
	}

	public Transcript getTranscript() {
		return transcript;
	}

	public void setTranscript(Transcript transcript) {
		this.transcript = transcript;
	}
	
}
