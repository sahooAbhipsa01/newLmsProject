<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard|Trainer</title>
    <link rel="stylesheet" href="css/trainerstyles.css">
</head>
<body>
    <div class="container">
        <!-- Left Section -->
        <div class="left-section">
            <h1 class="heading-underline">Welcome, <span id="trainerName">${sessionScope.user.name}</span>!</h1>
            <p>Manage your courses, grade your students, and engage in discussions here.</p>
        </div>

        <!-- Right Section -->
        <div class="right-section">
            <div class="form-container">
                <!-- Create New Course -->
                <h2>Create a New Course</h2>
               <form id="createCourseForm" action="createCourse" method="POST">
                    <div>
                        <label for="courseName">Course Name:</label>
                        <input type="text" id="courseName" name="courseName" placeholder="Enter course name" required>
                    </div>
                    <div>
                        <label for="courseDescription">Description:</label>
                        <textarea id="courseDescription" name="courseDescription" placeholder="Enter course description" required></textarea>
                    </div>
                    <button type="submit">Create Course</button>
                </form>
                
                <!-- Display Messages -->
                <c:if test="${not empty param.message}">
                    <div class="message">
                        <p>${param.message}</p>
                    </div>
                </c:if>

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
                        <!-- Example Row -->
                        <tr>
                            <td>John Doe</td>
                            <td>Mathematics 101</td>
                            <td>A</td>
                            <td>
                                <input type="text" placeholder="Enter grade">
                                <button type="submit">Update</button>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <!-- Manage Discussion Forum -->
                <h2>Discussion Forum</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Discussion Topic</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody id="discussionTopics">
                        <!-- Example Row -->
                        <tr>
                            <td>General Questions</td>
                            <td>
                                <button>View Discussion</button>
                                <button>Delete</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <form id="addDiscussionForm">
                    <label for="newTopic">Add New Topic:</label>
                    <input type="text" id="newTopic" placeholder="Enter topic">
                    <button type="submit">Add</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
