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
import models.ForumMessage;
import services.EnrollmentServices;
import services.ForumHistoryServices;
import services.ForumMessagesServices;

@WebServlet("/forum")
public class ForumServlet extends HttpServlet {

	private final ForumMessagesServices forumMessageService = new ForumMessagesServices();
	private final ForumHistoryServices forumHistoryServices = new ForumHistoryServices();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int topicId = Integer.parseInt(request.getParameter("topic_id"));
		List<ForumHistory> history = forumHistoryServices.getHistoryByTopicId(topicId);

		HttpSession session = request.getSession();
		session.setAttribute("allHistory", history);

		String topicName = forumMessageService.getTopicNameById(topicId);

		response.sendRedirect("discussion_history.jsp?topicId=" + topicId + "&topic=" + topicName);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = request.getParameter("discussionTopic");
		int userId = Integer.parseInt(request.getParameter("userId"));

		boolean isMessaged = forumMessageService.createForumMessage(userId, message);
		if (isMessaged) {
			// Set session attributes for the logged-in user
			HttpSession session = request.getSession();
			// Fetch all forum messages
			List<ForumMessage> allForumTopics = forumMessageService.getAllForumMessages();
			session.setAttribute("allForumMessages", allForumTopics);
			response.sendRedirect("student_dashboard.jsp");

		}

	}

}