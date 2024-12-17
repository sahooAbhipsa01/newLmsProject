<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="models.ForumMessage" %>
<%@ page import="models.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Discussion Forum</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Discussion Forum</h1>
        <h3>Welcome, <%= ((User) session.getAttribute("user")).getName() %>!</h3>
        
        <!-- Messages Section -->
        <div class="messages-section">
            <h2>All Messages</h2>
            <table border="1" style="width: 100%; border-collapse: collapse;">
                <thead>
                    <tr>
                        <th>User</th>
                        <th>Message</th>
                        <th>Posted At</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<ForumMessage> messages = (List<ForumMessage>) request.getAttribute("forumMessages");
                        if (messages != null && !messages.isEmpty()) {
                            for (ForumMessage message : messages) {
                    %>
                    <tr>
                        <td><%= message.getUserName() %></td>
                        <td><%= message.getMessage() %></td>
                        <td><%= message.getCreatedAt() %></td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="3">No messages yet. Be the first to post!</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>

        <!-- Post a New Message -->
        <div class="post-message-section">
            <h2>Post a New Message</h2>
            <form action="PostDiscussionServlet" method="post">
                <textarea name="message" rows="5" cols="50" placeholder="Enter your message here..." required></textarea>
                <br>
                <input type="submit" value="Post">
            </form>
        </div>
    </div>
</body>
</html>
