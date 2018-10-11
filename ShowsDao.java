package com.example.stefan.newapp3;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.*;

/**
 * Created by Stefan on 3/5/2018.
 */
@Dao
public interface ShowsDao {

    @Query("SELECT * FROM shows")
    List<Shows> getAllShows();

    @Query("SELECT x FROM shows WHERE shows_show_name like :showName")
    int getIdByName(String showName);

    @Query("SELECT COUNT(*) FROM shows WHERE shows_show_name LIKE :showName")
    int showExists(String showName);

    @Query("SELECT * FROM shows WHERE x like :showId")
    Shows getShowById(int showId);

    @Query("SELECT * FROM shows ORDER BY shows_show_name")
    List<Shows>getShowsOrderedByName();

    @Query("SELECT * FROM shows WHERE show_year < 1999")
    List<Shows> getless();

    @Query("SELECT * FROM shows WHERE show_genre LIKE :showGenre")
    List<Shows>getByGenre(String showGenre);

    @Query("SELECT * FROM shows WHERE shows_show_name LIKE :showName ")
    Shows findShow(String showName);

    @Query("SELECT * FROM shows WHERE shows_show_name LIKE :showName ")
    List<Shows> findShows(String showName);

    @Query("SELECT COUNT(shows_show_name) FROM shows")
    int showsNumber();

    @Query("DELETE FROM shows")
    void nukeTable();

    @Insert
    void insertAll(Shows... shows);
}