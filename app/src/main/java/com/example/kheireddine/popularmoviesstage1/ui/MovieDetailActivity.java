package com.example.kheireddine.popularmoviesstage1.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kheireddine.popularmoviesstage1.R;
import com.example.kheireddine.popularmoviesstage1.api.MovieDBServiceAPI;
import com.example.kheireddine.popularmoviesstage1.model.Movie;
import com.example.kheireddine.popularmoviesstage1.utils.Utils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends MainActivity {
    @BindView(R.id.iv_backdrop) ImageView ivBackdrop;
    @BindView(R.id.iv_poster_detail) ImageView ivPosetr;
    @BindView(R.id.tv_title_detail) TextView tvTitle;
    @BindView(R.id.tv_synopsis) TextView tvSynopsis;
    private Movie mMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        long movieId = getIntent().getExtras().getLong("movie_id");
        httpGetMovieDetails(movieId);



    }



    /**************************************************************************************************
     *                                            HTTP calls
     ************************************************************************************************/
    public void httpGetMovieDetails(long movieId) {
        Call<Movie> call = mdbAPI.getMovieDetails(movieId);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                // retrieve the selected movie
                mMovie = response.body();
                // set the title
                tvTitle.setText(mMovie.getTitle());
                // set synopsis
                tvSynopsis.setText(mMovie.getSynopsis());
                // set the poster
                Picasso.with(mContext)
                        .load(MovieDBServiceAPI.API_POSTER_HEADER_LARGE +mMovie.getPoster())
                        .into(ivPosetr);
                // set the background
                Picasso.with(mContext)
                        .load(MovieDBServiceAPI.API_BACKDROP_HEADER+mMovie.getBackdrop())
                        .into(ivBackdrop);


            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d(Utils.TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    public void onClickButtonBackdrop(View view) {
        Utils.showShortToastMessage(mContext,"Soon...");
    }
}
