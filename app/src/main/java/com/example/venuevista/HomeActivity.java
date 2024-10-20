package com.example.venuevista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private EditText searchBar;
    private ImageButton profileButton;
    private ImageButton homeIcon;
    private ImageButton calendarIcon;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ImageView image6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize all the views
        searchBar = findViewById(R.id.search_bar);
        profileButton = findViewById(R.id.profile_button);
        homeIcon = findViewById(R.id.home_icon);
        calendarIcon = findViewById(R.id.calendar_icon);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);


        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Actions to click images
              Intent intent = new Intent(HomeActivity.this,SelectPackageActivity.class);
              startActivity(intent);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Actions to click images
                Intent intent = new Intent(HomeActivity.this,SelectPackage2Activity.class);
                startActivity(intent);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Actions to click images
                Intent intent = new Intent(HomeActivity.this,SelectPackage3Activity.class);
                startActivity(intent);
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Actions to click images
                Intent intent = new Intent(HomeActivity.this,SelectPackage4Activity.class);
                startActivity(intent);
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Actions to click images
                Intent intent = new Intent(HomeActivity.this,SelectPackage5Activity.class);
                startActivity(intent);
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Actions to click images
                Intent intent = new Intent(HomeActivity.this,SelectPackage6Activity.class);
                startActivity(intent);
            }
        });


        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle profile button click
                Intent intent = new Intent(HomeActivity.this, MainProfileActivity.class);
                startActivity(intent);
                finish();

            }
        });


        calendarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,BookingActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
