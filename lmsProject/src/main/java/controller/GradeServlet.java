package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Grade;
import services.EnrollmentServices;

@WebServlet("/gradeStudent")
public class GradeServlet extends HttpServlet {
    private final EnrollmentServices enrollmentServices = new EnrollmentServices();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int enrollmentId = Integer.parseInt(request.getParameter("enroll_id"));
		String updatedGrade = request.getParameter("newGrade");
		int currentUserId = Integer.parseInt(request.getParameter("currentUserId"));
		
		enrollmentServices.updateGradeByEnrollmentId(enrollmentId, updatedGrade);
		
		HttpSession session = request.getSession();
		List<Grade> gradesList = enrollmentServices.getEnrollmentsByTeacherId(currentUserId);
		
        session.setAttribute("gradesList", gradesList);
        
        response.sendRedirect("trainer_dashboard.jsp");
	}

}