package com.example.stefan.newapp3;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Stefan on 3/14/2018.
 */

@Dao
public interface PersonalListDao {

    @Query("SELECT * FROM plist ORDER BY show_name")
    List<PersonalList> getAllShows();

    @Query("SELECT COUNT(*) FROM plist WHERE show_name LIKE :showName")
    int showExists(String showName);

    @Query("SELECT * FROM plist WHERE show_name LIKE :showName")
    List<PersonalList> getByName(String showName);

    @Query("DELETE FROM plist WHERE show_name = :showName")
    void deleteByName(String showName);

    @Query("UPDATE plist SET is_watched = :isWatched WHERE show_name = :showName")
    void updateWatched(boolean isWatched,String showName);

    @Query("SELECT COUNT(show_name) FROM plist")
    int showsNumber();

    @Query("DELETE FROM plist")
    void nukeTheTable();

    @Insert
    void insertShow(PersonalList plist);

    @Insert
    void insertAll(PersonalList... personalLists);


}

