package services;

import database.DBConnection;
import database.DBQueries;
import models.ForumMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ForumMessagesServices {

    public List<ForumMessage> getAllForumMessages() {
        List<ForumMessage> messages = new ArrayList<>();
        String query = "SELECT fm.forumMessages_id, u.name AS user_name, fm.message, fm.created_at " +
                       "FROM forum_messages fm " +
                       "JOIN users u ON fm.user_id = u.user_id";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ForumMessage message = new ForumMessage();
                message.setId(rs.getInt("forumMessages_id"));
                message.setUserId(rs.getInt("user_name"));
                message.setMessage(rs.getString("message"));
                message.setCreatedAt(rs.getString("created_at"));
                messages.add(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }
    
    public boolean createForumMessage(int userId,String message) {
    	 String query = DBQueries.insertForumMessgaes;

         try (Connection con = DBConnection.connectDB();
              PreparedStatement ps = con.prepareStatement(query)) {
             ps.setInt(1, userId);
             ps.setString(2, message);
             int result = ps.executeUpdate();
             return result > 0;
         } catch (Exception e) {
             e.printStackTrace();
         }
         return false;
    	
    }
}
