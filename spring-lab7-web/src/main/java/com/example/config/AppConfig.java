package com.example.config;

import com.example.booking.BookingManager;
import com.example.booking.SeatService;
import com.example.event.UserService;
import com.example.movie.MovieInfoService;
import com.example.movie.RatingProvider;
import com.example.timer.ShowTimer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(initMethod = "startShow", destroyMethod = "stopShow")
    public ShowTimer showTimer() {
        return new ShowTimer();
    }

    @Bean
    public RatingProvider ratingProvider() {
        return new RatingProvider();
    }

    @Bean
    public MovieInfoService movieInfoService(RatingProvider ratingProvider) {
        return new MovieInfoService(ratingProvider);
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

    @Bean
    public SeatService seatService() {
        return new SeatService();
    }

    @Bean
    public BookingManager bookingManager(UserService userService, SeatService seatService) {
        return new BookingManager(userService, seatService);
    }
}
