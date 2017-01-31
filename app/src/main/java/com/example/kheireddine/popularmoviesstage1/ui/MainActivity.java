package com.example.kheireddine.popularmoviesstage1.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.kheireddine.popularmoviesstage1.R;
import com.example.kheireddine.popularmoviesstage1.api.IMovieDBRestAPI;
import com.example.kheireddine.popularmoviesstage1.api.MovieDBServiceAPI;
import com.example.kheireddine.popularmoviesstage1.model.Movie;
import com.example.kheireddine.popularmoviesstage1.model.MoviesResults;
import com.example.kheireddine.popularmoviesstage1.ui.adapters.MoviesListAdapter;
import com.example.kheireddine.popularmoviesstage1.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_movies_list)
    RecyclerView rvMovieList;
    private Context mContext;
    private IMovieDBRestAPI tmdbAPI;
    private List<Movie> mMoviesList;
    private MoviesListAdapter mAdapter;
    private String SORT_BY = MovieDBServiceAPI.SORT_BY_DEFAULT;
    private final static int NB_CELL = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        tmdbAPI = MovieDBServiceAPI.createService(IMovieDBRestAPI.class);

        setToolBar();
        setLayoutManager();

        if (Utils.isOnline(mContext)){
            if (Utils.isValidApiKey())
                httpGetMovies(SORT_BY);
                // invalid API_KEY
            else {
                Utils.showDialog(MainActivity.this,getString(R.string.dialog_error_api_key_title),getString(R.string.dialog_error_api_key_message));

            }
        }
        // No network
        else {
            Utils.showDialog(MainActivity.this,getString(R.string.dialog_error_network_title),getString(R.string.dialog_error_network_message));

        }



    }


    public void setLayoutManager() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, NB_CELL);
        rvMovieList.setLayoutManager(gridLayoutManager);
        rvMovieList.setHasFixedSize(true);
    }

    private void setRecyclerAdapter(RecyclerView recyclerView) {
        mAdapter = new MoviesListAdapter(mContext, mMoviesList);
        recyclerView.setAdapter(mAdapter);
    }

    private void setToolBar(){
        getSupportActionBar().setTitle(getString(R.string.toolbar_pop_movies));
    }

    /**Menu*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_sort_by_popularity:
                Utils.showShortToastMessage(mContext, "pop");
                SORT_BY = MovieDBServiceAPI.SORT_BY_POPOLARITY;

                httpGetMovies(SORT_BY);
                return true;

            case R.id.item_sort_by_top_rated:
                Utils.showShortToastMessage(mContext, "top");
                SORT_BY = MovieDBServiceAPI.SORT_BY_TOP_RATED;
                item.setChecked(true);
                httpGetMovies(SORT_BY);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    /**************************************************************************************************
     *                                  HTTP calls
     * ************************************************************************************************/
    public void httpGetMovies(String sortBy){
        Call<MoviesResults> call = tmdbAPI.getPopluarMovies(sortBy);
        call.enqueue(new Callback<MoviesResults>() {
            @Override
            public void onResponse(Call<MoviesResults> call, Response<MoviesResults> response) {
                Log.d(Utils.TAG, "onResponse = " + response.body().getmMoviesResults().get(0).getTitle());
                if (response.isSuccessful()){
                    mMoviesList = response.body().getmMoviesResults();
                    if(mMoviesList.size()!=0){
                        setRecyclerAdapter(rvMovieList);
                    }
                    else {
                        //TODO empty list error
                    }
                }
                else {
                    //TODO http response error
                }
            }

            @Override
            public void onFailure(Call<MoviesResults> call, Throwable t) {
                Utils.showLongToastMessage(mContext,"Error fetching movies :"+t.getMessage());
            }
        });

    }
}

