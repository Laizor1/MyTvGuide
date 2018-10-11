package com.example.stefan.newapp3;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

/**
 * Created by Stefan on 4/2/2018.
 */
@Entity(tableName = "sgjoin",
        primaryKeys = { "join_show", "join_genre" },
        foreignKeys = {
                @ForeignKey(entity = Shows.class,
                        parentColumns = "shows_show_name",
                        childColumns = "join_show"),
                @ForeignKey(entity = Genres.class,
                        parentColumns = "genres_genre_name",
                        childColumns = "join_genre")
        }
)
public class SGJoin {

    public SGJoin(String showName,String showGenre)
    {
        this.showName=showName;
        this.showGenre=showGenre;
    }

    @NonNull @ColumnInfo(name="join_show")
    private String showName;
    @NonNull @ColumnInfo(name="join_genre")
    private String showGenre;

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowGenre() {
        return showGenre;
    }

    public void setShowGenre(String showGenre) {
        this.showGenre = showGenre;
    }

}
