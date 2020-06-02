package com.example.googlemapstart;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class FirstActivity extends AppCompatActivity {
    LinearLayout safePlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        safePlace = findViewById(R.id.safePlace);
        safePlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, MainActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        locationPermissionRequest();
    }
    private void locationPermissionRequest() {//This is for location permission request
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//jodi device ti mersmelow theke up hoi tahole permission lagbe na hole lagbe na
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {//jodi location PERMISSION_GRANTED na thake
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);//permisson request
            } else {

            }
        }
    }
}
