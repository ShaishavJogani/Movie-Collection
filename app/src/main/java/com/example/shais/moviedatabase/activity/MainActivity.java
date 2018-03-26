package com.example.shais.moviedatabase.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.shais.moviedatabase.R;
import com.example.shais.moviedatabase.adapter.ViewPagerAdapter;
import com.example.shais.moviedatabase.data_items.Movie;
import com.example.shais.moviedatabase.fragments.NowPlaying;
import com.example.shais.moviedatabase.fragments.Upcoming;
import com.example.shais.moviedatabase.utils.Config;
import com.example.shais.moviedatabase.utils.GlobalLoader;
import com.example.shais.moviedatabase.utils.ParseMovies;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainClass";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Fragment nowplaying, upcoming;

    List<Movie> recentMovie, upcomingMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nowplaying = new NowPlaying();
        upcoming = new Upcoming();

        recentMovie = new ArrayList<>();
        upcomingMovie = new ArrayList<>();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.container);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(nowplaying, "Now Playing");
        adapter.addFragment(upcoming, "Upcoming");
        viewPager.setAdapter(adapter);
    }

    /**
     * Call the API to get the results.
     * @param url API URL.
     */
    private void GetNowPlaying(String url) {
        final RequestQueue queue = Volley.newRequestQueue(this);
        Log.d(TAG, "Sending request..." + url);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    ArrayList<Movie> mymovies = ParseMovies.parseData(response);

                    ((NowPlaying) nowplaying).moviesDataArrive(mymovies);
                    GetUpcoming(queue, Config.getUpcomingURI());
                } catch (JSONException e) {
                    e.printStackTrace();
                    GlobalLoader.FinishMe();
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error occur : " + error.toString() + "\n" + error.getMessage());
                GlobalLoader.FinishMe();
                finish();
            }
        });
        queue.add(jsonRequest);

        return;
    }

    /**
     * Call the API to get the results.
     * @param url API URL.
     */
    private void GetUpcoming(RequestQueue queue, String url) {
        Log.d(TAG, "Sending request..." + url);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    ArrayList<Movie> mymovies = ParseMovies.parseData(response);
                    GlobalLoader.FinishMe();

                    ((Upcoming) upcoming).moviesDataArrive(mymovies);
                    setupViewPager(viewPager);
                    tabLayout.setupWithViewPager(viewPager);

                } catch (JSONException e) {
                    e.printStackTrace();
                    GlobalLoader.FinishMe();
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error occur : " + error.toString() + "\n" + error.getMessage());
                GlobalLoader.FinishMe();
                finish();
            }
        });
        queue.add(jsonRequest);

        return;
    }



    @Override
    protected void onResume() {
        super.onResume();

        new GlobalLoader("Loading Movies...", MainActivity.this, MainActivity.this);
        GetNowPlaying(Config.getNowPlayingURI());

    }

}
