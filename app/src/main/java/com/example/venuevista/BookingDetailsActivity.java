package com.example.venuevista;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;


public class BookingDetailsActivity extends AppCompatActivity {

    //Do some Dropdown
    String[] items = {"Birthday","Wedding","Anniversary","Meeting","Conference",};
    String[] venue = {"Pavilion","Cottage","Poolside","Garden","Center",};
    AutoCompleteTextView etEventType, etVenue;
    ArrayAdapter<String> adapterItems, adapterVenue;


    private TextInputEditText  etCustomization, etDetails, etDate, etVisitors;
    private TextView tvNoChoices, tvTotalPrice;
    private Button btnCalculatePrice, btnConfirm; // Declare btnConfirm
    private LinearLayout layoutPriceCalculation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        //For Eventype
        etEventType = findViewById(R.id.etEventType);
        adapterItems = new ArrayAdapter<String>(this,R.layout.activity_list_item,items);
        etEventType.setAdapter(adapterItems);

        etEventType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: " +item,Toast.LENGTH_SHORT).show();
            }
        });

        //For Venue
        etVenue = findViewById(R.id.etVenue);
        adapterVenue = new ArrayAdapter<String>(this,R.layout.activity_list_item,venue);
        etVenue.setAdapter(adapterVenue);

        etVenue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: " +item,Toast.LENGTH_SHORT).show();
            }
        });


        etCustomization = findViewById(R.id.etCustomization);
        etDetails = findViewById(R.id.etDetails);
        etDate = findViewById(R.id.etDate);
        etVisitors = findViewById(R.id.etVisitors);
        btnCalculatePrice = findViewById(R.id.btnCalculatePrice);
        btnConfirm = findViewById(R.id.btnConfirm);
        layoutPriceCalculation = findViewById(R.id.layoutPriceCalculation);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);



        // TextWatcher for additional details
        etDetails.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed
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
                // No action needed
            }
        });

        // Handle booking confirmation
        btnConfirm.setOnClickListener(v -> {
            String eventType = etEventType.getText().toString();
            String venue = etVenue.getText().toString();
            String customization = etCustomization.getText().toString();
            String additionalDetails = etDetails.getText().toString();

            // Check if any required fields are empty
            if (eventType.isEmpty() || venue.isEmpty() || etVisitors.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Confirmation message
            String confirmationMessage = "Event Type: " + eventType +
                    "\nVenue: " + venue +
                    "\nCustomization: " + customization +
                    "\nAdditional Details: " + additionalDetails;

            // Show confirmation dialog
            new AlertDialog.Builder(this)
                    .setTitle("Confirm Booking")
                    .setMessage(confirmationMessage)
                    .setPositiveButton("Confirm", (dialog, which) -> {
                        // Navigate to CompleteActivity
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
            try {
                int numberOfVisitors = Integer.parseInt(visitorsText);
                double pricePerVisitor = 500.0; // Example price per visitor in PHP
                double totalPrice = numberOfVisitors * pricePerVisitor;

                // Display the total price in Philippine Pesos
                tvTotalPrice.setText("Total Amount: ₱" + String.format("%.2f", totalPrice));
                layoutPriceCalculation.setVisibility(View.VISIBLE);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter a valid number of visitors", Toast.LENGTH_SHORT).show();
            }
        } else {
            tvTotalPrice.setText("Total Amount: ₱0.00");
        }
    }
}
