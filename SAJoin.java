package com.example.stefan.newapp3;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

/**
 * Created by Stefan on 4/18/2018.
 */
@Entity(tableName = "sajoin",
        primaryKeys = { "join_s", "join_a" },
        foreignKeys = {
                @ForeignKey(entity = Shows.class,
                        parentColumns = "shows_show_name",
                        childColumns = "join_s"),
                @ForeignKey(entity = Actors.class,
                        parentColumns = "actor_name",
                        childColumns = "join_a")
        }
)
public class SAJoin {
        public SAJoin(String saShowName,String saActorName,String actorRole) {
                this.saShowName=saShowName;
                this.saActorName=saActorName;
                this.actorRole=actorRole;
        }

    @NonNull @ColumnInfo(name="join_s")
    private String saShowName;
    @NonNull @ColumnInfo(name="join_a")
    private String saActorName;
    @ColumnInfo(name="join_role")
    private String actorRole;

    @NonNull
    public String getSaShowName() {
        return saShowName;
    }

    public void setSaShowName(@NonNull String saShowName) {
        this.saShowName = saShowName;
    }

    @NonNull
    public String getSaActorName() {
        return saActorName;
    }

    public void setSaActorName(@NonNull String saActorName) {
        this.saActorName = saActorName;
    }

    public String getActorRole() {
        return actorRole;
    }

    public void setActorRole(String actorRole) {
        this.actorRole = actorRole;
    }
}
