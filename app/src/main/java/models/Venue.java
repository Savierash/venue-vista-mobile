package models;

public class Venue {
    private int venueId;
    private String name;
    private double price;
    private String photo;

    // Constructor
    public Venue(int venueId, String name, double price, String photo) {
        this.venueId = venueId;
        this.name = name;
        this.price = price;
        this.photo = photo;
    }

    // Getters
    public int getVenueId() {
        return venueId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getPhoto() {
        return photo;
    }

    // Setters (if needed)
    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    // Method to get formatted price (useful for displaying price with currency symbol)
    public String getFormattedPrice() {
        return String.format("$%.2f", price);
    }

    // toString method for easy debugging and logging
    @Override
    public String toString() {
        return "Venue{" +
                "venueId=" + venueId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", photo='" + photo + '\'' +
                '}';
    }

    // Optional: Equals and hashCode methods if you plan on comparing Venue objects
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Venue venue = (Venue) obj;

        return venueId == venue.venueId;
    }

    @Override
    public int hashCode() {
        return venueId;
    }
}
