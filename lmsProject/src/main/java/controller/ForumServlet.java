package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.EnrollmentServices;
import services.ForumMessagesServices;


@WebServlet("/forum")
public class ForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private final ForumMessagesServices forumMessageService = new ForumMessagesServices();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message=request.getParameter("discussionTopic");
		int userId=Integer.parseInt(request.getParameter("userId"));
		
		boolean isMessaged=forumMessageService.createForumMessage(userId, message);
		if(isMessaged) {
			response.sendRedirect(message);
			
		}
		
		
		
		
		
		
	}

}
