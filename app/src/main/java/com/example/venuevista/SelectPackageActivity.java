package com.example.venuevista;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SelectPackageActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_details);  // Corrected layout file name


        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();  // Closes the current activity and returns to the previous one
            }
        });

    }
}
