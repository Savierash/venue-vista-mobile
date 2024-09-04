package com.example.venuevista;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

public class SignupActivity extends AppCompatActivity {

    private EditText signupUsernameEditText;
    private EditText signupEmailEditText;
    private EditText signupPasswordEditText;
    private EditText signupConfirmPasswordEditText;
    private Button signupButton;
    private ImageView togglepassword;
    private ImageView toggleconfirmpassword;
    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;
    private TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupUsernameEditText = findViewById(R.id.signup_username);
        signupEmailEditText = findViewById(R.id.signup_email);
        signupPasswordEditText = findViewById(R.id.signup_password);
        signupConfirmPasswordEditText = findViewById(R.id.signup_confirm_password);
        signupButton = findViewById(R.id.signup_button);
        togglepassword = findViewById(R.id.toggle_password);
        toggleconfirmpassword = findViewById(R.id.toggle_confirm_password);
        loginLink = findViewById(R.id.login_link);

        // Toggle password visibility for password
        togglepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPasswordVisible) {
                    // Hide password
                    signupPasswordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    togglepassword.setImageResource(R.drawable.baseline_visibility_off_24);
                } else {
                    // Show password
                    signupPasswordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    togglepassword.setImageResource(R.drawable.baseline_visibility_24);
                }
                isPasswordVisible = !isPasswordVisible;
                signupPasswordEditText.setSelection(signupPasswordEditText.getText().length());
            }
        });

        // Toggle password visibility for confirm password
        toggleconfirmpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConfirmPasswordVisible) {
                    // Hide password
                    signupConfirmPasswordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    toggleconfirmpassword.setImageResource(R.drawable.baseline_visibility_off_24);
                } else {
                    // Show password
                    signupConfirmPasswordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    toggleconfirmpassword.setImageResource(R.drawable.baseline_visibility_24);
                }
                isConfirmPasswordVisible = !isConfirmPasswordVisible;
                signupConfirmPasswordEditText.setSelection(signupConfirmPasswordEditText.getText().length());
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
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
}
