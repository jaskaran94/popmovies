package com.example.www.popmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.www.popmovies.adapters.MovieAdapter;
import com.example.www.popmovies.model.Movie;

public class MainActivity extends AppCompatActivity implements MovieAdapter.onAdapterItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MovieFragment())
                    .commit();
        }

    }

    @Override
    public void onItemSelected(Movie movie) {
        Intent movieDetailActivity = new Intent(this, MovieDetailActivity.class)
                .putExtra("movie", movie);
        startActivity(movieDetailActivity);
    }
}
