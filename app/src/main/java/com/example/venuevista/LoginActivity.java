package com.example.venuevista;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView signupLink;
    private ImageView togglepassword;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initializing views
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        signupLink = findViewById(R.id.signup_link);
        togglepassword = findViewById(R.id.toggle_password);

        // Toggle password visibility
        togglepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPasswordVisible) {
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    togglepassword.setImageResource(R.drawable.baseline_visibility_off_24);
                } else {
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    togglepassword.setImageResource(R.drawable.baseline_visibility_24);
                }
                isPasswordVisible = !isPasswordVisible;
                passwordEditText.setSelection(passwordEditText.getText().length());
            }
        });

        // Handle login button click
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Input validation
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Both fields are required!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Call AsyncTask to perform login
                new LoginTask().execute(username, password);
            }
        });

        // Navigate to the Signup page
        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private class LoginTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String username = params[0];
            String password = params[1];

            try {
                URL url = new URL("http://10.0.2.2/VenueVista2/login.php"); // Adjust the URL as needed
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);

                // Create JSON object for the login data
                String jsonInputString = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);

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
                    return "Error: " + responseCode;
                }

                return response.toString(); // Return the response from the server
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Handle the result returned by the server
            try {
                JSONObject jsonResponse = new JSONObject(result);
                boolean success = jsonResponse.getBoolean("success");
                String message = jsonResponse.getString("message");

                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();

                // Optional: Navigate to the HomeActivity if login is successful
                if (success) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    // finish(); // Comment out this line to keep the app open
                }
            } catch (JSONException e) {
                Toast.makeText(LoginActivity.this, "Error parsing response: " + e.getMessage(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(LoginActivity.this, "Unexpected error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
