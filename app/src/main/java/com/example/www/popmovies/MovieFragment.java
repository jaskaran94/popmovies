package com.example.www.popmovies;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.www.popmovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private RequestQueue mRequestQueue;

    private int firstVisibleItem, visibleItemCount, totalItemCount;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 4;
    private int pageCount = 1;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("mMovieList", mMovieList);
        outState.putInt("pageCount", pageCount);
        outState.putInt("previousTotal", previousTotal);
        outState.putInt("firstVisibleItem", firstVisibleItem);
        outState.putInt("visibleItemCount", visibleItemCount);
        outState.putInt("totalItemCount", totalItemCount);
        outState.putBoolean("loading", loading);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            mMovieList = savedInstanceState.getParcelableArrayList("mMovieList");
            pageCount = savedInstanceState.getInt("pageCount");
            previousTotal = savedInstanceState.getInt("previousTotal");
            firstVisibleItem = savedInstanceState.getInt("firstVisibleItem");
            visibleItemCount = savedInstanceState.getInt("visibleItemCount");
            totalItemCount = savedInstanceState.getInt("totalItemCount");
            loading = savedInstanceState.getBoolean("loading");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_fragment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.movieview);

        mRequestQueue = Volley.newRequestQueue(getActivity());
        mAdapter = new MovieAdapter(mMovieList, getActivity(), getActivity());
        mGridLayoutManager = new GridLayoutManager(getActivity(), 2);

        url = getResources().getString(R.string.base_url) + getResources().getString(R.string.api_key) + getResources().getString(R.string.sort);
        getMovieList(url);
        setUpRecyclerView(mRecyclerView);

        return rootView;

    }


    private void getMovieList(String url){
        JsonObjectRequest getListData = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray mResultArray = response.getJSONArray("results");
                    for (int i = 0; i < mResultArray.length(); i++) {
                        JSONObject mResultObject = mResultArray.getJSONObject(i);
                        Movie movie = new Movie(mResultObject.getString("id"),
                                mResultObject.getString("original_title"),
                                mResultObject.getString("overview"),
                                mResultObject.getString("release_date"),
                                getResources().getString(R.string.poster_path_url) + mResultObject.getString("poster_path"),
                                getResources().getString(R.string.bacdrop_path_url) + mResultObject.getString("backdrop_path"),
                                mResultObject.getString("vote_average"));
                        mMovieList.add(movie);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(LOG_TAG, "error parsing JSON");
            }
        });
        mRequestQueue.add(getListData);
    }

    private void setUpRecyclerView(RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setLayoutManager(mGridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = mRecyclerView.getChildCount();
                totalItemCount = mGridLayoutManager.getItemCount();
                firstVisibleItem = mGridLayoutManager.findFirstVisibleItemPosition();

                if (loading){
                    if(totalItemCount > previousTotal){
                        loading = false;
                        previousTotal = totalItemCount;
                        pageCount++;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)){
                    String url = getResources().getString(R.string.base_url) + getResources().getString(R.string.api_key) + getResources().getString(R.string.sort) + "&page=" + pageCount;
                    getMovieList(url);
                    loading = true;
                }
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

}
