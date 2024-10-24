package com.example.venuevista;


import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.venuevista.SelectPackage4Activity;
import com.example.venuevista.SelectPackage5Activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private EditText searchBar;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ImageView image6;

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize all the views
        searchBar = findViewById(R.id.search_bar);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.navigation_profile) {
                    startActivity(new Intent(getApplicationContext(), MainProfileActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (id == R.id.navigation_home) {
                    return true;
                } else if (id == R.id.navigation_booking) {
                    startActivity(new Intent(getApplicationContext(), BookingActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                }

                return false;
            }
        });



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
                Intent intent = new Intent(HomeActivity.this, SelectPackage4Activity.class);
                startActivity(intent);
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Actions to click images
                Intent intent = new Intent(HomeActivity.this, SelectPackage5Activity.class);
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



    }

}