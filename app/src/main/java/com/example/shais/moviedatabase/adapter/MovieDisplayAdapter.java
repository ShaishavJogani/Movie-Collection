package com.example.shais.moviedatabase.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.shais.moviedatabase.R;
import com.example.shais.moviedatabase.data_items.Movie;
import com.example.shais.moviedatabase.utils.ImageDownloader;

import java.util.List;

/**
 * Created by Shaishav on 3/24/2018.
 */

public class MovieDisplayAdapter extends RecyclerView.Adapter<MovieDisplayAdapter.MyViewHolder> {

    private final static int NormalView = 0;
    private final static int HighlightView = 1;

    List<Movie> movies;

    Context mContext;

    public MovieDisplayAdapter(Context c, List<Movie> movies) {
        mContext = c;
        this.movies = movies;
    }


    @Override
    public MovieDisplayAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie, parent, false);
        return new MyViewHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(MovieDisplayAdapter.MyViewHolder holder, int position) {
        Movie m = movies.get(position);

        holder.title.setText(m.getName());
        holder.popularity.setText("Popularity: " + (int)Double.parseDouble(m.getPopularity()));
        holder.genre.setText(m.getGeners());

        String posteruri = "https://image.tmdb.org/t/p/w500" + m.getPosterpath();
        holder.poster.setImageUrl(posteruri, ImageDownloader.getInstance(mContext).getImageLoader());
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, popularity, genre;
        NetworkImageView poster;


        public MyViewHolder(View itemView, int viewType) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tvMovieTitle);
            popularity = (TextView) itemView.findViewById(R.id.tvPopularity);
            genre = (TextView) itemView.findViewById(R.id.tvGeneres);
            poster = (NetworkImageView) itemView.findViewById(R.id.ivImagePoster);

            title.setSelected(true);
            genre.setSelected(true);
            poster.setDefaultImageResId(android.R.drawable.btn_dialog);
        }
    }
}
