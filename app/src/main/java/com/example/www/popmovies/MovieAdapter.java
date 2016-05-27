package com.example.www.popmovies;

import android.app.Activity;
import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.www.popmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jaskaran on 27/5/16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<Movie> mMovieList = new ArrayList<>();
    private Activity mAct;
    private Context mContext;

    public MovieAdapter(ArrayList<Movie> mMovieList, Activity activity){
        this.mMovieList = mMovieList;
        this.mAct = activity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_poster, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        holder.title.setText(movie.getTitle());
        Picasso.with(mContext)
                .load(movie.getPoster_path())
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView poster;
        public TextView title;

        public ViewHolder(View view){
            super(view);
            poster = (ImageView) view.findViewById(R.id.image_poster);
            title = (TextView) view.findViewById(R.id.movie_title);
        }

    }

}
