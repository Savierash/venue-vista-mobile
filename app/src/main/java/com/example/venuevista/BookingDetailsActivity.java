package com.example.venuevista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BookingDetailsActivity extends AppCompatActivity {
    private TextView venueNameTextView;
    private TextView venueDetailsTextView;
    private Button bookNowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_details);

        // Initialize views
        venueNameTextView = findViewById(R.id.venue_name);
        venueDetailsTextView = findViewById(R.id.venue_details);
        bookNowButton = findViewById(R.id.book_now_button);

        // Get data from Intent
        Intent intent = getIntent();
        String venueName = intent.getStringExtra("VENUE_NAME");  // Ensure the key matches the one used in the previous activity
        String venueDetails = intent.getStringExtra("VENUE_DETAILS"); // Ensure the key matches the one used in the previous activity

        // Set data to views, handling null cases
        venueNameTextView.setText(venueName != null ? venueName : "Unknown Venue");  // Fallback text for venue name
        venueDetailsTextView.setText(venueDetails != null ? venueDetails : "No details available."); // Fallback text for venue details

        // Set up button click listener
        bookNowButton.setOnClickListener(v -> {
            // Handle booking logic here, e.g., send booking request to server
            Toast.makeText(this, "Booking request sent!", Toast.LENGTH_SHORT).show();
            finish(); // Close activity after booking
        });
    }
}
