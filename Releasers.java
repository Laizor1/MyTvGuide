package com.example.stefan.newapp3;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Stefan on 5/13/2018.
 */
@Entity(tableName = "releasers",
        indices = {@Index(value={"releaser_name"},unique=true)})
public class Releasers {
    public Releasers(String releaserName){
        this.releaserName = releaserName;
    }

    @PrimaryKey(autoGenerate = true)
    private int x;

    @NonNull
    @ColumnInfo(name = "releaser_name")
    private String releaserName;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @NonNull
    public String getReleaserName() {
        return releaserName;
    }

    public void setReleaserName(@NonNull String releaserName) {
        this.releaserName = releaserName;
    }
}
