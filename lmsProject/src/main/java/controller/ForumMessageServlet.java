package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.ForumHistory;
import services.ForumHistoryServices;
import services.ForumMessagesServices;

/**
 * Servlet implementation class ForumMessageServlet
 */
@WebServlet("/forumMessageServlet")
public class ForumMessageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final ForumMessagesServices forumMessageService = new ForumMessagesServices();
	private final ForumHistoryServices forumHistoryServices = new ForumHistoryServices();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = request.getParameter("reply");
		int topicId = Integer.parseInt(request.getParameter("topicId"));
		int currentUserId = Integer.parseInt(request.getParameter("userId"));
		
		forumHistoryServices.createForumHistory(topicId, currentUserId, message);
		
		List<ForumHistory> history = forumHistoryServices.getHistoryByTopicId(topicId);
		
		HttpSession session = request.getSession();
		session.setAttribute("allHistory", history);
		
		
		String topicName = forumMessageService.getTopicNameById(topicId);
		
		response.sendRedirect("discussion_history.jsp?topicId=" + topicId + "&topic=" + topicName);
		
	}

}