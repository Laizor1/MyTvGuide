package com.example.stefan.newapp3;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Stefan on 5/23/2018.
 */
@Entity(tableName = "recents",
        indices = {@Index(value = {"show_id"}, unique = true)},
        foreignKeys = {
                @ForeignKey(entity = Shows.class,
                        parentColumns = "x",
                        childColumns = "show_id",
                        onDelete = CASCADE)}
                )
@TypeConverters({Converters.class})
public class Recents {
    public Recents(int showId,Date dateAdded){
        this.showId=showId;
        this.dateAdded=dateAdded;
    }
    @PrimaryKey(autoGenerate = true)
    private int x;
    @NonNull
    @ColumnInfo(name="show_id")
    private int showId;
    @ColumnInfo(name="date_added")
    private Date dateAdded;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
