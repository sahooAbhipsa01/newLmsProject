package models;

public class User {
    @Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", gender=" + gender + ", password="
				+ password + ", role=" + role + "]";
	}

	private int userId; // Updated from "id" to "userId"
    private String name;
    private String email;
    private String gender;
    private String password;
    private String role;

    // Default Constructor
    public User() {}

    // Parameterized Constructor
    public User(int userId, String name, String email, String gender, String password, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
