package com.example.www.popmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jaskaran on 27/5/16.
 */
public class Movie implements Parcelable{

    public static final Creator CREATOR = new Creator() {

        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    String id;
    String title;
    String overview;
    String release_date;
    String poster_path;
    String backdrop_path;
    String vote_average;

    public Movie(String id, String title, String overview, String release_date, String poster_path, String backdrop_path, String vote_average){
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.vote_average = vote_average;
    }

    public Movie(Parcel in) {
        String[] data = new String[7];

        in.readStringArray(data);
        this.id = data[0];
        this.title = data[1];
        this.overview = data[2];
        this.release_date = data[3];
        this.poster_path = data[4];
        this.backdrop_path = data[5];
        this.vote_average = data[6];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                this.id,
                this.title,
                this.overview,
                this.release_date,
                this.poster_path,
                this.backdrop_path,
                this.vote_average
        });
    }
}
