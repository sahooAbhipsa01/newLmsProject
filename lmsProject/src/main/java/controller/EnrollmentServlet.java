package controller;

import services.CourseServices;
import services.EnrollmentServices;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Courses;

import java.io.IOException;
import java.util.List;

@WebServlet("/enroll")
public class EnrollmentServlet extends HttpServlet {

    private final EnrollmentServices enrollmentService = new EnrollmentServices();
    private final CourseServices courseServices=new CourseServices();
    
   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve data from the form
    	
    	System.out.println(request.getParameter("studentId")  );
        System.out.println(request.getParameter("courseId"));

        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        
        // Attempt to enroll the student in the course
        enrollmentService.enrollStudentInCourse(studentId, courseId);
        
        HttpSession session = request.getSession();
        int currentUserId=  (int) session.getAttribute("loggedInUserId");
        
        
        // Fetch enrolled courses for the student
        List<Courses> enrolledCourses = enrollmentService.getEnrolledCourses(currentUserId);
        session.setAttribute("enrolledCoursesList", enrolledCourses);
        
        
     // Fetch all courses for the student
        List<Courses>allCourses=courseServices.getAllCourses(); 
        session.setAttribute("allCoursesList", allCourses);

        // Simple redirect to dashboard
        response.sendRedirect("student_dashboard.jsp");
    }
}
