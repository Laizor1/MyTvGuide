package com.example.stefan.newapp3;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Stefan on 4/18/2018.
 */

@Entity(tableName = "actors",
        indices = {@Index(value = {"actor_name"}, unique = true)}
)
public class Actors {

    public Actors(String actorName, String actorBirth, String actorDesc, String actorUrl) {
        this.actorName = actorName;
        this.actorBirth = actorBirth;
        this.actorDesc = actorDesc;
        this.actorUrl = actorUrl;
    }

    @PrimaryKey(autoGenerate = true)
    private int x;
    @NonNull
    @ColumnInfo(name = "actor_name")
    private String actorName;
    @ColumnInfo(name = "birth")
    private String actorBirth;
    @ColumnInfo(name = "actor_desc")
    private String actorDesc;
    @ColumnInfo(name = "imgsrc")
    private String actorUrl;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getActorBirth() {
        return actorBirth;
    }

    public void setActorBirth(String actorBirth) {
        this.actorBirth = actorBirth;
    }

    public String getActorDesc() {
        return actorDesc;
    }

    public void setActorDesc(String actorDesc) {
        this.actorDesc = actorDesc;
    }

    public String getActorUrl() {
        return actorUrl;
    }

    public void setActorUrl(String actorUrl) {
        this.actorUrl = actorUrl;
    }
}