package com.example.shais.moviedatabase.data_items;

import java.util.List;

/**
 * Created by Shaishav on 3/24/2018.
 */

public class Movie {
    String id;
    String name, posterpath;
    String popularity;
    List<Integer> genres;

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
}
