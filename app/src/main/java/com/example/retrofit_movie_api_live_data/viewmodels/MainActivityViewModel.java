package com.example.retrofit_movie_api_live_data.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.retrofit_movie_api_live_data.Movie;
import com.example.retrofit_movie_api_live_data.repository.MovieRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    public static final String API_KEY = "c6c5374b89a1bba116b802ef29e0783a";

    private MovieRepository movieRepository;
    MutableLiveData<List<Movie>> livemovieslist;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        livemovieslist = movieRepository.getMovies(API_KEY);


    }


    public MutableLiveData<List<Movie>> getLivemovieslist() {
        return livemovieslist;
    }
}
