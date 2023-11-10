package com.customer.movie.booking.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingResponse {
    private String message;
    private String movieName;
    private int seatBooked;
    private LocalDate date;
    private LocalTime time;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getSeatBooked() {
        return seatBooked;
    }

    public void setSeatBooked(int seatNumber) {
        this.seatBooked = seatNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
