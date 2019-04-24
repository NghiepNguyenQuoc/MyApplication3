package com.nghiep.myapplication.movie_detail;

import com.nghiep.myapplication.model.Movie;

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {
    MovieDetailsContract.View movieDetailView;
    MovieDetailsContract.Model movieDetailModel;

    public MovieDetailsPresenter(MovieDetailsContract.View movieDetailView) {
        this.movieDetailView = movieDetailView;
        this.movieDetailModel = new MovieDetailsModel();
    }

    @Override
    public void onDestroy() {
        movieDetailView = null;
    }

    @Override
    public void requestMovieData(int movieId) {
        if (movieDetailModel != null)
            movieDetailView.showProgress();
        movieDetailModel.getMovieDetail(new MovieDetailsContract.Model.OnFinishedListener() {
            @Override
            public void onFinished(Movie movie) {
                if (movieDetailView != null)
                    movieDetailView.hideProgress();
                movieDetailView.setDataToView(movie);
            }

            @Override
            public void onFailure(Throwable t) {
                if (movieDetailView != null)
                    movieDetailView.hideProgress();
                movieDetailView.onResponseFailure(t);
            }
        }, movieId);
    }
}
