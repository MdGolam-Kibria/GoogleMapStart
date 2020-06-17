package com.example.googlemapstart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class FirstActivity extends AppCompatActivity {
    ImageButton safePlace;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        safePlace = findViewById(R.id.safePlace);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navbar = (NavigationView) findViewById(R.id.navBar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for toggle
        navbar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Toast.makeText(FirstActivity.this, "wow it is works", Toast.LENGTH_LONG).show();
                        return true;
                }
                return false;
            }
        });
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


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.row_menu, menu);
        //hide searchview as we don't need it here
        // menu.findItem(R.id.action_search).setVisible(false);//remove search icon from here......
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

        //        if (item.getItemId() == R.id.leftMore) {
//
//        }
//        if (item.getItemId() == R.id.share) {
//
//        }
//        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.home:
////                repleaseFragment(new HomeFragment());
//                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.nav_hospital:
////                repleaseFragment(new WishList());
//                Toast.makeText(this, "hospital", Toast.LENGTH_SHORT).show();
//
//                return true;
//            case R.id.nav_bus:
////                repleaseFragment(new Cart());
//                Toast.makeText(this, "bus", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.nav_shool:
////                repleaseFragment(new DashBoard());
//                Toast.makeText(this, "school", Toast.LENGTH_SHORT).show();
//                return true;
//        }
//        return false;
//    }
    private void locationPermissionRequest() {//This is for location permission request
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//jodi device ti mersmelow theke up hoi tahole permission lagbe na hole lagbe na
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {//jodi location PERMISSION_GRANTED na thake
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);//permisson request
            } else {

            }
        }
    }
}
