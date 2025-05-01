package com.example.lostfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lostfoundapp.data.DatabaseHelper;
import com.example.lostfoundapp.model.Advert;

import java.util.ArrayList;
import java.util.List;

public class AdvertListActivity extends AppCompatActivity {

    private ListView lvAdverts;
    private DatabaseHelper dbHelper;
    private List<String> advertTitles;
    private ArrayAdapter adapter;
    private List<Advert> advertList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert_list);

        lvAdverts = findViewById(R.id.lvAdverts);
        dbHelper = new DatabaseHelper(this, null, null, 1);

        loadAdverts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAdverts();
    }

    private void loadAdverts()
    {
        // Get all adverts from db
        advertList = dbHelper.getAllAdverts();

        advertTitles = new ArrayList<>();

        // Create list of advert titles in format "Type: Item"
        for (Advert advert : advertList)
        {
            advertTitles.add(advert.getType() + ": " + advert.getName());
        }

        // Set adapter with advert titles
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, advertTitles);
        lvAdverts.setAdapter(adapter);

        // Set click listener for each advert
        lvAdverts.setOnItemClickListener((parent, view, position, id) -> {
            Advert clickedAdvert = advertList.get(position);

            Intent intent = new Intent(this, AdvertDetailsActivity.class);
            intent.putExtra("advertId", clickedAdvert.getId());
            intent.putExtra("advertName", clickedAdvert.getName());
            intent.putExtra("advertPhone", clickedAdvert.getPhone());
            intent.putExtra("advertDate", clickedAdvert.getDate());
            intent.putExtra("advertLocation", clickedAdvert.getLocation());
            intent.putExtra("advertDescription", clickedAdvert.getDescription());
            intent.putExtra("advertType", clickedAdvert.getType());

            startActivity(intent);
        });
    }
}