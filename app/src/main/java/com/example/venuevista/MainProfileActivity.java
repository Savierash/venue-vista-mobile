package com.example.venuevista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainProfileActivity extends AppCompatActivity {

    private ImageButton proceedButton;
    private ImageButton homeIcon;
    private ImageButton calendarIcon;
    private FrameLayout box3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_main_profile);

        proceedButton = findViewById(R.id.proceed_button);
        homeIcon = findViewById(R.id.home_icon);
        calendarIcon = findViewById(R.id.calendar_icon);
        box3 = findViewById(R.id.box3);



        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainProfileActivity.this,ProfileActivity.class);
                startActivity(intent);

            }
        });


        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainProfileActivity.this,HomeActivity.class);
                startActivity(intent);

            }
        });

        calendarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainProfileActivity.this,BookingActivity.class);
                startActivity(intent);

            }
        });

        box3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show a Toast message confirming logout
                Toast.makeText(MainProfileActivity.this, "You have logged out", Toast.LENGTH_SHORT).show();

                // Redirect to login activity
                Intent intent = new Intent(MainProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Finish the current activity so the user can't go back to it
            }
        });


    }
}
