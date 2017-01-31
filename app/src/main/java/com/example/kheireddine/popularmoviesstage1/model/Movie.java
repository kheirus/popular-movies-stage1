package com.example.kheireddine.popularmoviesstage1.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kheireddine on 30/01/17.
 */

public class Movie {
    @SerializedName("id")
    private long id;
    @SerializedName("original_title")
    private String title;
    @SerializedName("poster_path")
    private String poster;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }
}
