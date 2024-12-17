package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import database.DBQueries;
import models.Courses;

public class TrainerCourseServices {
	
	private int trainerId;

		// create course for trainer
		public boolean createCourseForTrainer(Courses course) {
		    String query = DBQueries.insertCoursesWithDescription;

		    try (Connection con = DBConnection.connectDB();
		         PreparedStatement ps = con.prepareStatement(query)) {
		        ps.setString(1, course.getName());
		        ps.setInt(2, course.getTrainerId());
		        ps.setString(3, course.getCourseDescription());  // Store course description
		        int result = ps.executeUpdate();
		        return result > 0;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return false;
		}
		
		// Get all courses for Trainer
	    public List<Courses> getAllCoursesForTrainer() {
	        List<Courses> courses = new ArrayList<>();
	        String query = "SELECT * FROM courses WHERE trainer_id = ?";

	        try (Connection con = DBConnection.connectDB();
	             PreparedStatement ps = con.prepareStatement(query)) {
	            ps.setInt(1, trainerId);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                Courses course = new Courses();
	                course.setId(rs.getInt("course_id"));
	                course.setName(rs.getString("course_name"));
	                course.setTrainerName(rs.getString("trainer_name"));
	                course.setCourseDescription(rs.getString("course_description"));
	                courses.add(course);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return courses;
	    }
	    

}
