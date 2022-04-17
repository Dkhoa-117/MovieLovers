package com.example.movielovers;

import com.example.movielovers.Models.Movie;
import com.example.movielovers.Models.ResponseBody;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PlaceHolder {
    @GET("movie/popular?api_key=04b5fc6bef5ce2d372e07b0d96563b53&language=en-US&page=1")//https://api.themoviedb.org/3/movie/
    Call<ResponseBody> getPopularMovies();

    @GET("movie/{id}?api_key=04b5fc6bef5ce2d372e07b0d96563b53&language=en-US")
    Call<Movie> getMovie(@Path("id") long id);

    @GET("search/movie?api_key=04b5fc6bef5ce2d372e07b0d96563b53&language=en-US&page=1&include_adult=false")
    Call<ResponseBody> searchMovies(@Query("query") String query);
}
