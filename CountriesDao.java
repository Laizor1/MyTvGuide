package com.example.stefan.newapp3;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Stefan on 5/11/2018.
 */
@Dao
public interface CountriesDao {
    @Query("SELECT * FROM countries")
    List<Countries> getCountries();

    @Query("DELETE FROM countries")
    void nukeTheTable();

    @Query("SELECT COUNT(*) FROM COUNTRIES")
    int testCount();

    @Insert
    void insertAll(Countries... countries);
}