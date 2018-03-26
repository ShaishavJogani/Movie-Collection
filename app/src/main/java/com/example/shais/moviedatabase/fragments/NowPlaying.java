package com.example.shais.moviedatabase.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.shais.moviedatabase.R;
import com.example.shais.moviedatabase.adapter.MovieDisplayAdapter;
import com.example.shais.moviedatabase.data_items.Movie;
import com.example.shais.moviedatabase.MovieDataReceive;
import com.example.shais.moviedatabase.utils.Config;
import com.example.shais.moviedatabase.utils.EndlessRecyclerViewScrollListener;
import com.example.shais.moviedatabase.utils.GlobalLoader;
import com.example.shais.moviedatabase.utils.ParseMovies;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Fragment to display now playing movies.
 * Created by Shaishav on 3/24/2018.
 */

public class NowPlaying extends Fragment implements MovieDataReceive {

    private final String TAG = "NowPlaying...";

    ArrayList<Movie> movies;

    RecyclerView list;
    LinearLayoutManager mLayoutManager;
    MovieDisplayAdapter mAdapter;

    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movielist, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = view.findViewById(R.id.list_movies);
        mLayoutManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(mLayoutManager);

        mAdapter = new MovieDisplayAdapter(getContext(), movies);
        list.setAdapter(mAdapter);

        scrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                GetNowPlaying(Config.getNowPlayingURI(page));
            }
        };
        list.addOnScrollListener(scrollListener);
    }


    private void GetNowPlaying(String url) {
        final RequestQueue queue = Volley.newRequestQueue(getContext());
        Log.d(TAG, "Sending request..." + url);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    ArrayList<Movie> mymovies = ParseMovies.parseData(response);
                    movies.addAll(mymovies);
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error occur : " + error.toString() + "\n" + error.getMessage());
            }
        });
        queue.add(jsonRequest);

        return;
    }


    @Override
    public void moviesDataArrive(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
