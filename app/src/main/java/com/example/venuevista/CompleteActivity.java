package com.example.venuevista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        TextView tvBookingComplete = findViewById(R.id.tvBookingComplete);
        Button btnGoToHome = findViewById(R.id.btnGoToHome); // Initialize the button


        tvBookingComplete.setText("Your booking has been confirmed!");

        btnGoToHome.setOnClickListener(v -> {
            // Handle the action to go back to the homepage
            Intent intent = new Intent(CompleteActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
