package com.example.googlemapstart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ZoomControls;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Button satellite;
    private ZoomControls zoomControls;
    List<String> locationName = new ArrayList<>();
    List<String> lat = new ArrayList<>();
    List<String> lng = new ArrayList<>();

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
        locationName.add("Rayer Bazar Bodhyo Bhumi");lat.add("23.751033");lng.add("90.357096");//1
        locationName.add("coffee express");lat.add("23.754114");lng.add("90.360951");//2
        locationName.add("Dutch-Bangla Bank Limited (DBBL)");lat.add("23.754340");lng.add("90.360895");//3
        locationName.add("RFL Best Buy - Kaderabad Housing");lat.add("23.754794");lng.add("90.360611");//4
        locationName.add("Coffee Adda");lat.add("23.754581");lng.add("90.361576");//5
        locationName.add("Katashur Shahi Jam'e Mosque");lat.add("23.754507");lng.add("90.362424");//6



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
        insertMarkers(lat, lng, locationName,mMap);
//        satellite.setText("satellite");
//
//        // Add a marker in Sydney, Australia, and move the camera.
//
//        LatLng latLng = new LatLng(23.754303, 90.360690);
//        mMap.addMarker(new MarkerOptions().position(latLng).title("Alhaj Mockbul Hossain University College"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
////        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    }

    private void insertMarkers(List<String> lat, List<String> lng, List<String> list, GoogleMap mMap) {
        final LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for (int i = 0; i < lat.size(); i++) {
            final LatLng position = new LatLng(Double.parseDouble(lat.get(i)), Double.parseDouble(lng.get(i)));
            final MarkerOptions options = new MarkerOptions().position(position).title(locationName.get(i));

            mMap.addMarker(options);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 16));
            builder.include(position);
        }

    }

}
