package services;

import database.DBQueries;
import database.DBConnection;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServices {
    public boolean registerUser(User user) {
        try (Connection connection = DBConnection.connectDB()) {
            PreparedStatement ps = connection.prepareStatement(DBQueries.insertUser);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getGender());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());

            int result = ps.executeUpdate();
            System.out.println("Rows affected: " + result);
            return result > 0;
        } catch (Exception e) {
            System.err.println("Error during user registration: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    public int login(String email,String password) {
    	try (Connection connection = DBConnection.connectDB()){
    			PreparedStatement ps=connection.prepareStatement(DBQueries.getUserByUserEmail);
    			ps.setString(1, email);
    			ResultSet resultSet=ps.executeQuery();
    			if(resultSet.next()) {
    				String storedPassword=resultSet.getString("password");
    				
    				int currentUserId=resultSet.getInt("user_id");
    				
    				if(PasswordUtils.verifyPassword(password, storedPassword))
    					return currentUserId;
    			}
    		
    		
    	}catch(SQLException e) {
    		System.err.println(e.getMessage());
    	}
    	return -1;
    	
    }
    
    public User getUserById(int userId) {
    	User user=new User();
    	try(Connection con=DBConnection.connectDB();
    			PreparedStatement ps=con.prepareStatement(DBQueries.getUserByUserId);
    			){
    		ps.setInt(1, userId);
    		ResultSet resultSet=ps.executeQuery();
    		
    		if (resultSet.next()) {
    			String name=resultSet.getString("name");
    			String role=resultSet.getString("role");
    			String email=resultSet.getString("email");
    			user.setUserId(userId);
    			user.setName(name);
    			user.setRole(role);
    			user.setEmail(email);
    			
    			
    			
    		}
    		
    	}catch(SQLException e) {
    		System.err.println(e.getMessage());
    	}
    	System.out.println(user);
    	return user;
    	
    }
    public static void main(String[] args) {
        UserServices userServices = new UserServices();

        // Create a sample user for testing
        User testUser = new User();
        testUser.setName("John Doe");
        testUser.setEmail("john.doe@example.com");
        testUser.setGender("M");
        testUser.setPassword("password123");
        testUser.setRole("student");

        // Test the registerUser method
        boolean isRegistered = userServices.registerUser(testUser);
        if (isRegistered) {
            System.out.println("User registration successful!");
        } else {
            System.out.println("User registration failed.");
        }
        
    }
}
