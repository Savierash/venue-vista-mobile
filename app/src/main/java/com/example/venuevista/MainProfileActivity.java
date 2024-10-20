package com.example.venuevista;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import models.User;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainProfileActivity extends AppCompatActivity {

    private EditText editUsername, editEmail, editPassword, confirmPassword;
    private Button saveButton, deleteAccountButton, logoutButton;
    private String userId;
    private String currentUsername, currentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize UI components
        editUsername = findViewById(R.id.edit_username);
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        confirmPassword = findViewById(R.id.confirm_password);
        saveButton = findViewById(R.id.save_button);
        deleteAccountButton = findViewById(R.id.delete_account_button);
        logoutButton = findViewById(R.id.logout_button);

        // Set userId (this should be retrieved from your user session)
        userId = getIntent().getStringExtra("USER_ID");

        // Load user data
        loadUserData();

        // Save button click listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserProfile();
            }
        });

        // Delete account button click listener
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUserAccount();
            }
        });

        // Logout button click listener
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    private void loadUserData() {
        new LoadUserDataTask().execute(userId);
    }

    private void updateUserProfile() {
        String newUsername = editUsername.getText().toString();
        String newEmail = editEmail.getText().toString();
        String newPassword = editPassword.getText().toString();
        String confirmPasswordText = confirmPassword.getText().toString();

        // Check if any of the fields have changed
        boolean isUsernameChanged = !newUsername.equals(currentUsername);
        boolean isEmailChanged = !newEmail.equals(currentEmail);
        boolean isPasswordChanged = !newPassword.isEmpty();

        // Validate password fields only if password is being changed
        if (isPasswordChanged && !newPassword.equals(confirmPasswordText)) {
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            return;
        }

        // If no fields are changed, do not proceed with the update
        if (!isUsernameChanged && !isEmailChanged && !isPasswordChanged) {
            Toast.makeText(this, "No changes detected.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Execute the update task, passing only the fields that were changed
        new UpdateProfileTask(isUsernameChanged ? newUsername : null,
                isEmailChanged ? newEmail : null,
                isPasswordChanged ? newPassword : null).execute();
    }

    private void deleteUserAccount() {
        new DeleteAccountTask().execute();
    }

    private void logoutUser() {
        Intent intent = new Intent(MainProfileActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private class LoadUserDataTask extends AsyncTask<String, Void, User> {
        @Override
        protected User doInBackground(String... params) {
            String userId = params[0]; // Get the user ID from parameters
            User user = null; // Initialize the user object to null

            try {
                // Construct the URL for the API endpoint (replace with your actual URL)
                URL url = new URL("http://10.0.2.2/VenueVista2/get_user.php?user_id=" + userId);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json"); // Expecting JSON response
                conn.connect(); // Establish the connection

                // Check the response code
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Read the response
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line); // Append each line to the response
                    }
                    reader.close(); // Close the reader

                    // Parse the JSON response
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    String id = jsonResponse.getString("user_id"); // Get user ID from response
                    String username = jsonResponse.getString("username"); // Get username
                    String email = jsonResponse.getString("email"); // Get email

                    // Create a new User object with the fetched data
                    user = new User(id, username, email);
                }
            } catch (Exception e) {
                e.printStackTrace(); // Print the stack trace for debugging
            }

            return user; // Return the user object or null if not found
        }

        @Override
        protected void onPostExecute(User user) {
            if (user != null) {
                // Update UI elements with the retrieved user data
                currentUsername = user.getUsername();
                currentEmail = user.getEmail();
                editUsername.setText(currentUsername);
                editEmail.setText(currentEmail);
            } else {
                Toast.makeText(MainProfileActivity.this, "Failed to load user data.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class UpdateProfileTask extends AsyncTask<Void, Void, String> {
        private String username, email, password;

        public UpdateProfileTask(String username, String email, String password) {
            this.username = username; // Assign the provided username
            this.email = email; // Assign the provided email
            this.password = password; // Assign the provided password
        }

        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder responseBuilder = new StringBuilder(); // To store the response
            try {
                // Construct the URL for the update endpoint (replace with your actual URL)
                URL url = new URL("http://10.0.2.2/VenueVista2/update_user.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST"); // Set the request method to POST
                conn.setDoOutput(true); // Allow output
                conn.setRequestProperty("Content-Type", "application/json"); // Expecting JSON data

                // Construct the JSON object to send as a request body
                JSONObject jsonRequest = new JSONObject();
                jsonRequest.put("user_id", userId); // Include user ID
                if (username != null) jsonRequest.put("username", username); // Include username if provided
                if (email != null) jsonRequest.put("email", email); // Include email if provided
                if (password != null) jsonRequest.put("password", password); // Include password if provided

                // Send the request
                OutputStream os = conn.getOutputStream();
                os.write(jsonRequest.toString().getBytes("UTF-8")); // Write the JSON data
                os.close(); // Close the output stream

                // Read the response
                int responseCode = conn.getResponseCode(); // Get the response code
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        responseBuilder.append(line); // Append each line to the response
                    }
                    reader.close(); // Close the reader
                }
            } catch (Exception e) {
                e.printStackTrace(); // Print the stack trace for debugging
            }

            return responseBuilder.toString(); // Return the response
        }

        @Override
        protected void onPostExecute(String result) {
            if ("success".equals(result)) {
                Toast.makeText(MainProfileActivity.this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
                loadUserData(); // Reload user data to reflect changes
            } else {
                Toast.makeText(MainProfileActivity.this, "Failed to update profile.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class DeleteAccountTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder responseBuilder = new StringBuilder(); // To store the response
            try {
                // Construct the URL for the delete endpoint (replace with your actual URL)
                URL url = new URL("http://10.0.2.2/VenueVista2/delete_user.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST"); // Set the request method to POST
                conn.setDoOutput(true); // Allow output
                conn.setRequestProperty("Content-Type", "application/json"); // Expecting JSON data

                // Construct the JSON object to send as a request body
                JSONObject jsonRequest = new JSONObject();
                jsonRequest.put("user_id", userId); // Include user ID

                // Send the request
                OutputStream os = conn.getOutputStream();
                os.write(jsonRequest.toString().getBytes("UTF-8")); // Write the JSON data
                os.close(); // Close the output stream

                // Read the response
                int responseCode = conn.getResponseCode(); // Get the response code
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        responseBuilder.append(line); // Append each line to the response
                    }
                    reader.close(); // Close the reader
                }
            } catch (Exception e) {
                e.printStackTrace(); // Print the stack trace for debugging
            }

            return responseBuilder.toString(); // Return the response
        }

        @Override
        protected void onPostExecute(String result) {
            if ("success".equals(result)) {
                Toast.makeText(MainProfileActivity.this, "Account deleted successfully!", Toast.LENGTH_SHORT).show();
                logoutUser(); // Log out the user after account deletion
            } else {
                Toast.makeText(MainProfileActivity.this, "Failed to delete account.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
