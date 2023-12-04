package com.example.moviepedia.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.moviepedia.R;
import com.example.moviepedia.Service.MovieApiService;
import com.example.moviepedia.Service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long,Result>{

    private MovieApiService movieApiService;
    private Application application;

    public MovieDataSource(MovieApiService movieApiService, Application application) {
        this.movieApiService = movieApiService;
        this.application = application;
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> loadParams, @NonNull final LoadCallback<Long,
            Result> loadCallback) {
        movieApiService= RetrofitInstance.getService();
        Call<MovieApiResponce> call = movieApiService.getPopularMoviesWithPaging(application.getApplicationContext()
                .getString(R.string.api_key),loadParams.key);

        call.enqueue(new Callback<MovieApiResponce>() {
            @Override
            public void onResponse(Call<MovieApiResponce> call, Response<MovieApiResponce> response) {
                MovieApiResponce movieApiResponce = response.body();
                ArrayList<Result> results = new ArrayList<>();

                if(movieApiResponce !=null && movieApiResponce.getResults() != null){
                    results = (ArrayList<Result>) movieApiResponce.getResults();
                    loadCallback.onResult(results, loadParams.key+1);
                }

            }

            @Override
            public void onFailure(Call<MovieApiResponce> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> loadParams, @NonNull LoadCallback<Long,
            Result> loadCallback) {

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> loadInitialParams,
                            @NonNull LoadInitialCallback<Long, Result> loadInitialCallback) {

        movieApiService= RetrofitInstance.getService();
        Call<MovieApiResponce> call = movieApiService.getPopularMoviesWithPaging(application.getApplicationContext()
                .getString(R.string.api_key),1);

        call.enqueue(new Callback<MovieApiResponce>() {
            @Override
            public void onResponse(Call<MovieApiResponce> call, Response<MovieApiResponce> response) {
                MovieApiResponce movieApiResponce = response.body();
                ArrayList<Result> results = new ArrayList<>();

                if(movieApiResponce !=null && movieApiResponce.getResults() != null){
                    results = (ArrayList<Result>) movieApiResponce.getResults();
                    loadInitialCallback.onResult(results,null,(long)2);
                }

            }

            @Override
            public void onFailure(Call<MovieApiResponce> call, Throwable t) {

            }
        });

    }
}
