package com.example.googlemapstart;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ZoomControls;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Button satellite;
    private ZoomControls zoomControls;
    private Boolean permissionIsGranted = false;
    List<String> locationName = new ArrayList<>();
    List<String> lat = new ArrayList<>();
    List<String> lng = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar a = getSupportActionBar();
        a.setTitle("CWS");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        satellite = findViewById(R.id.satellite);
        satellite.setText("Satellite");
        zoomControls = findViewById(R.id.zoom);
        mapFragment.getMapAsync(this);
        locationPermissionRequest();

//        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//        executorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 2, 1, TimeUnit.SECONDS);

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
//        locationName.add("hhh");
//        lat.add("24.977869");
//        lng.add("88.842820");
        locationName.add("Nikunja 2 Kacha Bazar");
        lat.add("23.831758");
        lng.add("90.415829");//1
        locationName.add("22b Kobi Faruk Sarani");
        lat.add("23.832107");
        lng.add("90.418743");//2ok
        locationName.add("1 Rd No. 11");
        lat.add("23.832368");
        lng.add("90.418422");//3ok
        locationName.add("Rnr Auto");
        lat.add("23.831598");
        lng.add("90.418484");//4ok
        locationName.add("SAY AUTOMATION & ENGINEERING");
        lat.add("23.831910");
        lng.add("90.417539");//5ok
        locationName.add("Al Modina Pharmacy");
        lat.add("23.831321");
        lng.add("90.417115");//6
        locationName.add("Khilkhet Nikunja 2 Jame Masjid");
        lat.add("23.830353");
        lng.add(" 90.417930");
        locationName.add("Tommy Miah's Tea Bar And Cafe Restaurant");
        lat.add("23.834495");
        lng.add("90.416581");


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

    private void locationPermissionRequest() {//This is for location permission request
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//jodi device ti mersmelow theke up hoi tahole permission lagbe na hole lagbe na
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {//jodi location PERMISSION_GRANTED na thake
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);//permisson request
            } else {
                permissionIsGranted = true;
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        insertMarkers(lat, lng, locationName, mMap);

        if (permissionIsGranted == true) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);

            getDeviceLocation();
            insertMarkers(lat, lng, locationName, mMap);
        }



        //recomment above line

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
            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.safe);
            mMap.addMarker(options).setIcon(icon);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 16));
            builder.include(position);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {//permission catch korer jonno
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//check korlam PERMISSION_GRANTED ase kina
                //in future here wii be add device location code
                Toast.makeText(this, "Location PERMISSION_GRANTED ", Toast.LENGTH_LONG).show();
                permissionIsGranted=true;
            } else {
                Toast.makeText(this, "deny permission", Toast.LENGTH_SHORT);
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);//PERMISSION_GRANTED na thakle aber permission er jonno request korbo
            }
        }
    }

    private void getDeviceLocation() {//for get device last location
        FusedLocationProviderClient locationProviderClient = LocationServices.getFusedLocationProviderClient(this);//for get last location
        locationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful()) {
                    Location location = task.getResult();//this is the device location
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 16));
                    mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("This is your location"));
                }
            }
        });
    }
}
