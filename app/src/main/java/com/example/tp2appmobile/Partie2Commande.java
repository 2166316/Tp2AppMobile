package com.example.tp2appmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp2appmobile.BD.BDHelper;
import com.example.tp2appmobile.BD.Pizzeria;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Partie2Commande extends AppCompatActivity {

    private Address addressClient;

    private SQLiteDatabase database;
    private BDHelper dbHelper;

    private List<Pizzeria> pizzeriaList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partie2_commande);

        //init composantes
        initSpinnerVoyageur();
        initButtons();

        //init db
        dbHelper = new BDHelper(this,null);
        dbHelper.onCreate(dbHelper.getWritableDatabase());
        database = dbHelper.getWritableDatabase();


        //init liste de pizzeria
        pizzeriaList = getAllPizzerias();

        int idx =0;
        for (Pizzeria p:pizzeriaList) {
            Log.println(Log.INFO,"PIZZERIAS "+idx+" : ",p.toString());
        }
    }

    /**
     * Adresse : 3 Rue de la Pêche, Sainte-Hélène-de-Breakeyville
     * Code postal : QC G0S 1E3
     *
     * Adresse : 2336 Ch Ste-Foy Suite 800, Québec
     * Code postal : QC G1V 1S5
     *
     */
    public void initButtons() {
        Button buttonEnvoyerCommande = (Button) findViewById(R.id.envoyerCommande);
        Button buttonAfficherMap = (Button) findViewById(R.id.afficherMap);
        buttonEnvoyerCommande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //valide que tous les champs sont remplis
                boolean valide = validation();
                if (valide) {
                    Address address = validationAdresse();
                    if (address!=null){
                        afficheMsg("Adresse correct");
                        //ajoute valeur à address client
                        addressClient = address;
                        Pizzeria pizzeria = pizzeriaPlusProcheChoisie();
                        afficheMsg("Votre commande va être livrée par la succursale"+ pizzeria.getCOLUMN_PIZZERIA_SUCCURSALE()+"  Numéro de téléphone de cette succursale"+pizzeria.getCOLUMN_PIZZERIA_TELEPHONE());

                    }
                    else {
                        afficheMsg("pas une adresse");
                    }
                } else {
                    afficheMsg("Remplir tous les champs s.v.p");
                }
            }
        });

        buttonAfficherMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //valide que tous les champs sont remplis
                boolean valide = validation();
                if (valide) {
                    Address address = validationAdresse();
                    if (address!=null){
                        afficheMsg("Adresse correct");
                        //ajoute valeur à address client
                        addressClient = address;
                        //affiche adresse client
                        afficherMap(revoie());
                    }
                    else {
                        afficheMsg("pas une adresse");
                    }
                } else {
                    afficheMsg("Remplir tous les champs s.v.p");
                }
            }
        });
    }

    public Address revoie(){
        return getAddressFromStringAddress(this, pizzeriaPlusProcheChoisie().getCOLUMN_PIZZERIA_ADRESSE());
    }

    void afficheMsg(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    public boolean validation() {
        EditText adresseTextEdit = findViewById(R.id.adresseTextEdit);
        EditText nomTextEdit = findViewById(R.id.nomTextEdit);
        EditText prenomTextEdit = findViewById(R.id.prenomTextEdit);
        EditText telephoneTextEdit = findViewById(R.id.telephoneTextEdit);
        EditText codePostalTextEdit = findViewById(R.id.codePostalTextEdit);

        boolean isValid = true;

        // Check si les champs sont remplis
        if (adresseTextEdit.getText().toString().trim().isEmpty()) {
            adresseTextEdit.setError("Address is required");
            isValid = false;
        }

        if (nomTextEdit.getText().toString().trim().isEmpty()) {
            nomTextEdit.setError("Name is required");
            isValid = false;
        }

        if (prenomTextEdit.getText().toString().trim().isEmpty()) {
            prenomTextEdit.setError("First name is required");
            isValid = false;
        }

        if (telephoneTextEdit.getText().toString().trim().isEmpty()) {
            telephoneTextEdit.setError("Phone number is required");
            isValid = false;
        }

        if (codePostalTextEdit.getText().toString().trim().isEmpty()) {
            codePostalTextEdit.setError("Postal code is required");
            isValid = false;
        }
        return isValid;
    }


    public void initSpinnerVoyageur() {
        List<String> pizzaList = new ArrayList<>();
        pizzaList.add("Fromage");
        pizzaList.add("Peppéroni");
        pizzaList.add("Bacon");
        pizzaList.add("Garnie");
        pizzaList.add("Végé");

        Spinner spinner = (Spinner) findViewById(R.id.pizzaTypeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.simpletextlayout, pizzaList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                modifPrix();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    public void modifPrix() {
        Spinner spinner = (Spinner) findViewById(R.id.pizzaTypeSpinner);
        TextView textView = (TextView) findViewById(R.id.prixTotal);

        switch (spinner.getSelectedItem().toString()) {
            case "Fromage":
                textView.setText("9.0 $");
                break;
            case "Peppéroni":
                textView.setText("9.5 $");
                break;
            case "Bacon":
                textView.setText("10.0 $");
                break;
            case "Garnie":
                textView.setText("11.0 $");
                break;
            case "Végé":
                textView.setText("8.0 $");
                break;
            default:
                textView.setText("0.0 $");
        }
    }

    public void afficherMap(Address address) {

        address = address;
        Location location = new Location("Client");
        location.setLatitude(address.getLatitude());
        location.setLongitude(address.getLongitude());
        Intent intent = new Intent(this, MapPartie2.class);
        intent.putExtra("latitude", location.getLatitude());
        intent.putExtra("longitude", location.getLongitude());
        this.startActivity(intent);
    }


    public Address getAddressFromStringAddress(Context context, String strAddress) {
        Geocoder coder = new Geocoder(context, Locale.CANADA);
        List<Address> addresses = null;
        try {
            // May throw an IOException
            addresses = coder.getFromLocationName(strAddress, 1);
            Log.d("Adresse= ", addresses + "");

            if (addresses == null || addresses.size() == 0) {
                return null;
            }
        } catch (IOException ex) {
            return null;
        }
        return addresses.get(0);
    }

    public Address validationAdresse(){
        TextView textView = (TextView ) findViewById(R.id.adresseTextEdit);
        TextView textView2 = (TextView ) findViewById(R.id.codePostalTextEdit);
        String adresse = textView.getText().toString() +","+ textView2;
        //grpc failed
        Address addressRet =getAddressFromStringAddress(this,adresse);
        return addressRet;

    }

    //retourne tous les pizzeria sous forme de liste
    public List<Pizzeria> getAllPizzerias(){
        if(database==null){
            database = dbHelper.getWritableDatabase();
        }
        List<Pizzeria> pizzerias = new ArrayList<Pizzeria>();
        String[] allColumns = {BDHelper.COLUMN_ID,BDHelper.COLUMN_PIZZERIA_SUCCURSALE,BDHelper.COLUMN_PIZZERIA_TELEPHONE,BDHelper.COLUMN_PIZZERIA_ADRESSE};

        Cursor cursor = database.query(BDHelper.PIZZERIAS_TABLE_NAME,allColumns, null, null, null, null, null);

        //Ajouter tous les objets Comment à la liste
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Pizzeria pizzeria = cursorToPizzeria(cursor);
            pizzerias.add(pizzeria);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        dbHelper.close();
        return pizzerias;
    }

    private Pizzeria cursorToPizzeria(Cursor cursor) {
        Pizzeria pizzeria = new Pizzeria();
        //getLong(columnIndex)
        pizzeria.setCOLUMN_ID(cursor.getLong(0));
        pizzeria.setCOLUMN_PIZZERIA_SUCCURSALE(cursor.getString(1));
        pizzeria.setCOLUMN_PIZZERIA_TELEPHONE(cursor.getString(2));
        pizzeria.setCOLUMN_PIZZERIA_ADRESSE(cursor.getString(3));
        return pizzeria;
    }


    public static LatLng getLatLngFromAddress(Context context, String address) {
        Geocoder geocoder = new Geocoder(context);
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocationName(address, 1);

            if (addresses != null && addresses.size() > 0) {
                double latitude = addresses.get(0).getLatitude();
                double longitude = addresses.get(0).getLongitude();
                return new LatLng(latitude, longitude);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // Return null if no coordinates are found
    }

    public double calculeDistanceEntreDeuxPoints(LatLng client,LatLng pizzeria){
        double distance = SphericalUtil.computeDistanceBetween(client, pizzeria);
        return distance;
    }

    public Pizzeria pizzeriaPlusProcheChoisie(){
        TextView textView = (TextView ) findViewById(R.id.adresseTextEdit);
        TextView textView2 = (TextView ) findViewById(R.id.codePostalTextEdit);
        String adresseClient = textView.getText().toString() +","+ textView2;

        Pizzeria pizzeria = new Pizzeria();
        double distance = 0.0;
        int idx =0;
        for (Pizzeria p:pizzeriaList) {
            double distanceCourante = calculeDistanceEntreDeuxPoints(getLatLngFromAddress(this,adresseClient),getLatLngFromAddress(this,p.getCOLUMN_PIZZERIA_ADRESSE()));

            if (idx==0){
                distance = distanceCourante;
                pizzeria = p;
                idx++;
            }

            if (distanceCourante<distance){
                distance = distanceCourante;
                pizzeria = p;
            }

        }
        return pizzeria;
    }

}