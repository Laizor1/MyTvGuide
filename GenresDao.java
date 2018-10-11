package com.example.stefan.newapp3;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Stefan on 3/26/2018.
 */
@Dao
public interface GenresDao {
    @Query("SELECT * FROM genres ORDER BY genres_genre_name")
    List<Genres> getGenres();

    @Query("DELETE FROM genres")
    void nukeTheTable();

    @Query("SELECT COUNT(*) FROM genres")
    int countTest();

    @Insert
    void insertAll(Genres... genres);
}