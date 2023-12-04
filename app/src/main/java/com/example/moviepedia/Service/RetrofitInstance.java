package com.example.moviepedia.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static String BASE_URL = "https://api.themoviedb.org/3/";

    public static MovieApiService getService(){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit.create(MovieApiService.class);
    }
}
