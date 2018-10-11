package com.example.stefan.newapp3;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Stefan on 5/11/2018.
 */
@Dao
public interface ReleasersDao {
    @Query("SELECT * FROM releasers")
    List<Releasers> getReleasers();

    @Query("DELETE FROM releasers")
    void nukeTheTable();

    @Query("SELECT COUNT(*) FROM releasers")
    int testCount();

    @Insert
    void insertAll(Releasers... releasers);
}
