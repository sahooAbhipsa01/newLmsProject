package models;

public class Grade {
	private int id;
    private int studentId;
    private int courseId;
    private String courseName;
    public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	private String grade;
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
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getGrade() {
		return grade;
	}
	@Override
	public String toString() {
		return "Grade [id=" + id + ", studentId=" + studentId + ", courseId=" + courseId + ", grade=" + grade + "]";
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Grade(int id, int studentId, int courseId, String grade) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.courseId = courseId;
		this.grade = grade;
	}
	public Grade() {}

}
