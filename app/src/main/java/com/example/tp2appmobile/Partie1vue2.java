package com.example.tp2appmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Partie1vue2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partie1vue2);

        String nomImage = getIntent().getStringExtra("nomImage");


        if(nomImage!=null){
            afficheData(nomImage);
        }

        Button button = (Button) findViewById(R.id.butonRetourPartie1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retourPartie1();
            }
        });
    }

    public void retourPartie1(){
        Intent intent = new Intent(this, Partie1vue1.class);
        startActivity(intent);
    }

    public void afficheData( String nom){
        TextView textView = (TextView)  findViewById(R.id.afficheNomImage);
        textView.setText(nom);
    }


}