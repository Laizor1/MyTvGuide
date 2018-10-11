package com.example.stefan.newapp3;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Stefan on 4/18/2018.
 */
@Dao
public interface SAJoinDao {

    @Insert
    void insertAll(SAJoin... saJoins);

    @Query("SELECT * FROM sajoin WHERE join_s=:showName")
    List<SAJoin> getByName(String showName);
    @Query("SELECT * FROM sajoin ORDER BY join_s")
    List<SAJoin> getAllJoins();
    @Query("SELECT COUNT(*) FROM SAJOIN")
    int countTest();
    @Query("SELECT Shows.* FROM sajoin inner join Shows on Shows.shows_show_name=sajoin.join_s WHERE sajoin.join_a = :actorName")
    List<Shows> getShowsByActor(String actorName);
    @Query("DELETE FROM sajoin")
    void nukeTable();
    @Query("SELECT Actors.* FROM sajoin inner join actors on Actors.actor_name=sajoin.join_a WHERE sajoin.join_s = :showName")
    List<Actors> getActorsByShow(String showName);
}
