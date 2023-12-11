package com.example.team1.ui.calender;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.team1.R;

public class AnnouncementDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_details);

        // Retrieve announcement details from intent extras
        String title = getIntent().getStringExtra("title");
        String location = getIntent().getStringExtra("location");
        String dateTime = getIntent().getStringExtra("dateTime");
        String description = getIntent().getStringExtra("description");

        // Populate UI elements with announcement details
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView locationTextView = findViewById(R.id.locationTextView);
        TextView dateTimeTextView = findViewById(R.id.dateTimeTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);

        titleTextView.setText(title);
        locationTextView.setText(location);
        dateTimeTextView.setText(dateTime);
        descriptionTextView.setText(description);
    }
}
