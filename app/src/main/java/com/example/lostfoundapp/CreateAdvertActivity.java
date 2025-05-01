package com.example.lostfoundapp;

import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lostfoundapp.data.DatabaseHelper;
import com.example.lostfoundapp.model.Advert;

public class CreateAdvertActivity extends AppCompatActivity {

    private EditText etName, etPhone, etDescription, etDate, etLocation;
    private RadioGroup radioPostType;
    private Button btnSave;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        // Link XML elements
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etDescription = findViewById(R.id.etDescription);
        etDate = findViewById(R.id.etDate);
        etLocation = findViewById(R.id.etLocation);
        radioPostType = findViewById(R.id.radioPostType);
        btnSave = findViewById(R.id.btnSave);

        // Create database helper
        dbHelper = new DatabaseHelper(this, null, null, 1);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAdvert();
            }
        });
    }

    private void saveAdvert()
    {
        // Get data from fields
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String description = etDescription.getText().toString();
        String date = etDate.getText().toString();
        String location = etLocation.getText().toString();

        // Get selected radio button
        int selectedId = radioPostType.getCheckedRadioButtonId();
        if (selectedId == -1){
            Toast.makeText(this, "Please select a post type", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton radioButton = findViewById(selectedId);
        String type = radioButton.getText().toString().toUpperCase();

        // Handle empty fields
        if (name.isEmpty() || phone.isEmpty() || description.isEmpty() || date.isEmpty() || location.isEmpty()){
            Toast.makeText(this, "Please complete all fields first...", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create advert object
        Advert advert = new Advert(name, phone, date, location, description, type);
        long result = dbHelper.insertAdvert(advert);

        if (result > 0)
        {
            Toast.makeText(this, "Advert successfully saved", Toast.LENGTH_SHORT).show();

            // Clear fields
            etName.setText("");
            etPhone.setText("");
            etDescription.setText("");
            etDate.setText("");
            etLocation.setText("");
            radioPostType.check(R.id.radioBtnLost);
        }
        else
        {
            Toast.makeText(this, "Failed to save advert", Toast.LENGTH_SHORT).show();
        }
    }
}