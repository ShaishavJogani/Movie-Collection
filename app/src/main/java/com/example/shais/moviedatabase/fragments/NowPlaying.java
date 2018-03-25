package com.example.shais.moviedatabase.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AndroidException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shais.moviedatabase.R;
import com.example.shais.moviedatabase.adapter.MovieDisplayAdapter;
import com.example.shais.moviedatabase.data_items.Movie;
import com.example.shais.moviedatabase.nowPlayingMovieDataReceive;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Shaishav on 3/24/2018.
 */

public class NowPlaying  extends Fragment implements nowPlayingMovieDataReceive {

    ArrayList<Movie> movies;

    RecyclerView list;
    LinearLayoutManager mLayoutManager;
    MovieDisplayAdapter mAdapter;

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

    }


    @Override
    public void onNowPlayingArrive(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
