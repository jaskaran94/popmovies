package com.example.www.popmovies;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
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
    Activity mActivity;
    TextView title;
    //private ArrayList<Trailers> mTrailerList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = getActivity();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.movie_detail_fragment, container, false);
        final Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra("movie")) {
            movie = intent.getExtras().getParcelable("movie");
            displayInfo(rootView);
        }

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(title.getText());
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
                .fitCenter()
                .into(posterImage);

        title = (TextView) v.findViewById(R.id.movie_title);
        title.setText(movie.getTitle());
        Typeface titleFont = Typeface.createFromAsset(mActivity.getAssets(), "Roboto-Regular.ttf");
        title.setTypeface(titleFont);

        Typeface descFont = Typeface.createFromAsset(mActivity.getAssets(), "Roboto-Light.ttf");

        final TextView tagLine = (TextView) v.findViewById(R.id.tag_line);
        tagLine.setText("Its good to be bad");
        tagLine.setTypeface(descFont);

        final TextView releaseDate = (TextView) v.findViewById(R.id.release_date);
        releaseDate.setText(movie.getRelease_date());
        releaseDate.setTypeface(descFont);

        final TextView voteAverage = (TextView) v.findViewById(R.id.vote_average);
        voteAverage.setText(movie.getVote_average() + "/10");
        voteAverage.setTypeface(descFont);

        final TextView movieDesc = (TextView) v.findViewById(R.id.movie_desc);
        movieDesc.setText(movie.getOverview());
        movieDesc.setTypeface(descFont);
    }

    /*private void parseVideos(String url){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray mResultArray = response.getJSONArray("results");
                    for (int i=0; i<mResultArray.length(); ++i){
                        JSONObject mResultObject = mResultArray.getJSONObject(i);
                        mResultObject.getString("id");
                        mResultObject.getString("key");
                        mResultObject.getString("name");
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        })
    }*/

}
