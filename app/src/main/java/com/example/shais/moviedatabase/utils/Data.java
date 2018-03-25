package com.example.shais.moviedatabase.utils;

/**
 * Created by Shaishav on 3/24/2018.
 */

public class Data {

    public static final String APIKEY_v4 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyZjI3YzdjNzg0YWIzNjU4NGY3NTY3MmE3NTM3YmY1MyIsInN1YiI6IjVhYjZlYTE2OTI1MTQxMzk2MTAxMzhkMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.b6ZeOrVpkpMMhZyuOg1Rd7BGUK6O-NTYgokQ7bgb-Ec";

    public static String getNowPlayingURI() {
        return getNowPlayingURI(1);
    }

    public static String getNowPlayingURI(int pageno) {
        return "https://api.themoviedb.org/3/movie/now_playing?api_key=2f27c7c784ab36584f75672a7537bf53&language=en-US&page="+pageno;
    }


    public static String getUpcomingURI() {
        return getUpcomingURI(1);
    }

    public static String getUpcomingURI(int pageno) {
        return "https://api.themoviedb.org/3/movie/upcoming?api_key=2f27c7c784ab36584f75672a7537bf53&language=en-US&page="+pageno;
    }



}
