package com.example.venuevista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import network.ApiService; // Ensure this is imported correctly

public class ProfileActivity extends AppCompatActivity {

    private ImageButton profileButton;
    private ImageButton homeIcon;
    private ImageButton calendarIcon;
    private Button saveButton;
    private Button deleteAccountButton;

    private EditText editUsername;
    private EditText editEmail;
    private EditText editPassword;
    private EditText confirmPassword;

    private String userId; // Initialize this with the actual user ID.

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileButton = findViewById(R.id.profile_button);
        homeIcon = findViewById(R.id.home_icon);
        calendarIcon = findViewById(R.id.calendar_icon);
        saveButton = findViewById(R.id.save_button);
        deleteAccountButton = findViewById(R.id.delete_account_button);

        editUsername = findViewById(R.id.edit_username);
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        confirmPassword = findViewById(R.id.confirm_password);

        // Initialize the userId (You may want to get this from SharedPreferences or passed Intent)
        userId = "1"; // Example user ID; replace this with actual user ID.

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/") // Replace with your actual base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        // Navigate to HomeActivity
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // Navigate to BookingActivity
        calendarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, BookingActivity.class);
                startActivity(intent);
            }
        });

        // Save Changes button action
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String username = editUsername.getText().toString();
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                String confirmPasswordInput = confirmPassword.getText().toString();

                // Validate password match
                if (!password.equals(confirmPasswordInput)) {
                    Toast.makeText(ProfileActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Prepare JSON object for the request
                JSONObject profileData = new JSONObject();
                try {
                    profileData.put("user_id", userId);
                    profileData.put("username", username);
                    profileData.put("email", email);
                    profileData.put("password", password);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Send update request to the server
                sendUpdateRequest(profileData);
            }
        });

        // Delete Account button action
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Prepare JSON object for delete request
                JSONObject deleteRequest = new JSONObject();
                try {
                    deleteRequest.put("user_id", userId); // Ensure you pass the correct user ID
                } catch (Exception e) {
                    e.printStackTrace();
                }
                deleteAccount(deleteRequest);
            }
        });

        // Optional: Add function to edit profile
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this, "Edit Profile clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendUpdateRequest(JSONObject profileData) {
        Call<JSONObject> call = apiService.updateProfile(profileData);
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(ProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this, "Error updating profile", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteAccount(JSONObject deleteRequest) {
        Call<JSONObject> call = apiService.deleteAccount(deleteRequest);
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(ProfileActivity.this, "Account Deleted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();  // Close current activity
                } else {
                    Toast.makeText(ProfileActivity.this, "Error deleting account", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
