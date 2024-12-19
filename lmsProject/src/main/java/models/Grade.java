package models;

public class Grade {
	
	private int enrollementId;
	private String studentName;
	private String courseName;
	private String currentGrade;
	
	public int getEnrollementId() {
		return enrollementId;
	}

	public void setEnrollementId(int enrollementId) {
		this.enrollementId = enrollementId;
	}

	
	
	@Override
	public String toString() {
		return "Grade [studentName=" + studentName + ", courseName=" + courseName + ", currentGrade=" + currentGrade
				+ "]";
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCurrentGrade() {
		return currentGrade;
	}

	public void setCurrentGrade(String currentGrade) {
		this.currentGrade = currentGrade;
	}

	public Grade() {
		super();
	}

	public Grade(String studentName, String courseName, String currentGrade) {
		super();
		this.studentName = studentName;
		this.courseName = courseName;
		this.currentGrade = currentGrade;
	}
	
	

}