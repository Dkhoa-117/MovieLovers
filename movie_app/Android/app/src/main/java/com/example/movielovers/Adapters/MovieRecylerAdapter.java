package com.example.movielovers.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielovers.DownloadImageTask;
import com.example.movielovers.MainActivity;
import com.example.movielovers.Models.Movie;
import com.example.movielovers.MovieDetail;
import com.example.movielovers.R;

import java.util.ArrayList;

public class MovieRecylerAdapter extends RecyclerView.Adapter<MovieRecylerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Movie> movies;

    public MovieRecylerAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieRecylerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecylerAdapter.ViewHolder holder, int position) {
        Movie currentMovie = movies.get(position);
        holder.tvName.setText(currentMovie.getTitle());
        new DownloadImageTask(holder.ivImg).execute("https://image.tmdb.org/t/p/w500"+currentMovie.getPoster_path());
        holder.ivImg.setAnimation(AnimationUtils.loadAnimation(context, R.anim.item_animation));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetail.class);
                intent.putExtra("movie_object", (Parcelable) currentMovie);
                context.startActivity(intent);
                ((MainActivity) context).overridePendingTransition(R.anim.anim_slide_up, -1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        ImageView ivImg;
        public ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_movieName);
            ivImg = view.findViewById(R.id.iv_movieImage);
        }
    }
}
