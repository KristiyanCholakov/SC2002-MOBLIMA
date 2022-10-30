package models.cinemas;

import models.cinemas.Screen;
import models.movies.Movie;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Screening implements Serializable {
    private LocalDate date;
    private Screen screen;
    private LocalTime startTime;
    private LocalTime endTime;
    private Movie movie;

    public Screening(LocalDate date, Screen screen, LocalTime startTime, LocalTime endTime, Movie movie) {
        this.date = date;
        this.screen = screen;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
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
}
