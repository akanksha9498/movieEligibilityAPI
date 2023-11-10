package com.customer.movie.booking.controller;


import com.customer.movie.booking.Entity.Movie;
import com.customer.movie.booking.model.BookingRequest;
import com.customer.movie.booking.model.BookingResponse;
import com.customer.movie.booking.repository.CustomerRepository;
import com.customer.movie.booking.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movie/")
public class MovieController {
    @Autowired
    MovieRepo movieRepo;
    @Autowired
    CustomerRepository custRepo;

    @PostMapping("save")
    public String movieData(@RequestBody Movie movie){
        movieRepo.save(movie);
        return "Movie saved successfully.";
    }
    @GetMapping("currentlyStreaming")
    public List<Movie> moviesAvailable(){
        return movieRepo.getMovies();
    }
    @PostMapping("ticketBooking")
    public BookingResponse ticketBooking(@RequestBody BookingRequest bookingRequest){

        BookingResponse bookingResponse = new BookingResponse();
        Movie movie = new Movie();
        boolean isEligibleForUnder12 = false;
        int count=0;
        switch (bookingRequest.getMovieType().toLowerCase()) {
            case "horror":
            case "r-rated":
            case "violent":
                isEligibleForUnder12 = false;
                break;
            case "comedy":
            case "thriller":
            case "science fiction":
            case "adventure":
            case "documentary":
            case "musical":
            case "romance":
            case "animation":
                isEligibleForUnder12 = true;
                break;

            default:
                bookingResponse.setMessage("INVALID MOVIE TYPE : BOOKING UNSUCCESSFUL");
                return bookingResponse;

        }
        int seats = movieRepo.numberOfSeats(bookingRequest.getMovieName());
        int customerAge = bookingRequest.getAge();
        if (customerAge < 12 && isEligibleForUnder12==false) {
            bookingResponse.setMessage("Booking Unsuccessful. User Not Eligible For Given Movie Type");
            return bookingResponse;
        }
        if(bookingRequest.getPaymentStatus()==0){
            bookingResponse.setMessage("Booking Unsuccessful. Payment Pending");
            return bookingResponse;
        }
        if(bookingRequest.getSeats() > seats){
             bookingResponse.setMessage("Booking Unsuccessful. Seats Not Available");
             return bookingResponse;
        }
        int remainingSeats = seats - bookingRequest.getSeats();
        movieRepo.updateSeats(remainingSeats,bookingRequest.getMovieName());


        bookingResponse.setMessage("Congratulations...!! Booking Successful..!! You can download your ticket");
        bookingResponse.setMovieName(bookingRequest.getMovieName());
        bookingResponse.setSeatBooked(bookingRequest.getSeats());
        bookingResponse.setDate(bookingRequest.getDate());
        bookingResponse.setTime(bookingRequest.getTime());

        return bookingResponse;
    }


}
