package controller;

import models.User;
import services.PasswordUtils;
import services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserServices userServices = new UserServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data from the request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        
        response.setContentType("text/html");
        response.getWriter().println("<h3>Received Form Data:</h3>");
        response.getWriter().println("<p>Name: " + name + "</p>");
        response.getWriter().println("<p>Email: " + email + "</p>");
        response.getWriter().println("<p>Gender: " + gender + "</p>");
        response.getWriter().println("<p>Password: " + password + "</p>");
        response.getWriter().println("<p>Role: " + role + "</p>");

        // Validate required fields
        if (name == null || email == null || gender == null || password == null || role == null) {
            response.getWriter().println("<h3>All fields are required! <a href='register.jsp'>Try again</a></h3>");
            return;
        }
        
        password=PasswordUtils.encryptPassword(password);

        // Create a new User object
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setGender(gender);
        user.setPassword(password);
        user.setRole(role);

        // Call the UserServices to register the user
        boolean isRegistered = userServices.registerUser(user);

        // Handle the response
        response.setContentType("text/html");
        if (isRegistered) {
            // Send an alert message and redirect to login.html
            response.getWriter().println("<script type='text/javascript'>");
            response.getWriter().println("alert('Registration Successful!');");
            response.getWriter().println("window.location.href = 'index.html';");
            response.getWriter().println("</script>");
        } else {
            response.getWriter().println("<h3>Registration failed. <a href='registration.html'>Try again</a></h3>");
        }
    }
}
