package com.example.www.popmovies;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.www.popmovies.model.Movie;

import java.util.ArrayList;

/**
 * Created by jaskaran on 26/5/16.
 */
public class MovieFragment extends Fragment {

    final String LOG_TAG = MovieFragment.class.getSimpleName();

    private ArrayList<Movie> mMovieList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private GridLayoutManager mGridLayoutManager;
    private String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_fragment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.movieview);

        mAdapter = new MovieAdapter(mMovieList, getActivity());
        mGridLayoutManager = new GridLayoutManager(getActivity(), 2);

        return rootView;
    }
}
