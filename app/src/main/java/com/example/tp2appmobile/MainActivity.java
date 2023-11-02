package com.example.tp2appmobile;

import androidx.appcompat.app.AppCompatActivity;
import  android.content.Context;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Address address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle("Tp2");
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        Intent intent = null;

        if (itemId==R.id.partie1){
            intent = new Intent(this, Partie1vue1.class);
            super.startActivity(intent);
        }else if(itemId==R.id.partie2) {

            intent = new Intent(this, Partie2Commande.class);
            super.startActivity(intent);

        }

        return true;
    }





}