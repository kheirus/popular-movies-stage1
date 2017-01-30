package com.example.kheireddine.popularmoviesstage1.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.kheireddine.popularmoviesstage1.R;
import com.example.kheireddine.popularmoviesstage1.api.ITmdbAPI;
import com.example.kheireddine.popularmoviesstage1.api.ServiceAPI;
import com.example.kheireddine.popularmoviesstage1.model.MoviesResults;
import com.example.kheireddine.popularmoviesstage1.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_test_title) TextView tvTeesTitle;
    private Context mContext;
    private ITmdbAPI tmdbAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        tmdbAPI = ServiceAPI.createService(ITmdbAPI.class);

        Call<MoviesResults> call = tmdbAPI.getPopluarMovies("popularity.desc");
        call.enqueue(new Callback<MoviesResults>() {
            @Override
            public void onResponse(Call<MoviesResults> call, Response<MoviesResults> response) {
                Log.d(Utils.TAG,"onResponse = "+response.body().getmMoviesResults().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<MoviesResults> call, Throwable t) {
                Log.d(Utils.TAG,"onFailure = "+t.getMessage());
            }
        });
    }


}

