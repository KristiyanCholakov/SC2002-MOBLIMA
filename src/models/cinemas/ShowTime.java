package models.cinemas;

import models.movies.Movie;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

public class ShowTime implements Serializable {
    private LocalDate date;
    private Cinema cinema;
    private LocalTime startTime;
    private LocalTime endTime;
    private Movie movie;
    private HashMap<Character, List<Seat>> seatOccupancy;

    public ShowTime(LocalDate date, Cinema cinema, LocalTime startTime, LocalTime endTime, Movie movie, HashMap<Character, List<Seat>> seatOccupancy) {
        this.date = date;
        this.cinema = cinema;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
        this.seatOccupancy = seatOccupancy;
    }

    public LocalDate getDate() {
        return date;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public HashMap<Character, List<Seat>> getSeatOccupancy() {
        return seatOccupancy;
    }
}
