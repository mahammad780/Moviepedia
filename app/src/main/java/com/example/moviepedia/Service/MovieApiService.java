package com.example.moviepedia.Service;

import com.example.moviepedia.Model.MovieApiResponce;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("movie/popular")
    Call<MovieApiResponce> getPopularMovies(@Query("api key") String apiKey);

    @GET("movie/popular")
    Call<MovieApiResponce> getPopularMoviesWithPaging(@Query("api key") String apiKey, @Query("page") long page);
}
