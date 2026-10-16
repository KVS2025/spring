package com.example.booking;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class SeatService {

    private final List<String> bookedSeats = new ArrayList<>();

    public synchronized String bookSeat(String movie, String seat) {
        String booking = movie.trim().toLowerCase() + "-" + seat.trim().toUpperCase();
        if (bookedSeats.contains(booking)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "That seat is already booked.");
        }
        bookedSeats.add(booking);
        return "Seat " + seat.trim().toUpperCase() + " booked for " + movie.trim();
    }

    public synchronized List<String> getBookedSeats() {
        return new ArrayList<>(bookedSeats);
    }
}
