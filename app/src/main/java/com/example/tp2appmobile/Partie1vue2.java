package com.example.tp2appmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Partie1vue2 extends AppCompatActivity {


    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partie1vue2);

        //start music
        if(mediaPlayer ==null){
            mediaPlayer = MediaPlayer.create(this,R.raw.song);
            mediaPlayer.setLooping(true);
        }
        mediaPlayer.start();



        //mediaPlayer = new  MediaPlayer();
        //texte
        String nomImage = getIntent().getStringExtra("nomImage");
        TextView textView = (TextView)  findViewById(R.id.afficheNomImage);
        textView.setText(nomImage);

        //image
        byte[] imageBytes = getIntent().getByteArrayExtra("image_byte");
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        ImageView imageView = (ImageView)  findViewById(R.id.imagePartie1Vue2);
        imageView.setImageBitmap(bitmap);

        //boutton retour
        Button button = (Button) findViewById(R.id.butonRetourPartie1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retourPartie1();
            }
        });
    }

    public void retourPartie1(){
        if(mediaPlayer !=null){
            mediaPlayer.stop();
        }
        Intent intent = new Intent(this, Partie1vue1.class);
        startActivity(intent);

    }


}