package com.example.movielovers.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Movie implements Parcelable{
    private String poster_path;
    private Boolean adult;
    private String overview;
    private String release_date;
    private ArrayList<Integer> genre_ids;
    private long id;
    private String original_title;
    private String title;

    public float getPopularity() {
        return popularity;
    }

    private String backdrop_path;
    private float popularity;
    private long vote_count;
    private boolean video;
    private float vote_average;

    public String getPoster_path() {
        return poster_path;
    }
    protected Movie(Parcel in) {
        poster_path = in.readString();
        byte tmpAdult = in.readByte();
        adult = tmpAdult == 0 ? null : tmpAdult == 1;
        overview = in.readString();
        release_date = in.readString();
        id = in.readLong();
        original_title = in.readString();
        title = in.readString();
        backdrop_path = in.readString();
        popularity = in.readFloat();
        vote_count = in.readLong();
        video = in.readByte() != 0;
        vote_average = in.readFloat();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public long getVote_count() {
        return vote_count;
    }

    public float getVote_average() {
        return vote_average;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(poster_path);
        parcel.writeByte((byte) (adult == null ? 0 : adult ? 1 : 2));
        parcel.writeString(overview);
        parcel.writeString(release_date);
        parcel.writeLong(id);
        parcel.writeString(original_title);
        parcel.writeString(title);
        parcel.writeString(backdrop_path);
        parcel.writeFloat(popularity);
        parcel.writeLong(vote_count);
        parcel.writeByte((byte) (video ? 1 : 0));
        parcel.writeFloat(vote_average);
    }
}

