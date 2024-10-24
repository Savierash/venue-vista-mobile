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
import android.widget.Toast;
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
    protected void onCreate(Bundle savedInstanceState) {
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


        Button btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingDetailsActivity.this,CompleteActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
