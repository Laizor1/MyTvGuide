package com.example.stefan.newapp3;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Stefan on 4/18/2018.
 */

@Dao
public interface ActorsDao {
    @Query("SELECT * FROM actors ORDER BY actor_name")
    List<Actors> getActors();

    @Query("SELECT * from actors where actor_name like :actorName")
    List<Actors> getActorsByName(String actorName);

    @Query("SELECT * from actors where actor_name like :actorName")
    Actors getActorByName(String actorName);

    @Query("SELECT COUNT(*) FROM actors")
    int getNumberOfActors();

    @Insert
    void insertAll(Actors... actors);

    @Query("DELETE FROM actors")
    void nukeTheTable();
}
