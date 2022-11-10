package models.cinemas;

import models.movies.Movie;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ShowTime implements Serializable {
    private LocalDate date;
    private Cinema cinema;
    private LocalTime startTime;
    private LocalTime endTime;
    private Movie movie;
    private HashMap<Character, ArrayList<Seat>> seatOccupancy;

    private HashMap<Character, ArrayList<Seat>> configuration(Cinema cinema) {
        HashMap<Character, ArrayList<Seat>> configuration = new HashMap<Character, ArrayList<Seat>>();
        if (cinema.seatConfiguration.isWithPandC()) {
            for (int i = 65; i < 65 + cinema.getSeatConfiguration().getRows(); i++) {
                char row = (char) i;
                ArrayList<Seat> rowSeats = new ArrayList<>();
                if (i > 65 + cinema.getSeatConfiguration().getRows() - 3) {
                    for (int j = 0; j < cinema.seatConfiguration.getColumns()/2; j++) {
                        rowSeats.add(new Seat(row, j + 1, false, SeatEnums.SeatType.COUPLE));
                    }
                } else if (i == 65 + cinema.getSeatConfiguration().getRows() - 3) {
                    for (int j = 0; j < cinema.seatConfiguration.getColumns(); j++) {
                        rowSeats.add(new Seat(row, j + 1, false, SeatEnums.SeatType.DELUXE));
                    }
                } else {
                    for (int j = 0; j < cinema.seatConfiguration.getColumns(); j++) {
                        rowSeats.add(new Seat(row, j + 1, false, SeatEnums.SeatType.NORMAL));
                    }
                }
                configuration.put(row, rowSeats);
            }
            return configuration;
        } else {
            if (cinema.getType() == CinemaEnums.CinemaType.NORMAL) {
                for (int i = 65; i < 65 + cinema.getSeatConfiguration().getRows(); i++) {
                    char row = (char) i;
                    ArrayList<Seat> rowSeats = new ArrayList<>();
                    for (int j = 0; j < cinema.seatConfiguration.getColumns(); j++) {
                        rowSeats.add(new Seat(row, j + 1, false, SeatEnums.SeatType.NORMAL));
                    }
                    configuration.put(row, rowSeats);
                }
                return configuration;
            } else {
                for (int i = 65; i < 65 + cinema.getSeatConfiguration().getRows(); i++) {
                    char row = (char) i;
                    ArrayList<Seat> rowSeats = new ArrayList<>();
                    for (int j = 0; j < cinema.seatConfiguration.getColumns(); j++) {
                        rowSeats.add(new Seat(row, j + 1, false, SeatEnums.SeatType.DELUXE));
                    }
                    configuration.put(row, rowSeats);
                }
                return configuration;
            }
        }
    }

    public ShowTime(LocalDate date, Cinema cinema, LocalTime startTime, LocalTime endTime, Movie movie) {
        this.date = date;
        this.cinema = cinema;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
        this.seatOccupancy = configuration(cinema);
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

    public void printShowTime() {
        System.out.println("(" + this.startTime.toString() + " - " + this.endTime.toString() + ") " + this.movie.getTitle());
    }

    @Override
    public String toString() {
        return "Showtime:\n" +
                "Movie=" + this.movie.getTitle() + "\n" +
                "Cinema Number=" + this.getCinema().getNumber() + "\n" +
                "Start Time=" + this.getStartTime() + "\n" +
                "End Time=" + this.getEndTime();
    }

    public static class ByStartTime implements Comparator<ShowTime> {

        @Override
        public int compare(ShowTime o1, ShowTime o2) {
            return o1.getStartTime().compareTo(o2.getStartTime());
        }
    }
}
