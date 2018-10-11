package com.example.stefan.newapp3;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;
import java.util.List;

/**
 * Created by Stefan on 5/23/2018.
 */
@Dao
@TypeConverters({Converters.class})
public interface RecentsDao {
    @Query("SELECT * FROM recents ORDER BY date_added")
    List<Recents> getRecents();

    @Query("DELETE FROM recents")
    void nukeTheTable();

    @Query("SELECT COUNT(*) FROM recents")
    int countRecents();

    @Query("SELECT COUNT(*) FROM recents WHERE show_id like :showId")
    int countShowExists(int showId);

    @Query("SELECT MIN(date_added) FROM recents")
    Date getOldestDate();
    @Query("DELETE FROM recents WHERE date_added = :dateAdded")
    void deleteOldest(Date dateAdded);

    @Query("UPDATE recents SET date_added = :newDate WHERE show_id = :showId")
    void updateDate(int showId,Date newDate);
    @Insert
    void insertAll(Recents... recents);
}
