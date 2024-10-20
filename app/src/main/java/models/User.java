package models; // Make sure this matches your package structure

public class User {
    private String username;
    private String email;
    private String password; // This should ideally not be returned for security reasons

    // Constructor
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    // Optionally, you can add setters if needed
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // You may not want to include a setter for the password
}
