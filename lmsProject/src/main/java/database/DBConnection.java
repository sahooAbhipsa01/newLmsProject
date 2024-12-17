package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    
    private static final String url = "jdbc:mysql://localhost:3306/lms";
    private static final String user = "root";
    private static final String password = "Abhipsa@2002";

    
    public static Connection connectDB() {
        Connection connection = null;
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

           
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connection established.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: Unable to connect to the database.");
            e.printStackTrace();
        }
        return connection;
    }
}
