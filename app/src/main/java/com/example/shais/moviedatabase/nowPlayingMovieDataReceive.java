package com.example.shais.moviedatabase;

import com.example.shais.moviedatabase.data_items.Movie;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Shaishav on 3/24/2018.
 */

public interface nowPlayingMovieDataReceive {

    public void onNowPlayingArrive(ArrayList<Movie> movies);

}
