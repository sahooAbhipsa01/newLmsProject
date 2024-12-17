package controller;

import models.Courses;
import services.TrainerCourseServices;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/createCourse")
public class TrainerController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private TrainerCourseServices trainerCourseServices = new TrainerCourseServices();

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object trainerIdObj = session.getAttribute("trainerId");

        // Check if trainerId is present in the session
        if (trainerIdObj == null) {
            // Redirect to login page or show error message
            response.sendRedirect("index.html"); // Adjust the page as needed
            return;
        }

        // If trainerId is present, continue with course creation
        int trainerId = Integer.parseInt(trainerIdObj.toString());

        String courseName = request.getParameter("courseName");
        String courseDescription = request.getParameter("courseDescription");

        Courses newCourse = new Courses();
        newCourse.setName(courseName);
        newCourse.setCourseDescription(courseDescription);
        newCourse.setTrainerId(trainerId);

        boolean isCreated = trainerCourseServices.createCourseForTrainer(newCourse);

        if (isCreated) {
            response.sendRedirect("trainer_dashboard.jsp?message=Course Created Successfully");
        } else {
            response.sendRedirect("trainer_dashboard.jsp?message=Error Creating Course");
        }
    }

}
