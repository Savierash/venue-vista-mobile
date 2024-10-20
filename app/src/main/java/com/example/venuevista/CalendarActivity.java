package com.example.venuevista;

import android.app.Activity;
import android.os.Bundle;

public class CalendarActivity extends Activity {
    // Ensure it's public and has an empty constructor if needed
    public CalendarActivity() {
        // Default constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar); // Replace with your actual layout
    }
}
