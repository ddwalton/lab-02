package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    String selectedCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        dataList = new ArrayList<String>();

        cityAdapter = new ArrayAdapter<String>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // Set click listener for list cities
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = dataList.get(position);
            }
        });

        // Set click listeners for buttons
        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(v -> {
            if (selectedCity == null) return;
            dataList.remove(selectedCity);;
            selectedCity = null;
            cityAdapter.notifyDataSetChanged();
        });

        Button addButton = findViewById(R.id.addButton);
        LinearLayout inputLayout = findViewById(R.id.input_layout);

        addButton.setOnClickListener(v -> {
            // show text input dialog
            inputLayout.setVisibility(View.VISIBLE);
        });

        EditText inputField = findViewById(R.id.inputField);
        Button confirmButton = findViewById(R.id.confirmButton);

        confirmButton.setOnClickListener(v -> {
            if (!inputField.getText().toString().isEmpty()) {
                dataList.add(inputField.getText().toString());
                cityAdapter.notifyDataSetChanged();
                inputLayout.setVisibility(View.GONE);
                inputField.setText("");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}