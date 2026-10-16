package com.example.booking;

import com.example.event.UserService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BookingManager {

    private final UserService userService;
    private final SeatService seatService;

    public BookingManager(UserService userService, SeatService seatService) {
        this.userService = userService;
        this.seatService = seatService;
    }

    public Map<String, Object> bookTicket(String name, String movie, String seat) {
        List<String> steps = new ArrayList<>();
        steps.add("Booking ticket for: " + movie);
        steps.add(userService.registerUser(name));
        steps.add(seatService.bookSeat(movie, seat));

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("steps", steps);
        response.put("allBookedSeats", seatService.getBookedSeats());
        return response;
    }
}
