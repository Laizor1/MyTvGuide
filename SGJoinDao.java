package com.example.stefan.newapp3;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Stefan on 4/2/2018.
 */
@Dao
public interface SGJoinDao {

    @Insert
    void insertAll(SGJoin... sgJoins);

    @Query("SELECT * FROM sgjoin WHERE join_show=:showName")
    List<SGJoin> getByName(String showName);
    @Query("SELECT * FROM sgjoin ORDER BY join_show")
    List<SGJoin> getAllJoins();

    @Query("SELECT genres.genres_genre_name FROM genres inner join sgjoin on genres.genres_genre_name=sgjoin.join_genre where sgjoin.join_show like :showName")
    List<String> getGenresByName(String showName);

    @Query("SELECT count(*) FROM sgjoin  ")
    int getCount();

    @Query("SELECT shows.* FROM shows INNER JOIN sgjoin on shows.shows_show_name=sgjoin.join_show WHERE sgjoin.join_genre=:showGenre ORDER BY shows.shows_show_name")
    List<Shows> getByGenre(String showGenre);

    @Query("DELETE FROM sgjoin")
    void nukeTable();
}
