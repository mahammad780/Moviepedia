package com.example.moviepedia.Model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.moviepedia.R;
import com.example.moviepedia.Service.MovieApiService;
import com.example.moviepedia.Service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private ArrayList<Result> results = new ArrayList<>();
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Result>> getMutableLiveData() {

        MovieApiService movieApiService = RetrofitInstance.getService();

        Call<MovieApiResponce> call = movieApiService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));
        call.enqueue(new Callback<MovieApiResponce>() {
            @Override
            public void onResponse(Call<MovieApiResponce> call, Response<MovieApiResponce> response) {
                MovieApiResponce movieApiResponce = response.body();

                if (movieApiResponce != null && movieApiResponce.getResults() != null){
                    results = (ArrayList<Result>)movieApiResponce.getResults();
                    mutableLiveData.setValue(results);

                }

            }

            @Override
            public void onFailure(Call<MovieApiResponce> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
