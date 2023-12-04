package com.example.moviepedia.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.moviepedia.Model.Result;
import com.example.moviepedia.R;

public class MovieDetailsActivity extends AppCompatActivity {

    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intentMovieDetails = getIntent();
        if(intentMovieDetails !=null && intentMovieDetails.hasExtra(getString(R.string.intent_key))){

            result = intentMovieDetails.getParcelableExtra(getString(R.string.intent_key));
        }
    }
}