package com.example.venuevista;

import org.json.JSONObject;
import org.json.JSONException;
import android.util.Log;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.method.PasswordTransformationMethod;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignupActivity extends AppCompatActivity {

    private EditText signupUsernameEditText;
    private EditText signupEmailEditText;
    private EditText signupPasswordEditText;
    private EditText signupConfirmPasswordEditText;
    private Button signupButton;
    private ImageView togglePassword;
    private ImageView toggleConfirmPassword;
    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;
    private TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initializing views
        signupUsernameEditText = findViewById(R.id.signup_username);
        signupEmailEditText = findViewById(R.id.signup_email);
        signupPasswordEditText = findViewById(R.id.signup_password);
        signupConfirmPasswordEditText = findViewById(R.id.signup_confirm_password);
        signupButton = findViewById(R.id.signup_button);
        togglePassword = findViewById(R.id.toggle_password);
        toggleConfirmPassword = findViewById(R.id.toggle_confirm_password);
        loginLink = findViewById(R.id.login_link);

        // Toggle password visibility for password
        togglePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPasswordVisible) {
                    signupPasswordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    togglePassword.setImageResource(R.drawable.baseline_visibility_off_24);
                } else {
                    signupPasswordEditText.setTransformationMethod(null); // Show text
                    togglePassword.setImageResource(R.drawable.baseline_visibility_24);
                }
                isPasswordVisible = !isPasswordVisible;
                signupPasswordEditText.setSelection(signupPasswordEditText.getText().length());
            }
        });

        // Toggle password visibility for confirm password
        toggleConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConfirmPasswordVisible) {
                    signupConfirmPasswordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    toggleConfirmPassword.setImageResource(R.drawable.baseline_visibility_off_24);
                } else {
                    signupConfirmPasswordEditText.setTransformationMethod(null); // Show text
                    toggleConfirmPassword.setImageResource(R.drawable.baseline_visibility_24);
                }
                isConfirmPasswordVisible = !isConfirmPasswordVisible;
                signupConfirmPasswordEditText.setSelection(signupConfirmPasswordEditText.getText().length());
            }
        });

        // Handle signup button click
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = signupUsernameEditText.getText().toString().trim();
                String email = signupEmailEditText.getText().toString().trim();
                String password = signupPasswordEditText.getText().toString().trim();
                String confirmPassword = signupConfirmPasswordEditText.getText().toString().trim();

                // Basic input validation
                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Call AsyncTask to perform signup
                new SignupTask().execute(username, email, password);
            }
        });

        // Redirect to login screen
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private class SignupTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String username = params[0];
            String email = params[1];
            String password = params[2];

            try {
                // Use 10.0.2.2 for the emulator to access localhost
                URL url = new URL("http://10.0.2.2/VenueVista2/signup.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);

                // Create JSON object for the signup data
                String jsonInputString = String.format("{\"username\":\"%s\", \"email\":\"%s\", \"password\":\"%s\"}", username, email, password);

                // Write JSON data to output stream
                try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream())) {
                    writer.write(jsonInputString);
                }

                // Handle the response
                int responseCode = conn.getResponseCode();
                StringBuilder response = new StringBuilder();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                } else {
                    return "Signup failed: " + responseCode;
                }

                return response.toString(); // Return the response from the server
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Here you can handle the result returned by the server
            try {
                Log.d("SignupResult", "Result: " + result);
                JSONObject jsonResponse = new JSONObject(result);
                boolean success = jsonResponse.getBoolean("success");
                String message = jsonResponse.getString("message");

                Toast.makeText(SignupActivity.this, message, Toast.LENGTH_LONG).show();

                // Optional: Navigate to the HomeActivity if signup is successful
                if (success) {
                    Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            } catch (JSONException e) {
                Toast.makeText(SignupActivity.this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
