package com.example.shais.moviedatabase.utils;

import com.example.shais.moviedatabase.data_items.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Provides method to parse movie data.
 * Created by Shaishav on 3/24/2018.
 */

public class ParseMovies {

    /**
     * Parse the JSON response from the server and returns the list of movies.
     * @param response A JSONObject
     * @return List of movies
     * @throws JSONException
     */
    public static ArrayList<Movie> parseData(JSONObject response) throws JSONException {
        ArrayList<Movie> movies = new ArrayList<>();

        JSONArray movielist = response.getJSONArray(MovieKeys.results);

        for (int i = 0; i < movielist.length(); i++) {
            JSONObject temp = movielist.getJSONObject(i);
            Movie movie = new Movie();
            movie.setId(temp.getString(MovieKeys.movie_id));
            movie.setName(temp.getString(MovieKeys.movie_title));
            movie.setPosterpath(temp.getString(MovieKeys.poster));
            movie.setPopularity(temp.getString(MovieKeys.popularity));

            JSONArray genre = temp.getJSONArray(MovieKeys.genres);
            for (int j = 0; j < genre.length(); j++) {
                movie.addGenres(genre.getInt(j));
            }
            movies.add(movie);
        }
        return movies;
    }


}
