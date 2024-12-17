package models;

public class Enrollments {
	private int id;          
    private int studentId;    
    private int courseId;
	@Override
	public String toString() {
		return "Enrollments [id=" + id + ", studentId=" + studentId + ", courseId=" + courseId + "]";
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
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public Enrollments(int id, int studentId, int courseId) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.courseId = courseId;
	}
	public Enrollments() {}

}
