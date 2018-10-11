package com.example.stefan.newapp3;

/**
 * Created by Stefan on 3/14/2018.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Stefan on 3/5/2018.
 */

@Entity(tableName = "genres",
        indices = {@Index(value = {"genres_genre_name"}, unique = true)}
)
public class Genres {

    public Genres(String genreName) {
        this.genreName = genreName;
    }

    @PrimaryKey(autoGenerate = true)
    private int x;

    @NonNull
    @ColumnInfo(name = "genres_genre_name")
    private String genreName;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}