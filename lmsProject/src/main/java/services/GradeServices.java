package services;

import database.DBConnection;
import models.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GradeServices {

    public List<Grade> getGradesByStudentId(int studentId) {
        List<Grade> grades = new ArrayList<>();
        String query = "SELECT c.name AS course_name, g.grade " +
                       "FROM grades g " +
                       "JOIN courses c ON g.course_id = c.course_id " +
                       "WHERE g.student_id = ?";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setCourseName(rs.getString("course_name"));
                grade.setGrade(rs.getString("grade"));
                grades.add(grade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return grades;
    }
}
