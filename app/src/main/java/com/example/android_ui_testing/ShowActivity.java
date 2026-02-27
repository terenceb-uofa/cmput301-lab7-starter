package com.example.android_ui_testing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Button backButton = findViewById(R.id.button_back);

        //Receive data (the intent that started the activity)
        Intent intent = getIntent();

        //Extract city name
        String cityName = intent.getStringExtra("city_name");

        TextView shownCity = findViewById(R.id.shownCity);
        if (cityName != null) {
            shownCity.setText(cityName);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               finish();
            }
        });


    }
}