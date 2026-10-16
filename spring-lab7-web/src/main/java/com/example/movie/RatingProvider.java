package com.example.movie;

import java.util.HashMap;
import java.util.Map;

public class RatingProvider {

    private final Map<String, String> ratings = new HashMap<>();

    public RatingProvider() {
        ratings.put("avengers", "4.5 / 5");
        ratings.put("3 idiots", "4.8 / 5");
        ratings.put("jawan", "4.2 / 5");
        ratings.put("kalki", "4.3 / 5");
    }

    public String getRating(String movie) {
        return ratings.getOrDefault(movie.trim().toLowerCase(), "Not available");
    }
}
