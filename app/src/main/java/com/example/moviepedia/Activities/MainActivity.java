package com.example.moviepedia.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.moviepedia.Adapter.ResultAdapter;
import com.example.moviepedia.Model.MovieApiResponce;
import com.example.moviepedia.Model.Result;
import com.example.moviepedia.R;
import com.example.moviepedia.Service.MovieApiService;
import com.example.moviepedia.Service.RetrofitInstance;
import com.example.moviepedia.ViewModel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    
    private PagedList<Result> resultsList;
    private RecyclerView recyclerView;
    private ResultAdapter resultAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(MainActivityViewModel.class);

        getPopularMovies();

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.purple);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });
    }

    public void getPopularMovies(){
        //mainActivityViewModel.getAllMovieData().observe(this, new Observer<List<Result>>() {
           // @Override
           // public void onChanged(List<Result> results) {
                //resultsList = (ArrayList<Result>) results;
               // fillRecyclerView();
              //  swipeRefreshLayout.setRefreshing(false);
           // }
      //  });
        mainActivityViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                resultsList = results;
                fillRecyclerView();
            }
        });
    }

    private void fillRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        resultAdapter = new ResultAdapter(this);
        resultAdapter.submitList(resultsList);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        resultAdapter.notifyDataSetChanged();
    }
}