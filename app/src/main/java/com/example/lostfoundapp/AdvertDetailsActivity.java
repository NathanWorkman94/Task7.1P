package com.example.lostfoundapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lostfoundapp.data.DatabaseHelper;

public class AdvertDetailsActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvDate;
    TextView tvLocation;
    TextView tvType;
    Button btnRemove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert_details);

        tvType = findViewById(R.id.tvDetailType);
        tvName = findViewById(R.id.tvDetailName);
        tvDate = findViewById(R.id.tvDetailDate);
        tvLocation = findViewById(R.id.tvDetailLocation);
        btnRemove = findViewById(R.id.btnRemove);

        tvType.setText(getIntent().getStringExtra("advertType"));
        tvName.setText(getIntent().getStringExtra("advertName"));
        tvDate.setText(getIntent().getStringExtra("advertDate"));
        tvLocation.setText(getIntent().getStringExtra("advertLocation"));

        // Get the advert ID from the intent
        int advertId = getIntent().getIntExtra("advertId", -1);

        btnRemove.setOnClickListener(v -> {
            if (advertId != -1)
            {
                // Delete the advert from the database
                DatabaseHelper dbHelper = new DatabaseHelper(this, null, null, 1);
                dbHelper.deleteAdvertById(advertId);

                Toast.makeText(this, "Advert successfully taken down.", Toast.LENGTH_SHORT).show();
                finish();
            }}
        );
    }
}