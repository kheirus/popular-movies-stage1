package com.example.kheireddine.popularmoviesstage1.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kheireddine.popularmoviesstage1.R;
import com.example.kheireddine.popularmoviesstage1.api.IMovieDBRestAPI;
import com.example.kheireddine.popularmoviesstage1.api.MovieDBServiceAPI;


/**
 * This is a base activity that all other activities have to extend
 * */
public class MainActivity extends AppCompatActivity {
    protected Context mContext;

    protected IMovieDBRestAPI mdbAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getBaseContext();
        mdbAPI = MovieDBServiceAPI.createService(IMovieDBRestAPI.class);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
