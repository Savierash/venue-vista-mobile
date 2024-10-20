package com.example.venuevista;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class BookingDetailsActivity extends AppCompatActivity {

    private TextInputEditText etEventType, etVenue, etCustomization, etDetails, etDate, etVisitors;
    private TextView tvNoChoices, tvTotalPrice;
    private Button btnCalculatePrice, btnConfirm; // Declare btnConfirm here
    private LinearLayout layoutPriceCalculation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details); // Ensure this matches your layout file name

        // Initialize views
        etEventType = findViewById(R.id.etEventType);
        etVenue = findViewById(R.id.etVenue);
        etCustomization = findViewById(R.id.etCustomization);
        etDetails = findViewById(R.id.etDetails);
        etDate = findViewById(R.id.etDate);
        etVisitors = findViewById(R.id.etVisitors);
        tvNoChoices = findViewById(R.id.tvNoChoices);
        btnCalculatePrice = findViewById(R.id.btnCalculatePrice);
        btnConfirm = findViewById(R.id.btnConfirm); // Initialize btnConfirm here
        layoutPriceCalculation = findViewById(R.id.layoutPriceCalculation);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);

        // TextWatcher for Additional Details
        etDetails.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed here
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Check if the input is empty
                if (s.toString().trim().isEmpty()) {
                    tvNoChoices.setVisibility(View.VISIBLE); // Show the message
                } else {
                    tvNoChoices.setVisibility(View.GONE); // Hide the message
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No action needed here
            }
        });

        btnConfirm.setOnClickListener(v -> {
            // Handle the confirmation logic here
            String eventType = etEventType.getText().toString();
            String venue = etVenue.getText().toString();
            String customization = etCustomization.getText().toString();
            String additionalDetails = etDetails.getText().toString();

            // Check if any fields are empty
            if (eventType.isEmpty() || venue.isEmpty() || etVisitors.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a confirmation message
            String confirmationMessage = "Event Type: " + eventType +
                    "\nVenue: " + venue +
                    "\nCustomization: " + customization +
                    "\nAdditional Details: " + additionalDetails;

            // Show a confirmation dialog
            new AlertDialog.Builder(this)
                    .setTitle("Confirm Booking")
                    .setMessage(confirmationMessage)
                    .setPositiveButton("Confirm", (dialog, which) -> {
                        // Navigate to BookingCompleteActivity
                        Intent intent = new Intent(BookingDetailsActivity.this, CompleteActivity.class);
                        startActivity(intent);
                        finish(); // Optionally finish this activity
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });


        // Button click listener for calculating price
        btnCalculatePrice.setOnClickListener(v -> calculatePrice());
    }

    private void calculatePrice() {
        // Example price calculation logic
        String visitorsText = etVisitors.getText().toString();
        if (!visitorsText.isEmpty()) {
            int numberOfVisitors = Integer.parseInt(visitorsText);
            double pricePerVisitor = 500.0; // Example price per visitor in PHP
            double totalPrice = numberOfVisitors * pricePerVisitor;

            // Display the total price in Philippine Pesos
            tvTotalPrice.setText("Total Amount: ₱" + String.format("%.2f", totalPrice));
            layoutPriceCalculation.setVisibility(View.VISIBLE);
        } else {
            tvTotalPrice.setText("Total Amount: ₱0.00");
            layoutPriceCalculation.setVisibility(View.GONE);
        }
    }
}
