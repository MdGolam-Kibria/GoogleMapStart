package com.example.googlemapstart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ZoomControls;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMapLongClickListener {
    private GoogleMap mMap;
    private Button satellite;
    private ZoomControls zoomControls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        satellite = findViewById(R.id.satellite);
        zoomControls = findViewById(R.id.zoom);
        mapFragment.getMapAsync(this);
        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });
        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });
        satellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
                    satellite.setText("Normal");
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

                } else if (mMap.getMapType() == GoogleMap.MAP_TYPE_SATELLITE) {
                    satellite.setText("satellite");
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                }
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        satellite.setText("satellite");

        // Add a marker in Sydney, Australia, and move the camera.

        LatLng latLng = new LatLng(23.754303, 90.360690);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Alhaj Mockbul Hossain University College"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
//        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

}
