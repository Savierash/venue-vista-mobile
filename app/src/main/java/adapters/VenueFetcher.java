package adapters; // Adjust the package name as necessary

import java.io.BufferedReader; // Importing BufferedReader
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader; // Importing InputStreamReader
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import models.Venue; // Import your Venue model

public class VenueFetcher {
    // Method to fetch venues from the provided URL
    public List<Venue> fetchVenues(String urlString) throws IOException, JSONException {
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            // Check the response code
            int responseCode = urlConnection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            InputStream in = urlConnection.getInputStream();
            return parseVenues(readStream(in));
        } finally {
            urlConnection.disconnect();
        }
    }

    // Method to parse the JSON response
    private List<Venue> parseVenues(String json) throws JSONException {
        List<Venue> venueList = new ArrayList<>();

        // Parse the JSON object
        JSONObject jsonObject = new JSONObject(json);
        // Check if the response indicates success
        if (jsonObject.getBoolean("success")) {
            JSONArray venuesArray = jsonObject.getJSONArray("venues");
            for (int i = 0; i < venuesArray.length(); i++) {
                JSONObject venueObject = venuesArray.getJSONObject(i);
                int venueId = venueObject.getInt("venue_id"); // Use "venue_id" if this is your JSON key
                String name = venueObject.getString("name");
                double price = venueObject.getDouble("price");
                String photo = venueObject.getString("photo");
                venueList.add(new Venue(venueId, name, price, photo));
            }
        } else {
            // Handle failure case if the response indicates failure
            System.err.println("Failed to fetch venues: " + jsonObject.optString("message"));
        }

        return venueList;
    }

    // Method to read the InputStream and convert it to a String
    private String readStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
}
