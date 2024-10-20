package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import models.Venue;

public class VenueAdapter extends RecyclerView.Adapter<VenueAdapter.VenueViewHolder> {

    private List<Venue> venueList;

    public VenueAdapter(List<Venue> venueList) {
        this.venueList = venueList != null ? venueList : new ArrayList<>(); // Initialize with an empty list if null
    }

    @NonNull
    @Override
    public VenueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_venue, parent, false);
        return new VenueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VenueViewHolder holder, int position) {
        Venue venue = venueList.get(position);
        holder.venueName.setText(venue.getName());
        holder.venuePrice.setText(String.format("$%.2f", venue.getPrice())); // Use this if you didn't implement getFormattedPrice

        Glide.with(holder.itemView.getContext())
                .load(venue.getPhoto())
                .into(holder.venueImage);
    }

    @Override
    public int getItemCount() {
        return venueList.size();
    }

    // Method to update the venue list
    public void updateVenues(List<Venue> venues) {
        this.venueList.clear(); // Clear the existing list
        if (venues != null) {
            this.venueList.addAll(venues); // Add new venues if not null
        }
        notifyDataSetChanged(); // Notify the adapter of the data change
    }

    static class VenueViewHolder extends RecyclerView.ViewHolder {
        TextView venueName;
        TextView venuePrice;
        ImageView venueImage;

        VenueViewHolder(View itemView) {
            super(itemView);
            venueName = itemView.findViewById(R.id.venue_name);
            venuePrice = itemView.findViewById(R.id.venue_price);
            venueImage = itemView.findViewById(R.id.venue_image);
        }
    }
}
