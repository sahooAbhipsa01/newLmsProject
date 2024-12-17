package services;

import database.DBConnection;
import database.DBQueries;
import models.Courses;

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
}
