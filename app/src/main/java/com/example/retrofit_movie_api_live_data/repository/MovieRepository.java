package com.example.retrofit_movie_api_live_data.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.retrofit_movie_api_live_data.Movie;
import com.example.retrofit_movie_api_live_data.R;
import com.example.retrofit_movie_api_live_data.adapter.MoviesAdapter;
import com.example.retrofit_movie_api_live_data.network.ApiClient;
import com.example.retrofit_movie_api_live_data.network.ApiInterface;
import com.example.retrofit_movie_api_live_data.network.MoviesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {


    LiveData<String> stringLiveData;
    ApiInterface apiService;
    private MutableLiveData<List<Movie>> listMovies;

    public MovieRepository(Application application) {
        apiService = ApiClient.getClient().create(ApiInterface.class);



    }

   public MutableLiveData<List<Movie>> getMovies(String API_KEY) {
        listMovies = new MutableLiveData<>();
        Call<MoviesResponse> call = apiService.getTopReateMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {

                    if (response.isSuccessful())
                        Log.d("hh",response.toString());
                    listMovies.setValue(response.body().getResults());
                    Log.d("hh",listMovies.getValue().size()+"");
                }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                listMovies.setValue(null);
            }
        });
        return listMovies;
    }
}
