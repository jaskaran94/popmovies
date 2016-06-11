package com.example.www.popmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.www.popmovies.model.Movie;

;

/**
 * Created by jaskaran on 1/6/16.
 */
public class MovieDetailFragment extends Fragment {

    Movie movie;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.movie_detail_fragment, container, false);
        final Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("details");

        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra("movie")) {
            movie = intent.getExtras().getParcelable("movie");
            displayInfo(rootView);
        }
        return rootView;
    }

    private void displayInfo(View v){
        final ImageView backdropImage = (ImageView) v.findViewById(R.id.backdrop);
        Glide.with(getActivity())
                .load(movie.getBackdrop_path())
                .centerCrop()
                .into(backdropImage);

        final ImageView posterImage = (ImageView) v.findViewById(R.id.image_poster);
        Glide.with(getActivity())
                .load(movie.getPoster_path())
                .centerCrop()
                .into(posterImage);

        final TextView title = (TextView) v.findViewById(R.id.movie_title);
        title.setText(movie.getTitle());

        final TextView movieDesc = (TextView) v.findViewById(R.id.movie_desc);
        movieDesc.setText(movie.getOverview());
    }
}
