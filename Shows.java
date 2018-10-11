package com.example.stefan.newapp3;

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
@Entity(tableName = "Shows",
        indices = {@Index(value = {"shows_show_name"}, unique = true)},
        foreignKeys = {
                @ForeignKey(entity = Countries.class,
                        parentColumns = "country_name",
                        childColumns = "country",
                        onDelete = CASCADE),
                @ForeignKey(entity = Releasers.class,
                        parentColumns = "releaser_name",
                        childColumns = "show_releaser",
                        onDelete = CASCADE)
        })
public class Shows {
    public Shows(String showName, String showYear, String showEnd, String showGenre, String showDesc, int genreRating, double imdbRating, String imgSrc, String country, String duration, String showReleaser ,boolean showCompleted) {
        this.showName = showName;
        this.showYear = showYear;
        this.showGenre = showGenre;
        this.showDesc = showDesc;
        this.genreRating = genreRating;
        this.imdbRating = imdbRating;
        this.imgSrc = imgSrc;
        this.country = country;
        this.duration = duration;
        this.showEnd = showEnd;
        this.showReleaser = showReleaser;
        this.showCompleted=showCompleted;
    }

    @PrimaryKey(autoGenerate = true)
    private int x;

    @ColumnInfo(name = "show_end")
    private String showEnd;

    @ColumnInfo(name = "show_completed")
    private boolean showCompleted;

    @ColumnInfo(name = "imdb_rating")
    private double imdbRating;

    @ColumnInfo(name = "genre_rating")
    private int genreRating;

    @ColumnInfo(name = "image_source")
    private String imgSrc;

    @NonNull
    @ColumnInfo(name = "shows_show_name")
    private String showName;

    @ColumnInfo(name = "show_synopsis")
    private String showDesc;
    @NonNull
    @ColumnInfo(name = "show_year")
    private String showYear;

    @ColumnInfo(name = "show_genre")
    private String showGenre;

    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "duration")
    private String duration;

    @ColumnInfo(name = "show_releaser")
    private String showReleaser;



    public String getShowEnd() {
        return showEnd;
    }

    public void setShowEnd(String showEnd) {
        this.showEnd = showEnd;
    }

    public boolean isShowCompleted() {
        return showCompleted;
    }

    public void setShowCompleted(boolean showCompleted) {
        this.showCompleted = showCompleted;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int getGenreRating() {
        return genreRating;
    }

    public void setGenreRating(int genreRating) {
        this.genreRating = genreRating;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getShowDesc() {
        return showDesc;
    }

    public void setShowDesc(String showDesc) {
        this.showDesc = showDesc;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowYear() {
        return showYear;
    }

    public void setShowYear(String showYear) {
        this.showYear = showYear;
    }

    public String getShowGenre() {
        return showGenre;
    }

    public void setShowGenre(String showGenre) {
        this.showGenre = showGenre;
    }

    public String getShowReleaser() {
        return showReleaser;
    }

    public void setShowReleaser(String showReleaser) {
        this.showReleaser = showReleaser;
    }
}
