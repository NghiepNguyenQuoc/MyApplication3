package com.nghiep.myapplication.movie_detail;

import com.nghiep.myapplication.model.Movie;

public interface MovieDetailsContract {
    interface Model {
        interface OnFinishedListener {
            void onFinished(Movie movie);

            void onFailure(Throwable t);
        }

        void getMovieDetail(OnFinishedListener onFinishedListener, int movieId);
    }

    interface View {
        void showProgress();

        void hideProgress();

        void setDataToView(Movie movie);

        void onResponseFailure(Throwable t);
    }

    interface Presenter {
        void onDestroy();

        void requestMovieData(int movieId);
    }
}

