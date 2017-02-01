package com.example.kheireddine.popularmoviesstage1.api;

import com.example.kheireddine.popularmoviesstage1.model.Movie;
import com.example.kheireddine.popularmoviesstage1.model.MoviesResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kheireddine on 30/01/17.
 */

public interface IMovieDBRestAPI {
    //http://api.themoviedb.org/3/movie/popular?api_key=999999999999999999
    @GET("movie/{sort_by}")
    Call<MoviesResults> getPopluarMovies(@Path("sort_by") String sortBy);

    //http://api.themoviedb.org/3/movie/123?api_key=99999999999999999999
    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id")long movieId);
}
