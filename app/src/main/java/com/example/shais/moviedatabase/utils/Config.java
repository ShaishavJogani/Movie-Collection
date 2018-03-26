package com.example.shais.moviedatabase.utils;

import java.util.HashMap;

/**
 * Created by Shaishav on 3/24/2018.
 */

public class Config {

    public static String getNowPlayingURI() {
        return getNowPlayingURI(1);
    }

    public static String getNowPlayingURI(int pageno) {
        return "https://api.themoviedb.org/3/movie/now_playing?api_key=2f27c7c784ab36584f75672a7537bf53&language=en-US&page=" + pageno;
    }


    public static String getUpcomingURI() {
        return getUpcomingURI(1);
    }

    public static String getUpcomingURI(int pageno) {
        return "https://api.themoviedb.org/3/movie/upcoming?api_key=2f27c7c784ab36584f75672a7537bf53&language=en-US&page=" + pageno;
    }

    private static HashMap<Integer, String> genres;

    private static void createGneres() {
        genres = new HashMap<>();
        genres.put(28, "Action");
        genres.put(12, "Adventure");
        genres.put(16, "Animation");
        genres.put(35, "Comedy");
        genres.put(80, "Crime");
        genres.put(99, "Documentary");
        genres.put(18, "Drama");
        genres.put(10751, "Family");
        genres.put(14, "Fantasy");
        genres.put(36, "History");
        genres.put(27, "Horror");
        genres.put(10402, "Music");
        genres.put(9648, "Mystery");
        genres.put(10749, "Romance");
        genres.put(878, "Science Fiction");
        genres.put(10770, "TV Movie");
        genres.put(53, "Thriller");
        genres.put(10752, "War");
        genres.put(37, "Western");
    }

    public static String getGenre(int id) {
        if (genres == null)
            createGneres();
        return genres.get(id);
    }

}
