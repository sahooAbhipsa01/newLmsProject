package services;

import database.DBConnection;
import database.DBQueries;
import models.Courses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseServices {


    public boolean createCourse(Courses course) {
        String query = DBQueries.insertCourse;

        try (Connection con = DBConnection.connectDB();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, course.getName());
            ps.setInt(2, course.getTrainerId());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public boolean updateCourse(Courses course) {
        String query = DBQueries.updateCoursesByCousrseId;

        try (Connection con = DBConnection.connectDB();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, course.getTrainerId());
            ps.setInt(2, course.getId());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCourse(int courseId) {
        String query = DBQueries.deleteCourseById;

        try (Connection con = DBConnection.connectDB();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, courseId);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public List<Courses> getAllCourses() {
        List<Courses> courses = new ArrayList<>();
        String query = DBQueries.getAllCourses;

        try (Connection con = DBConnection.connectDB();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Courses course = new Courses();
                course.setId(rs.getInt("course_id"));
                course.setName(rs.getString("course_name"));
                course.setTrainerName(rs.getString("trainer_name"));
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Courses retrieved: " + courses.size());
        return courses;
    }
}
