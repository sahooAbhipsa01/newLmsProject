package models;

import java.time.Duration;
import java.time.LocalDateTime;

public class ForumHistory {
	
    private String username;
    private String message;
    private String avatarInitial;  // First letter of username
    private String timeAgo;        // Computed "time ago"
    private LocalDateTime createdAt; // Creation timestamp of the message
    
    public ForumHistory() {
        super();
    }

    @Override
	public String toString() {
		return "ForumHistory [username=" + username + ", message=" + message + ", avatarInitial=" + avatarInitial
				+ ", timeAgo=" + timeAgo + ", createdAt=" + createdAt + "]";
	}

	// Constructor with creation timestamp
    public ForumHistory(String username, String message, String avatarInitial, LocalDateTime createdAt) {
        this.username = username;
        this.message = message;
        this.avatarInitial = avatarInitial;
        this.createdAt = createdAt;
        this.timeAgo = calculateTimeAgo(createdAt);  // Set the time ago
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAvatarInitial() {
        return avatarInitial;
    }

    public void setAvatarInitial(String avatarInitial) {
        this.avatarInitial = avatarInitial;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        this.timeAgo = calculateTimeAgo(createdAt);  // Update timeAgo whenever createdAt is set
    }

    // Method to calculate time ago
    private String calculateTimeAgo(LocalDateTime creationTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(creationTime, currentTime);
        
        long seconds = duration.getSeconds();
        if (seconds < 60) {
            return seconds + " seconds ago";
        }
        long minutes = duration.toMinutes();
        if (minutes < 60) {
            return minutes + " minutes ago";
        }
        long hours = duration.toHours();
        if (hours < 24) {
            return hours + " hours ago";
        }
        long days = duration.toDays();
        if (days < 7) {
            return days + " days ago";
        }
        long weeks = days / 7;
        if (weeks < 4) {
            return weeks + " weeks ago";
        }
        long months = weeks / 4;
        if (months < 12) {
            return months + " months ago";
        }
        long years = months / 12;
        return years + " years ago";
    }
    

}