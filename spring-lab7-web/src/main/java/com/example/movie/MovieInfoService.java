package com.example.movie;

public class MovieInfoService {

    private final RatingProvider ratingProvider;

    public MovieInfoService(RatingProvider ratingProvider) {
        this.ratingProvider = ratingProvider;
    }

    public String getRating(String movie) {
        return "Rating of " + movie + ": " + ratingProvider.getRating(movie);
    }
}
