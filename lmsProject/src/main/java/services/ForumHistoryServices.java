package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;
import models.ForumHistory;

public class ForumHistoryServices {
	
	public List<ForumHistory> getHistoryByTopicId(int topicId){
		String query = "SELECT u.name, fh.message, fh.created_at FROM forum_history fh INNER JOIN forum_messages fm ON fh.forum_topic_id = fm.forumMessages_id INNER JOIN users u ON fh.user_id = u.user_id WHERE fm.forumMessages_id = ? ORDER BY fh.created_at ASC";
		
		List<ForumHistory> forumHistories = new ArrayList<>();
		
		try (Connection con = DBConnection.connectDB();
	             PreparedStatement ps = con.prepareStatement(query)) {
			
			ps.setInt(1, topicId);
			ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
            	ForumHistory history = new ForumHistory();
            	
                String username = resultSet.getString("name");
                String message = resultSet.getString("message");
                LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                String avatarInitial = username.substring(0, 1).toUpperCase();
                
                history.setUsername(username);
                history.setMessage(message);
                history.setCreatedAt(createdAt);
                history.setAvatarInitial(avatarInitial);

                forumHistories.add(history);
            }
			
		} catch (Exception e) {
            e.printStackTrace();
        }
		
		return forumHistories;
		
	}
	
    public boolean createForumHistory(int topicId, int userId, String msg) {
    	
   	 String query = "INSERT INTO forum_history(forum_topic_id, user_id, message) VALUES(?,?,?)";

        try (Connection con = DBConnection.connectDB();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, topicId);
            ps.setInt(2, userId);
            ps.setString(3, msg);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;

	}
}