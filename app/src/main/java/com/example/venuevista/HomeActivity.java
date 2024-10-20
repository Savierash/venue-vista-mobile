package com.example.venuevista;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import adapters.VenueAdapter;
import models.Venue;
import adapters.VenueFetcher;

public class HomeActivity extends AppCompatActivity {

    private EditText searchBar;
    private ImageButton profileButton;
    private ImageButton homeButton;
    private ImageButton calendarButton;
    private RecyclerView recyclerView;
    private VenueAdapter venueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize UI components
        searchBar = findViewById(R.id.search_bar);
        profileButton = findViewById(R.id.profile_button);
        homeButton = findViewById(R.id.home_icon); // Updated to match XML ID
        calendarButton = findViewById(R.id.calendar_icon); // Updated to match XML ID
        recyclerView = findViewById(R.id.venues_recycler_view);

        // Set up RecyclerView
        venueAdapter = new VenueAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(venueAdapter);

        // Start fetching venues from the server
        String url = "http://10.0.2.2/VenueVista2/get_venues.php"; // URL to fetch venues
        new FetchVenuesTask(this, venueAdapter).execute(url); // Pass context here

        // Set click listener for search bar
        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for profile button
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainProfileActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for home button
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Optional: Handle home button action
                Toast.makeText(HomeActivity.this, "Home icon clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for calendar button
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BookingActivity.class);
                startActivity(intent);
            }
        });
    }

    private static class FetchVenuesTask extends AsyncTask<String, Void, List<Venue>> {
        private VenueAdapter venueAdapter;
        private HomeActivity homeActivity; // Reference to HomeActivity

        public FetchVenuesTask(HomeActivity homeActivity, VenueAdapter venueAdapter) {
            this.homeActivity = homeActivity; // Store the reference
            this.venueAdapter = venueAdapter;
        }

        @Override
        protected List<Venue> doInBackground(String... urls) {
            try {
                VenueFetcher venueFetcher = new VenueFetcher();
                return venueFetcher.fetchVenues(urls[0]);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return null; // Return null in case of an error
            }
        }

        @Override
        protected void onPostExecute(List<Venue> venues) {
            if (venues != null && !venues.isEmpty()) {
                venueAdapter.updateVenues(venues); // Update the venue list
                venueAdapter.notifyDataSetChanged(); // Notify adapter of data change
            } else {
                // Use the reference to HomeActivity to show the Toast
                Toast.makeText(homeActivity, "No venues found", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
