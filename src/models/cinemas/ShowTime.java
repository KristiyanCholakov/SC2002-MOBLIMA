package models.cinemas;

import models.movies.Movie;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ShowTime implements Serializable {
    private LocalDate date;
    private Cinema cinema;
    private LocalTime startTime;
    private LocalTime endTime;
    private Movie movie;
    private HashMap<Character, ArrayList<Seat>> seatOccupancy;

    private HashMap<Character, ArrayList<Seat>> configuration (int rows, int columns) {
        HashMap<Character, ArrayList<Seat>> configuration = new  HashMap<Character, ArrayList<Seat>>();
        for (int i = 65; i < 65 + rows; i++) {
            char row = (char) i;
            ArrayList<Seat> rowSeats = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                rowSeats.add(new Seat(row, j+1, false));
            }
            configuration.put(row, rowSeats);
        }
        return configuration;
    }

    public ShowTime(LocalDate date, Cinema cinema, LocalTime startTime, LocalTime endTime, Movie movie) {
        this.date = date;
        this.cinema = cinema;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
        this.seatOccupancy = configuration(cinema.seatConfiguration.getRows(), cinema.seatConfiguration.getColumns());
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

    public HashMap<Character, ArrayList<Seat>> getSeatOccupancy() {
        return seatOccupancy;
    }

    @Override
    public String toString() {
        return "Showtime:\n" +
                "Movie=" + this.movie.getTitle() + "\n" +
                "Cinema Number=" + this.getCinema().getNumber() + "\n" +
                "Start Time=" + this.getStartTime() + "\n"+
                "End Time=" + this.getEndTime();
    }
}
