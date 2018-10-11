package com.example.stefan.newapp3;

/**
 * Created by Stefan on 3/14/2018.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Stefan on 3/5/2018.
 */

@Entity(tableName = "plist",
        foreignKeys = {
                @ForeignKey(entity = Shows.class,
                        parentColumns = "x",
                        childColumns = "x",
                        onDelete = CASCADE)
        })
@TypeConverters({Converters.class})

public class PersonalList {

    public PersonalList(String showName, String showYear, String showGenre, String showDesc,Double imdbRating,String imgSrc,String country,String duration,String showEnd,String showReleaser,boolean isWatched,Date dateAdded) {
        this.showName = showName;
        this.showYear = showYear;
        this.showGenre = showGenre;
        this.showDesc = showDesc;
        this.imdbRating = imdbRating;
        this.imgSrc = imgSrc;
        this.country = country;
        this.duration = duration;
        this.showEnd = showEnd;
        this.showReleaser = showReleaser;
        this.isWatched=isWatched;
        this.dateAdded=dateAdded;
    }

    @PrimaryKey(autoGenerate = true)
    private int x;
    @ColumnInfo(name = "show_end")
    private String showEnd;
    @ColumnInfo(name = "imdb_rating")
    private double imdbRating;
    @ColumnInfo(name = "image_source")
    private String imgSrc;
    @ColumnInfo(name = "country")
    private String country;
    @ColumnInfo(name="is_watched")
    private boolean isWatched;
    @ColumnInfo(name = "duration")
    private String duration;
    @ColumnInfo(name="date_added")
    private Date dateAdded;
    @ColumnInfo(name = "show_releaser")
    private String showReleaser;
    @ColumnInfo(name = "show_name")
    private String showName;

    @ColumnInfo(name = "show_synopsis")
    private String showDesc;

    @ColumnInfo(name = "show_year")
    private String showYear;

    @ColumnInfo(name = "show_genre")
    private String showGenre;

    public int getX() {
        return x;
    }

    public boolean isWatched() {
        return isWatched;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setWatched(boolean watched) {
        isWatched = watched;
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

    public String getShowEnd() {
        return showEnd;
    }

    public void setShowEnd(String showEnd) {
        this.showEnd = showEnd;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getShowReleaser() {
        return showReleaser;
    }

    public void setShowReleaser(String showReleaser) {
        this.showReleaser = showReleaser;
    }
}