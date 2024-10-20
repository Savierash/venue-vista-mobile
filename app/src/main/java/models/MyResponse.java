package models; // Adjust this to your actual package structure

public class MyResponse {
    private String message; // Example field, add fields according to your API response

    public MyResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Optionally, you can add other fields and their getters/setters
}
