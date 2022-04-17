package com.example.movielovers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.example.movielovers.Adapters.MovieRecylerAdapter;
import com.example.movielovers.Models.Movie;
import com.example.movielovers.Models.ResponseBody;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> movies = new ArrayList<>();
    PlaceHolder placeHolder;
    MovieRecylerAdapter movieRecylerAdapter;
    RecyclerView recyclerView;
    FloatingActionButton btnSearch;
    Toolbar toolbar;
    String searchString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        findViewById();
        setData();
        buttonHandler();
        setAdapter();
    }

    private void buttonHandler() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.search_dialog);
                SearchView searchView = dialog.findViewById(R.id.fragment_search_SearchView);
                searchView.onActionViewExpanded();
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        searchString = query;
                        Call<ResponseBody> searchCall = placeHolder.searchMovies(searchString);
                        searchCall.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if(!response.isSuccessful()) {
                                    Toast.makeText(getApplication(), "code: "+ response.code(), Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                ResponseBody responseBody =  response.body();
                                if (responseBody != null) {
                                    if(!movies.isEmpty()){
                                        movies.clear();
                                    }
                                    movies.addAll(responseBody.getResults());
                                    movieRecylerAdapter.notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(getApplication(), "error", Toast.LENGTH_LONG).show();
                            }
                        });
                        dialog.dismiss();
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
            }
        });
    }

    private void findViewById(){
        recyclerView = findViewById(R.id.recyclerView);
        btnSearch = findViewById(R.id.btnSearch);
        toolbar = findViewById(R.id.AppToolbar);
    }
    private void setData() {
        String base_Url = "https://api.themoviedb.org/3/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        placeHolder = retrofit.create(PlaceHolder.class);

        Call<ResponseBody> call = placeHolder.getPopularMovies();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(getApplication(), "code: "+ response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                ResponseBody responseBody =  response.body();

                if (responseBody != null) {
                    if(!movies.isEmpty()){
                        if(!movies.equals(responseBody.getResults())){
                            movies.clear();
                            movies.addAll(responseBody.getResults());
                            movieRecylerAdapter.notifyDataSetChanged();
                        }
                        recyclerView.scrollToPosition(0);
                    }else{
                        movies.addAll(responseBody.getResults());
                        movieRecylerAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplication(), "error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setAdapter() {
        movieRecylerAdapter = new MovieRecylerAdapter(this, movies);
        recyclerView.setAdapter(movieRecylerAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
    }
}