package com.example.kheireddine.popularmoviesstage1.api;

import com.example.kheireddine.popularmoviesstage1.model.MoviesResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kheireddine on 30/01/17.
 */

public interface IMovieDBRestAPI {
    @GET("discover/movie")
    Call<MoviesResults> getPopluarMovies(@Query("sort_by") String sortBy);
}
