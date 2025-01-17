package com.example.myapplication;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.myapplication.databinding.ActivityMyLocationBinding;
import com.google.android.gms.maps.model.PolylineOptions;

public class MyLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button btn_satellite, btn_terrain, btn_normal, btn_hybrid;
    private ActivityMyLocationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMyLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn_satellite = findViewById(R.id.btn_map_satellite);
        btn_terrain = findViewById(R.id.btn_map_terrain);
        btn_normal = findViewById(R.id.btn_map_normal);
        btn_hybrid = findViewById(R.id.btn_map_hybrid);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng mylocation = new LatLng(20.03170746096047, 78.54737957764765);
        mMap.addMarker(new MarkerOptions().position(mylocation).title("Marker in Pandharkawada , Srujal's Home"));
 //       mMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
        mMap.animateCamera(CameraUpdateFactory. newLatLngZoom(mylocation,16),4000,null);

        mMap.addCircle(new CircleOptions()
                .center(mylocation)
                .fillColor(Color.parseColor("#9403A9F4"))
                .radius(150)
                .strokeColor(Color.BLUE)
        );

        btn_satellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });

        btn_terrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });

        btn_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        btn_hybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        LatLng arvi = new LatLng(20.995131547090242, 78.20565879445276);
        mMap.addMarker(new MarkerOptions().position(arvi).title("Marker in Arvi College"));

        mMap.addPolyline(new PolylineOptions()
                .add(mylocation, arvi)
                .width(10)
                .color(Color.GRAY)
                .geodesic(true)
        );
    }
}