package com.example.tp2appmobile.BD;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Pizzeria implements Parcelable {

    private    Long COLUMN_ID;
    private   String COLUMN_PIZZERIA_SUCCURSALE;
    private   String COLUMN_PIZZERIA_TELEPHONE;
    private   String COLUMN_PIZZERIA_ADRESSE;

    public Pizzeria() {
    }

    protected Pizzeria(Parcel in) {
        if (in.readByte() == 0) {
            COLUMN_ID = null;
        } else {
            COLUMN_ID = in.readLong();
        }
        COLUMN_PIZZERIA_SUCCURSALE = in.readString();
        COLUMN_PIZZERIA_TELEPHONE = in.readString();
        COLUMN_PIZZERIA_ADRESSE = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (COLUMN_ID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(COLUMN_ID);
        }
        dest.writeString(COLUMN_PIZZERIA_SUCCURSALE);
        dest.writeString(COLUMN_PIZZERIA_TELEPHONE);
        dest.writeString(COLUMN_PIZZERIA_ADRESSE);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pizzeria> CREATOR = new Creator<Pizzeria>() {
        @Override
        public Pizzeria createFromParcel(Parcel in) {
            return new Pizzeria(in);
        }

        @Override
        public Pizzeria[] newArray(int size) {
            return new Pizzeria[size];
        }
    };

    //get sets ____________________________________________________________________
    public Long getCOLUMN_ID() {
        return COLUMN_ID;
    }

    public void setCOLUMN_ID(Long COLUMN_ID) {
        this.COLUMN_ID = COLUMN_ID;
    }

    public String getCOLUMN_PIZZERIA_SUCCURSALE() {
        return COLUMN_PIZZERIA_SUCCURSALE;
    }

    public void setCOLUMN_PIZZERIA_SUCCURSALE(String COLUMN_PIZZERIA_SUCCURSALE) {
        this.COLUMN_PIZZERIA_SUCCURSALE = COLUMN_PIZZERIA_SUCCURSALE;
    }

    public String getCOLUMN_PIZZERIA_TELEPHONE() {
        return COLUMN_PIZZERIA_TELEPHONE;
    }

    public void setCOLUMN_PIZZERIA_TELEPHONE(String COLUMN_PIZZERIA_TELEPHONE) {
        this.COLUMN_PIZZERIA_TELEPHONE = COLUMN_PIZZERIA_TELEPHONE;
    }

    public String getCOLUMN_PIZZERIA_ADRESSE() {
        return COLUMN_PIZZERIA_ADRESSE;
    }

    public void setCOLUMN_PIZZERIA_ADRESSE(String COLUMN_PIZZERIA_ADRESSE) {
        this.COLUMN_PIZZERIA_ADRESSE = COLUMN_PIZZERIA_ADRESSE;
    }

    @Override
    public String toString() {
        return "Pizzeria{" +
                "COLUMN_ID=" + COLUMN_ID +
                ", COLUMN_PIZZERIA_SUCCURSALE='" + COLUMN_PIZZERIA_SUCCURSALE + '\'' +
                ", COLUMN_PIZZERIA_TELEPHONE='" + COLUMN_PIZZERIA_TELEPHONE + '\'' +
                ", COLUMN_PIZZERIA_ADRESSE='" + COLUMN_PIZZERIA_ADRESSE + '\'' +
                '}';
    }
}
