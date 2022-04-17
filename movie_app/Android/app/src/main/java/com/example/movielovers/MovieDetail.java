package com.example.movielovers;

import static java.lang.String.valueOf;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movielovers.Models.Movie;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MovieDetail extends AppCompatActivity {
    Movie movie;
    TextView txtViewOverview, txtViewReleaseDate, txtViewBudget, txtViewRating, txtViewPopularity;
    ImageView detail_movieImg;
    CollapsingToolbarLayout movieName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_movie);
        getData();
        findViewById();
        setView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }

    private void setView() {
        new DownloadImageTask(detail_movieImg).execute("https://image.tmdb.org/t/p/original"+movie.getBackdrop_path());
        movieName.setCollapsedTitleTextColor(Color.WHITE);
        movieName.setExpandedTitleColor(Color.WHITE);
        movieName.setTitle(movie.getTitle());
        txtViewOverview.setText(movie.getOverview());
        txtViewBudget.setText(String.valueOf(movie.getVote_count()));
        txtViewRating.setText(valueOf(movie.getVote_average()));
        txtViewReleaseDate.setText(movie.getRelease_date());
        txtViewPopularity.setText(valueOf(movie.getPopularity()));
    }

    void findViewById() {
        txtViewBudget = findViewById(R.id.vote_count);
        txtViewOverview = findViewById(R.id.overview);
        txtViewReleaseDate = findViewById(R.id.release_date);
        txtViewRating = findViewById(R.id.rating);
        txtViewPopularity = findViewById(R.id.popularity);
        detail_movieImg = findViewById(R.id.detail_movieImg);
        movieName = findViewById(R.id.CollapsingToolbar);
    }

    void getData(){
        Intent intent = getIntent();
        movie = intent.getParcelableExtra("movie_object");
    }
}
