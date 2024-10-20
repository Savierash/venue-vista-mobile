package com.example.venuevista;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000; // 3 seconds delay

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Using a handler to delay the transition to the MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // After the delay, start the MainActivity
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                // Close the SplashActivity
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
