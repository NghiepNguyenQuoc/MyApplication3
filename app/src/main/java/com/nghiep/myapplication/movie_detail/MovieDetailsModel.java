package com.nghiep.myapplication.movie_detail;

import android.util.Log;

import com.nghiep.myapplication.model.Movie;
import com.nghiep.myapplication.network.ApiClient;
import com.nghiep.myapplication.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nghiep.myapplication.network.ApiClient.API_KEY;
import static com.nghiep.myapplication.utils.Constants.*;

public class MovieDetailsModel implements MovieDetailsContract.Model {
    private final String TAG = "MovieDetailsModel";

    @Override
    public void getMovieDetail(final OnFinishedListener onFinishedListener, int movieId) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Movie> call = apiService.getMovieDetails(movieId, API_KEY, CREDITS);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                Log.d(TAG, "Movie data received: " + movie.toString());
                onFinishedListener.onFinished(movie);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }
}
