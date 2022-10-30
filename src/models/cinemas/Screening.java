package models.cinemas;

import models.cinemas.Screen;
import models.movies.Movie;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

public class Screening implements Serializable {
    private LocalDate date;
    private Screen screen;
    private LocalTime startTime;
    private LocalTime endTime;
    private Movie movie;
    private HashMap<Character, List<Seat>> seatOccupancy;

    public Screening(LocalDate date, Screen screen, LocalTime startTime, LocalTime endTime, Movie movie, HashMap<Character, List<Seat>> seatOccupancy) {
        this.date = date;
        this.screen = screen;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
        this.seatOccupancy = seatOccupancy;
    }

    public LocalDate getDate() {
        return date;
    }

    public Screen getScreen() {
        return screen;
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
