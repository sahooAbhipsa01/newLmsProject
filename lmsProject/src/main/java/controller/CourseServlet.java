package controller;

import models.Courses;
import services.CourseServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final CourseServices courseServices = new CourseServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String action = request.getParameter("action");
        String courseName = request.getParameter("courseName");

        try {
            boolean isSuccess = false;

            if ("Create".equalsIgnoreCase(action)) {
                // Create a new course
                //int trainerId = Integer.parseInt(request.getParameter("trainerId"));
            	int trainerId=6;
                Courses course = new Courses();
                course.setName(courseName);
               course.setTrainerId(trainerId);
                
                isSuccess = courseServices.createCourse(course);

            } else if ("Update".equalsIgnoreCase(action)) {
                // Update an existing course
                int courseId = Integer.parseInt(request.getParameter("courseId"));
                int trainerId = Integer.parseInt(request.getParameter("trainerId"));
                Courses course = new Courses(courseId, courseName, trainerId);
                isSuccess = courseServices.updateCourse(course);

            } else if ("Delete".equalsIgnoreCase(action)) {
                // Delete an existing course
                int courseId = Integer.parseInt(request.getParameter("courseId"));
                isSuccess = courseServices.deleteCourse(courseId);
            }

            // Redirect to the course page
            response.sendRedirect("courses.html");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("courses.html");
        }
    }
}
