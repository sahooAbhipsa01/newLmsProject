<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ page import="models.Courses"%>
<%@ page import="models.Grade"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dashboard|Student</title>
<link rel="stylesheet" href="css/studentstyles.css">
</head>
<body>
	<div class="container">
		<!-- Left Section -->
		<div class="left-section">
			<h1 class="heading-underline">
				Hello, <span id="studentName">${sessionScope.user.name}</span>!
			</h1>
			<p>Welcome to your dashboard. Here you can view your courses,
				grades, and participate in discussions.</p>
		</div>

		<!-- Right Section -->
		<div class="right-section">
			<div class="form-container">
				<!-- Enrolled Courses -->
				<h2>Your Courses</h2>
				<table>
					<thead>
						<tr>
							<th>Course Name</th>
							<th>Instructor</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody id="enrolledCourses">
						<!-- Example Row -->
						<%
						List<Courses> enrolledCourses = (List<Courses>) session.getAttribute("enrolledCoursesList");
						if (enrolledCourses != null) {
						for (Courses course : enrolledCourses) {
						%>
						<tr>
							<td><%=course.getName()%></td>
							<td><%=course.getTrainerName()%></td>
							<td>Enrolled</td>
							<td><button disabled>Enrolled</button></td>
						</tr>
						<%
						}
						}else	{
							
						%>
						<tr>
						<td colspan="4">No enrolled courses found.</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>

				<!-- All Courses -->
				<h2>All Courses</h2>
				<table>
					<thead>
						<tr>
							<th>Course Name</th>
							<th>Instructor</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody id="suggestedCourses">
						<!-- Example Row -->
						<%
						List<Courses> allCourses = (List<Courses>) session.getAttribute("allCoursesList");

						// Create a set of course IDs the student is already enrolled in
						Set<Integer> enrolledCourseIds = new HashSet<>();
						if (enrolledCourses != null) {
							for (Courses enrolledCourse : enrolledCourses) {
								enrolledCourseIds.add(enrolledCourse.getId());
							}
						}
						for (Courses cr : allCourses) {
							boolean isEnrolled = enrolledCourseIds.contains(cr.getId());
						%>
						<tr>
							<td><%=cr.getName()%></td>
							<td><%=cr.getTrainerName()%></td>
							<td>
								<%
								if (isEnrolled) {
								%>
								<button disabled>Enrolled</button> <%
 } else {
 %>
								<form action="enroll" method="post">
									<input type="hidden" name="courseId" value="<%=cr.getId()%>">
									<input type="hidden" name="studentId" value="${sessionScope.loggedInUserId }">
									
									<input type="submit" value="Enroll">
								</form> <%
 }
 %>
							</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>

				<!-- Grades -->
				<h2>Your Grades</h2>
				<table class="table-grades">
					<thead>
						<tr>
							<th>Course</th>
							<th>Grade</th>
						</tr>
					</thead>
					<tbody id="grades">
						<%
						List<Grade> gradesList = (List<Grade>) session.getAttribute("gradesList");
						for (Grade grade : gradesList) {
							System.out.println(grade);
						%>
						<tr>
							<td><%=grade.getCourseName()%></td>
							<td><%=grade.getGrade()%></td>
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
							<th>Action</th>
						</tr>
					</thead>
					<tbody id="discussionForum">
						<!-- Example Row -->
						<tr>
							<td>General Questions</td>
							<td>
								<button>Join Discussion</button>
								
							</td>
						</tr>

						<!-- Input Row -->
						<tr>
						<form action="forum"  method="post">
							<td colspan="2">
							<input type="text" name="discussionTopic"
							placeholder="Enter your question here"
							style="width:80%;
							height=100px;
							padding=10px;
							margin:10px 0;border:2px solid #ccc;
							border-radius:5px;"required>
							<input type="hidden" name="userId" value="${sessionScope.loggedInUserId }">
							
							<button type="submit">Post</button>
							
							</td>
							</form>
							</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
