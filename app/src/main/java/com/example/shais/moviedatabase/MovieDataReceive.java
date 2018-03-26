package com.example.shais.moviedatabase;

import com.example.shais.moviedatabase.data_items.Movie;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Shaishav on 3/24/2018.
 */

public interface MovieDataReceive {

    /**
     * Called when the "now playing movies" data arrive and parse.
     * @param movies List of movies
     */
    public void moviesDataArrive(ArrayList<Movie> movies);

}
