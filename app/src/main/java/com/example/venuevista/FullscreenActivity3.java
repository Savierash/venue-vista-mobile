package com.example.venuevista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class FullscreenActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen3);

        ImageView fullscreenImage = findViewById(R.id.fullscreen_image);

        fullscreenImage.setOnClickListener(v -> finish()); // Close the activity on click
    }
}
