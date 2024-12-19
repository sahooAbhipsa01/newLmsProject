package services;

import database.DBConnection;
import database.DBQueries;
import models.Courses;
import models.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentServices {

	public boolean enrollStudentInCourse(int studentId, int courseId) {
		String query = DBQueries.insertEnrollments;

		try (Connection con = DBConnection.connectDB(); PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, studentId);
			ps.setInt(2, courseId);

			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public List<Courses> getEnrolledCourses(int studentId) {
        List<Courses> courses = new ArrayList<>();
        String query = "SELECT c.course_id, c.name, u.name AS trainer_name " +
                       "FROM courses c " +
                       "JOIN enrollments e ON c.course_id = e.course_id " +
                       "JOIN users u ON c.trainer_id = u.user_id " +
                       "WHERE e.student_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Courses course = new Courses();
                course.setId(rs.getInt("course_id"));
                course.setName(rs.getString("name"));
                course.setTrainerName(rs.getString("trainer_name"));
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }
	
	public boolean updateGradeByEnrollmentId(int enrollmentId, String newGrade) {
		String query = "update enrollments set grade = ? where enrollment_id = ?";
		try (Connection con = DBConnection.connectDB();
	             PreparedStatement ps = con.prepareStatement(query)) {
	            ps.setString(1, newGrade);
	            ps.setInt(2, enrollmentId);
	            
	            int rowsAffected = ps.executeUpdate();
				return rowsAffected > 0;
				
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return false;
	}
	
	public List<Grade> getEnrollmentsByTeacherId(int userId){
		
		String query = "select e.enrollment_id, c.name as course_name, u.name as student_name, grade from enrollments e join courses c on e.course_id = c.course_id join users u on e.student_id = u.user_id where trainer_id = ?";
		
		List<Grade> allEnrollments = new ArrayList<>();
		try (Connection con = DBConnection.connectDB();
	             PreparedStatement ps = con.prepareStatement(query)) {
	            ps.setInt(1, userId);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	            	int enrollmentId = rs.getInt("enrollment_id");
	            	String courseName = rs.getString("course_name");
	            	String studentName = rs.getString("student_name");
	            	String currentGrade = rs.getString("grade");
	            	
	                Grade grade = new Grade();
	                grade.setEnrollementId(enrollmentId);
	                grade.setCourseName(courseName);
	                grade.setStudentName(studentName);
	                grade.setCurrentGrade(currentGrade);
	              
	                allEnrollments.add(grade);
	                
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return allEnrollments;		
	}
	
public List<Grade> getGradeByStudentId(int userId){
		
		String query = "select e.enrollment_id, c.name as course_name, u.name as student_name, grade from enrollments e join courses c on e.course_id = c.course_id join users u on e.student_id = u.user_id where student_id = ?";
		
		List<Grade> allEnrollments = new ArrayList<>();
		try (Connection con = DBConnection.connectDB();
	             PreparedStatement ps = con.prepareStatement(query)) {
	            ps.setInt(1, userId);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	  
	            	String courseName = rs.getString("course_name");
	            	String currentGrade = rs.getString("grade");
	            	
	                Grade grade = new Grade();
	             
	                grade.setCourseName(courseName);
	                
	                grade.setCurrentGrade(currentGrade);
	              
	                allEnrollments.add(grade);
	                
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return allEnrollments;		
	}
	
	
}