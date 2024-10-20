package models;

public class LoginResponse {
    private boolean success;
    private String message;

    // Optionally, include user data if needed
    private User user;

    public LoginResponse(boolean success, String message, User user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
