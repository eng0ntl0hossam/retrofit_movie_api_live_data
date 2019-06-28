package com.example.retrofit_movie_api_live_data;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.example.retrofit_movie_api_live_data.adapter.MoviesAdapter;
import com.example.retrofit_movie_api_live_data.network.ApiClient;
import com.example.retrofit_movie_api_live_data.network.ApiInterface;
import com.example.retrofit_movie_api_live_data.network.MoviesResponse;
import com.example.retrofit_movie_api_live_data.viewmodels.MainActivityViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


  MoviesAdapter moviesAdapter;
  List<Movie> movies;
    MainActivityViewModel mainActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesAdapter=new MoviesAdapter(R.layout.item,this);
        recyclerView.setAdapter(moviesAdapter);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.getLivemovieslist().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                moviesAdapter.setMovies(movies);
            }
        });

    }
}
