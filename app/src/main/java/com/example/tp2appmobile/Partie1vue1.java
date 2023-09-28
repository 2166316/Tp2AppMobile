package com.example.tp2appmobile;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Partie1 extends MainActivity{

    public boolean imagesAfficher;

    public int imageSelectionnerID;

    public Partie1() {
        this.imagesAfficher = false;
        this.imageSelectionnerID =0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partie1);


        Button buttonSuivant = (Button) findViewById(R.id.buttonSuivantPartie1);
        buttonSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    public void prochaineVue(){
        if(imageSelectionnerID!=0){
            
        }
    }



    /**
     * Appelé lors d'un touchscreen event (pour afficher image)
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action =event.getAction();
        if(action==MotionEvent.ACTION_DOWN){
            Log.println(Log.DEBUG,"MSG","Écran touched");
            if(!imagesAfficher){
                this.imagesAfficher = true;
                Log.println(Log.DEBUG,"MSG","Écran touched and image pas afficher");
                //appel methode
                afficheImages();
                ajoutEventClickImage();
             }
        }
        return super.onTouchEvent(event);
    }

    /**
     * Affiche les images
     */
    public void afficheImages(){
        ImageView imageView1 = (ImageView) findViewById(R.id.image1);
        ImageView imageView2 = (ImageView) findViewById(R.id.image2);
        ImageView imageView3 = (ImageView) findViewById(R.id.image3);
        ImageView imageView4 = (ImageView) findViewById(R.id.image4);

        List<Integer> listOfImages = new ArrayList<>();
        listOfImages.add(R.drawable.pomme);
        listOfImages.add(R.drawable.kiwi);
        listOfImages.add(R.drawable.orange);
        listOfImages.add(R.drawable.wifi);
        listOfImages.add(R.drawable.banana);

        //création array of random images
        List<Integer> listOfRandomImages = new ArrayList<>();
        for (int i =0;i<4;i++){
            int randomInt = (int) (Math.random()*listOfImages.size());
            listOfRandomImages.add(listOfImages.get(randomInt));
        }

        //ajout des images
        imageView1.setImageResource(listOfImages.get(0));
        imageView2.setImageResource(listOfImages.get(1));
        imageView3.setImageResource(listOfImages.get(2));
        imageView4.setImageResource(listOfImages.get(3));
    }

    /**
     * Ajout de la logique lors de click sur un image
     */
    public void ajoutEventClickImage(){

        ImageView imageView1 = (ImageView) findViewById(R.id.image1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surbrillance(view.getId());
            }
        });

        ImageView imageView2 = (ImageView) findViewById(R.id.image2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surbrillance(view.getId());
            }
        });

        ImageView imageView3 = (ImageView) findViewById(R.id.image3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surbrillance(view.getId());
            }
        });

        ImageView imageView4 = (ImageView) findViewById(R.id.image4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surbrillance(view.getId());
            }
        });
    }

    /**
     * efface la couleur
     */
    public void resetColor(){
        ImageView imageView1 = (ImageView) findViewById(R.id.image1);
        ImageView imageView2 = (ImageView) findViewById(R.id.image2);
        ImageView imageView3 = (ImageView) findViewById(R.id.image3);
        ImageView imageView4 = (ImageView) findViewById(R.id.image4);

        imageView1.setBackgroundColor(Color.WHITE);
        imageView2.setBackgroundColor(Color.WHITE);
        imageView3.setBackgroundColor(Color.WHITE);
        imageView4.setBackgroundColor(Color.WHITE);
    }

    /**
     * Ajout de la surbrillance sur image selectionné
     * @param imageViewId
     */
    public void surbrillance(int imageViewId){
        Log.println(Log.INFO,"INFO surbrillance","id img : "+imageViewId);
        this.imageSelectionnerID = imageViewId;
        resetColor();
        ImageView imageView1 = (ImageView) findViewById(imageViewId);
        imageView1.setBackgroundColor(Color.RED);

    }


}