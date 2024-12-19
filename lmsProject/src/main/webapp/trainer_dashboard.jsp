<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="models.ForumMessage"%>
<%@page import="java.util.*"%>
<%@ page import="models.Courses"%>
<%@ page import="models.Grade"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dashboard | Trainer</title>
<link rel="stylesheet" href="css/trainerstyles.css">
</head>
<body>
	<div class="container">
		<!-- Left Section -->
		<div class="left-section">
			<h1 class="heading-underline">
				Welcome, <span id="trainerName">${sessionScope.user.name}</span>!
			</h1>
			<p>Manage your courses, grade your students, and engage in
				discussions here.</p>
		</div>

		<!-- Right Section -->
		<div class="right-section">
			<div class="form-container">
				<!-- Create New Course -->
				<h2>Create a New Course</h2>
				<form id="createCourseForm" action="createCourse" method="POST">
					<div>
						<label for="courseName">Course Name:</label> <input type="text"
							id="courseName" name="courseName" placeholder="Enter course name"
							required>
					</div>
					<div>
						<label for="courseDescription">Description:</label>
						<textarea id="courseDescription" name="courseDescription"
							placeholder="Enter course description" required></textarea>
					</div>
					<button class="submit-btn" type="submit">Create Course</button>
				</form>

				

				<!-- Grading Section -->
				<h2>Grade Students</h2>
					<table>
						<thead>
							<tr>
								<th>Student Name</th>
								<th>Course</th>
								<th>Current Grade</th>
								<th>Update Grade</th>
							</tr>
						</thead>
						<tbody id="gradingTable">
							<!-- Dynamically generate rows for each enrolled student -->
							<%
						List<Grade> gradesList = (List<Grade>) session.getAttribute("gradesList");
						for (Grade grade : gradesList) {
							
						%>
						<tr>
							<td><%=grade.getStudentName()%></td>
							<td><%=grade.getCourseName()%></td>
							<td><%=grade.getCurrentGrade()%></td>
							<td> 
							<form action="gradeStudent" method="post">
							<input placeholder="Enter New Grade" style="width: 140px" type="text" name="newGrade"/>
							<input type="hidden" name="currentUserId" value="${sessionScope.loggedInUserId }"> 
							<input type="hidden" name="enroll_id" value="<%= grade.getEnrollementId()%>">
							<button type="submit">Update</button>
							</form>
							</td>
						</tr>
						<%
						}
						%>
						</tbody>
					</table>


				<!-- Discussion Forum -->
				<h2>Discussion Forum</h2>
				<table>
					<thead>
						<tr>
							<th>Discussion Topic</th>
							<th>Started By</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody id="discussionForum">
						<!-- Example Row -->
						<%
						List<ForumMessage> allMessages = (List<ForumMessage>) session.getAttribute("allForumMessages");
						
						for(ForumMessage fm : allMessages){
						%>
						<tr>
							<td> <%=fm.getMessage() %> </td>
							<td> <%=fm.getUserName() %> </td>
							<td>
								<button onclick="window.location.href='forum?topic_id=<%=fm.getId() %>'">Join Discussion</button>

							</td>
						</tr>
						<%} %>
					</tbody>
				</table>
				<!-- Input Row -->

				<form method="post" action="forum">
					<input type="text" name="discussionTopic"
						placeholder="Enter your question here"
						style="width: 80%; height: 25px; padding: 10px; margin: 10px 0; border: 2px solid #ccc; border-radius: 5px;"
						required> 
						<input type="hidden" name="userId" value="${sessionScope.loggedInUserId}"> 
						<input class="submit-btn" type="submit" value="Post">
				</form>



			</div>
		</div>
	</div>
</body>
</html>