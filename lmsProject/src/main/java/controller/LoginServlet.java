package controller;

import services.CourseServices;
import services.EnrollmentServices;
import services.ForumMessagesServices;
import services.UserServices;
import models.Courses;
import models.ForumMessage;
import models.Grade;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserServices userServices = new UserServices();
    private final CourseServices courseServices=new CourseServices();
    private final EnrollmentServices enrollmentServices = new EnrollmentServices();
    private final ForumMessagesServices forumMessagesServices = new ForumMessagesServices(); 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // Retrieve email and password from request
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate email and password inputs
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            response.getWriter().println("<script type='text/javascript'>");
            response.getWriter().println("alert('Email and password are required!');");
            response.getWriter().println("window.location.href = 'index.html';"); // Redirect back to login page
            response.getWriter().println("</script>");
            return;
        }

        // Validate login credentials
        int currentUserId = userServices.login(email, password);

        // If login is successful
        if (currentUserId != -1) {
            User user = userServices.getUserById(currentUserId);


         // Set session attributes for the logged-in user
            HttpSession session = request.getSession();
            
         // Fetch enrolled courses for the student
            List<Courses> enrolledCourses = enrollmentServices.getEnrolledCourses(currentUserId);
            session.setAttribute("enrolledCoursesList", enrolledCourses);
            
            
         // Fetch all courses for the student
            List<Courses>allCourses=courseServices.getAllCourses(); 
            session.setAttribute("allCoursesList", allCourses);
            session.setAttribute("loggedInUserId", currentUserId);
            session.setAttribute("user", user);
            
            
         // Fetch grades for the student if the role is "student"
            if ("student".equalsIgnoreCase(user.getRole())) {
            	List<Grade> gradesList = enrollmentServices.getGradeByStudentId(currentUserId);
                session.setAttribute("studentGradeList", gradesList);                
            }
            
         // Fetch all forum messages   
            List<ForumMessage> allForumTopics = forumMessagesServices.getAllForumMessages();
            session.setAttribute("allForumMessages", allForumTopics);

            // Check the user role
            if ("student".equalsIgnoreCase(user.getRole())) {
                System.out.println("Redirecting to student_dashboard.jsp");
                response.sendRedirect("student_dashboard.jsp");
            } else if ("teacher".equalsIgnoreCase(user.getRole())) {
            	List<Grade> gradesList = enrollmentServices.getEnrollmentsByTeacherId(currentUserId);
                session.setAttribute("gradesList", gradesList);
                System.out.println("Redirecting to trainer_dashboard.jsp");
                response.sendRedirect("trainer_dashboard.jsp");
            } else {
                response.sendRedirect("index.html");  // In case of an unrecognized role
            }
        } else {
            // If login failed, redirect back to login page
            System.out.println("Login failed. Redirecting to index.html.");
            response.sendRedirect("index.html");
        }
    }
}