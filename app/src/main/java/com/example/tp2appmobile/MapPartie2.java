package com.example.tp2appmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tp2appmobile.databinding.FragmentMyformapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapPartie2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FragmentMyformapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentMyformapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        Intent intent = this.getIntent();

        // Add a marker in Sydney and move the camera
        LatLng banque = new LatLng(intent.getDoubleExtra("latitude", 0),
                intent.getDoubleExtra("longitude",0));
        mMap.addMarker(new MarkerOptions().position(banque).title("Pizzeria")).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(banque, 16.0f));
        mMap.setMapType ( GoogleMap . MAP_TYPE_NORMAL );
        mMap.getUiSettings (). setZoomControlsEnabled ( true );
        mMap.getUiSettings (). setZoomGesturesEnabled ( true );
        mMap.getUiSettings (). setCompassEnabled (true);
    }
}