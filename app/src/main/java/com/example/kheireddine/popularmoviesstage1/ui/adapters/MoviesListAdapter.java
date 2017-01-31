package com.example.kheireddine.popularmoviesstage1.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kheireddine.popularmoviesstage1.R;
import com.example.kheireddine.popularmoviesstage1.api.ServiceAPI;
import com.example.kheireddine.popularmoviesstage1.model.Movie;
import com.example.kheireddine.popularmoviesstage1.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kheireddine on 30/01/17.
 */

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder> {

    Context mContext;
    List<Movie> moviesList;

    public MoviesListAdapter(Context mContext, List<Movie> moviesList) {
        this.mContext = mContext;
        this.moviesList = moviesList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();
        int layoutIdForMovieItem = R.layout.movie_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(layoutIdForMovieItem,parent,false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie mMovie = moviesList.get(position);
        Log.d(Utils.TAG, "poster =  "+mMovie.getPoster());
        Picasso.with(mContext)
                .load(ServiceAPI.API_POSTER_HEADER+mMovie.getPoster())
                .into(holder.ivPoser);
        holder.tvTitle.setText(mMovie.getTitle());
        holder.itemView.setTag(mMovie);

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_poster) ImageView ivPoser;
        @BindView(R.id.tv_title) TextView tvTitle;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
