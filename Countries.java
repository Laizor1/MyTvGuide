package com.example.stefan.newapp3;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Stefan on 5/11/2018.
 */
@Entity(tableName = "countries",
        indices = {@Index(value={"country_name"},unique=true)})
public class Countries {

    public Countries(String countryName) {
        this.countryName = countryName;
    }

    @PrimaryKey(autoGenerate = true)
    private int x;

    @NonNull
    @ColumnInfo(name = "country_name")
    private String countryName;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}