<%@page import="models.ForumHistory"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Discussion Forum</title>
    <style>
		.username {
		    font-weight: bold; /* Bold username */
		    display: inline;   /* Ensures it's inline */
		}

		.timestamp {
		    font-size: 12px;   /* Smaller font */
		    color: #333;       /* Black text color */
		    margin-left: 5px;  /* Space between username and timestamp */
		    display: inline;   /* Ensures it's inline */
		}


		.comment-section {
		    max-height: 400px; /* Fixed height for the comment area */
		    overflow-y: auto; /* Adds vertical scrollbar when content overflows */
		    border: 1px solid #ddd;
		    border-radius: 8px;
		    background-color: #ffffff;
		    padding: 10px;
		}

        /* General Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        /* Page Body */
        body {
            background-color: #326374;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        /* Discussion Container */
        .discussion-container {
            width: 700px;
            background-color: #f0f4f8; /* Light gray-blue */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        /* Discussion Topic */
        .discussion-topic {
            margin-bottom: 20px;
            text-align: center;
        }

        .discussion-topic h1 {
            font-size: 24px;
            color: #333;
            border-bottom: 2px solid #ccc;
            padding-bottom: 10px;
        }

        /* Comment Section */
        .comment {
            display: flex;
            margin-bottom: 15px;
            align-items: flex-start;
        }

        /* Circular Avatar */
        .user-avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            background-color: #2f788f; /* Avatar color */
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 18px;
            font-weight: bold;
            margin-right: 10px;
            flex-shrink: 0;
        }

        /* Comment Content */
        .comment-content {
            background-color: #ffffff;
            border: 1px solid #ddd;
            padding: 10px;
            border-radius: 8px;
            width: 100%;
        }


        .comment-text {
            font-size: 14px;
            color: #555;
            line-height: 1.5;
			padding: 10px;
        }

        /* Reply Form */
        .reply-form {
            margin-top: 20px;
        }

        .reply-form input[type="text"] {
            width: calc(100% - 80px);
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }

        .reply-form button {
            background-color: #2f788f;
            color: white;
            border: none;
            border-radius: 4px;
            padding: 10px 15px;
            margin-left: 10px;
            cursor: pointer;
            font-size: 14px;
            transition: background-color 0.3s ease;
        }

        .reply-form button:hover {
            background-color: #246370;
        }
    </style>
</head>
<body>
    <div class="discussion-container">
        <!-- Topic Heading -->
        <div class="discussion-topic">
            <h1>Discussion on: <span id="topic-title"> <%= request.getParameter("topic") %> </span></h1>
        </div>

        <!-- Comments -->
        <div class="comment-section">
			<!-- Comment 1 -->
			<%
			List<ForumHistory> history = (List<ForumHistory>) session.getAttribute("allHistory"); 
			for(ForumHistory fh : history){
			%>
			<div class="comment">
			    <div class="user-avatar"> <%= fh.getAvatarInitial() %> </div>
			    <div class="comment-content">
			        <span class="username"> <%= fh.getUsername() %> </span>
			        <span class="timestamp"> • <%= fh.getTimeAgo() %> </span>
			        <p class="comment-text"> <%= fh.getMessage() %> </p>
			    </div>
			</div>
			<%} %>

			

        </div>

        <!-- Reply Form -->
        <div class="reply-form">
            <form action="forumMessageServlet" method="post">
                <input type="text" name="reply" placeholder="Write your reply..." required>
                <input type="hidden" name="topicId" value="<%= request.getParameter("topicId") %>">
                <input type="hidden" name="userId" value="${sessionScope.loggedInUserId}">
                <button type="submit">Post</button>
            </form>
        </div>
    </div>
</body>
</html>