package com.example.tp2appmobile.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper {

    //nécessaire db
    private static final String DATABASE_NAME = "commentaires.db";
    private static final int DATABASE_VERSION = 1;

    //table Pizzerias
    public static final String PIZZERIAS_TABLE_NAME = "Pizzerias";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PIZZERIA_SUCCURSALE = "pizzeria_SUCCURSALE";
    public static final String COLUMN_PIZZERIA_TELEPHONE = "pizzeria_TELEPHONE";
    public static final String COLUMN_PIZZERIA_ADRESSE = "pizzeria_ADRESSE";

    private static final String PIZZERIAS_TABLE = "create table "
            + PIZZERIAS_TABLE_NAME + "(" +
            COLUMN_ID + " integer primary key autoincrement, "
            +COLUMN_PIZZERIA_SUCCURSALE +" text not null,"
            +COLUMN_PIZZERIA_TELEPHONE+" text not null, "
            +COLUMN_PIZZERIA_ADRESSE +" text not null);";


    public BDHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //efface
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PIZZERIAS_TABLE_NAME);
        //init
        sqLiteDatabase.execSQL(PIZZERIAS_TABLE);
        //init valeur défault
        initDefaultValues(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PIZZERIAS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    public void initDefaultValues(SQLiteDatabase sqLiteDatabase){


        ContentValues values1 = new ContentValues();

        values1.put(COLUMN_PIZZERIA_SUCCURSALE, "Domino's Pizza ste-foy");
        values1.put(COLUMN_PIZZERIA_TELEPHONE, "+14186505555");
        values1.put(COLUMN_PIZZERIA_ADRESSE, "2673 Ch Ste-Foy, Québec, QC G1V 1V3");

        //insert
        sqLiteDatabase.insert(PIZZERIAS_TABLE_NAME, null, values1);

        ContentValues values2 = new ContentValues();
        values2.put(COLUMN_PIZZERIA_SUCCURSALE, "Domino's Pizza Wilfrid-Hamel");
        values2.put(COLUMN_PIZZERIA_TELEPHONE, "+14186813363");
        values2.put(COLUMN_PIZZERIA_ADRESSE, "680 Bd Wilfrid-Hamel, Québec, QC G1M 2P9");

        //insert
        sqLiteDatabase.insert(PIZZERIAS_TABLE_NAME, null, values2);

        ContentValues values3 = new ContentValues();
        values3.put(COLUMN_PIZZERIA_SUCCURSALE, "Domino's Pizza l'Ormière");
        values3.put(COLUMN_PIZZERIA_TELEPHONE, "+14188431444");
        values3.put(COLUMN_PIZZERIA_ADRESSE, "9065 Bd de l'Ormière, Québec, QC G2B 3K2");

        //insert
        sqLiteDatabase.insert(PIZZERIAS_TABLE_NAME, null, values3);

    }
}
