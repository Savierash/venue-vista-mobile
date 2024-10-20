package com.example.venuevista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton profileButton;
    private ImageButton homeIcon;
    private ImageButton calendarIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileButton = findViewById(R.id.profile_button);  // Initialize the profileButton
        homeIcon = findViewById(R.id.home_icon);
        calendarIcon = findViewById(R.id.calendar_icon);

        // Add function to edit profile
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Edit profile button click
                // ADD more activity here optional
            }
        });


        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,HomeActivity.class);
                startActivity(intent);

            }
        });

        calendarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,BookingActivity.class);
                startActivity(intent);

            }
        });
    }
}
