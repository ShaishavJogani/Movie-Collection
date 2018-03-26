package com.example.shais.moviedatabase.data_items;

import android.util.Log;

import com.example.shais.moviedatabase.utils.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shaishav on 3/24/2018.
 */

public class Movie {

    private final String TAG = "Movie";

    String id;
    String name, posterpath;
    String popularity;
    List<Integer> genresId;
    List<String> genres;


    public Movie() {
        genresId = new ArrayList<>();
        genres = new ArrayList<>();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPosterpath() {
        return posterpath;
    }

    public void setPosterpath(String posterpath) {
        this.posterpath = posterpath;
    }

    public void addGenres(int id) {
        genresId.add(id);
        genres.add(Config.getGenre(id));
    }

    public String getGeners() {
        StringBuilder build = new StringBuilder();
        for (String g : genres)
            build.append(g + ", ");
        String genres = build.toString();
        return (genres.length() > 2) ? genres.substring(0, genres.length() - 2) : genres;

    }
}
