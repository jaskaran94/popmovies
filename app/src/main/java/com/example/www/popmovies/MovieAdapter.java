package com.example.www.popmovies;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.www.popmovies.model.Movie;

import java.util.ArrayList;

/**
 * Created by jaskaran on 27/5/16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<Movie> mMovieList = new ArrayList<>();
    private Context mContext;
    private Activity mActivity;
    private onAdapterItemSelectedListener mAdapterCallback;

    public MovieAdapter(ArrayList<Movie> mMovieList, Context context, Activity activity){
        this.mMovieList = mMovieList;
        this.mContext = context;
        this.mActivity = activity;
        mAdapterCallback = (onAdapterItemSelectedListener) mActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_poster, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public interface onAdapterItemSelectedListener{
        void onItemSelected(Movie movie);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView poster;
        public TextView title;

        public ViewHolder(View view){
            super(view);
            poster = (ImageView) view.findViewById(R.id.image_poster);
            title = (TextView) view.findViewById(R.id.movie_title);
            Typeface roboto = Typeface.createFromAsset(mContext.getAssets(), "Roboto-Medium.ttf");
            title.setTypeface(roboto);
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Movie movie = mMovieList.get(position);
        holder.title.setText(movie.getTitle());
        Glide.with(mContext)
                .load(movie.getPoster_path())
                .fitCenter()
                .into(holder.poster);
        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie1 = mMovieList.get(position);
                /*Intent detailActivity = new Intent(mContext, MovieDetailActivity.class)
                        .putExtra("movie", movie1);
                mContext.startActivity(detailActivity)*/
                if (mAdapterCallback != null){
                    mAdapterCallback.onItemSelected(movie1);
                }
            }
        });
    }

}
