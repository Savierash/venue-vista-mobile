package models; // Adjust this to your actual package structure

import java.util.List;

public class SearchResponse {
    private List<Item> items; // Assuming you have an Item class to represent search results

    public SearchResponse(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    // You can add other fields if needed
}
