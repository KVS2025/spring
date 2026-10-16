package com.example.web;

import com.example.booking.BookingManager;
import com.example.movie.MovieInfoService;
import com.example.timer.ShowTimer;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LabController {

    private final ShowTimer showTimer;
    private final MovieInfoService movieInfoService;
    private final BookingManager bookingManager;
    private final ApplicationContext context;

    public LabController(ShowTimer showTimer,
                         MovieInfoService movieInfoService,
                         BookingManager bookingManager,
                         ApplicationContext context) {
        this.showTimer = showTimer;
        this.movieInfoService = movieInfoService;
        this.bookingManager = bookingManager;
        this.context = context;
    }

    @GetMapping("/timer/status")
    public Map<String, Object> timerStatus() {
        return timerResponse(0, false);
    }

    @PostMapping("/timer/sell")
    public Map<String, Object> sellTickets(@RequestParam int tickets) {
        if (tickets < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sell at least one ticket.");
        }
        showTimer.sellTickets(tickets);
        return timerResponse(tickets, true);
    }

    @GetMapping("/movies/rating")
    public Map<String, Object> movieRating(@RequestParam String movie) {
        requireText(movie, "Movie");
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("rating", movieInfoService.getRating(movie));
        return response;
    }

    @PostMapping("/bookings")
    public Map<String, Object> bookTicket(@RequestParam String name,
                                          @RequestParam String movie,
                                          @RequestParam String seat) {
        requireText(name, "Name");
        requireText(movie, "Movie");
        requireText(seat, "Seat");
        return bookingManager.bookTicket(name.trim(), movie.trim(), seat.trim());
    }

    @GetMapping("/beans")
    public List<Map<String, Object>> beans() {
        String[] names = {"showTimer", "ratingProvider", "movieInfoService",
                "userService", "seatService", "bookingManager"};
        List<Map<String, Object>> list = new ArrayList<>();
        for (String name : names) {
            Object bean = context.getBean(name);
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("name", name);
            row.put("class", bean.getClass().getSimpleName());
            row.put("objectId", Integer.toHexString(System.identityHashCode(bean)));
            list.add(row);
        }
        return list;
    }

    private Map<String, Object> timerResponse(int soldNow, boolean justSold) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", showTimer.getStatus());
        response.put("soldNow", justSold ? soldNow : showTimer.getSoldNow());
        response.put("totalTicketsSold", showTimer.getTotalTicketsSold());
        return response;
    }

    private void requireText(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, field + " is required.");
        }
    }
}
