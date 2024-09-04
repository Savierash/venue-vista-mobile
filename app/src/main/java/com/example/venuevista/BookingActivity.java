package com.example.venuevista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {

    private ImageView profileButton;
    private ImageView homeIcon;
    private ImageView calendarIcon;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_booking);

        profileButton = findViewById(R.id.profile_button);
        homeIcon = findViewById(R.id.home_icon);
        calendarIcon = findViewById(R.id.calendar_icon);

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();  // Closes the current activity and returns to the previous one
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle profile button click
                Intent intent = new Intent(BookingActivity.this, MainProfileActivity.class);
                startActivity(intent);
            }
        });


        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });


    }
}
