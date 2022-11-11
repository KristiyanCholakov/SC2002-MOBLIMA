package models.cinemas;

import models.movies.Movie;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 *  The ShowTime class representing the showtime record with its attributes, methods and construction.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class ShowTime implements Serializable {

    /**
     * An attribute of the date of the showtime.
     */
    private LocalDate date;

    /**
     * An attribute of the cinema of the showtime.
     */
    private Cinema cinema;

    /**
     * An attribute of the start time of the showtime.
     */
    private LocalTime startTime;

    /**
     * An attribute of the end time of the showtime.
     */
    private LocalTime endTime;

    /**
     * An attribute of the movie of the showtime.
     */
    private Movie movie;

    /**
     * An attribute of the seat occupancy of the showtime.
     */
    private HashMap<Character, ArrayList<Seat>> seatOccupancy;

    /**
     * The configuration method determines and creates the seat configuration based on the cinema room type.
     *
     * @param cinema The cinema where the showtime is presented.
     * @return Array lists of the seats in a row accessed by the row character.
     */
    private HashMap<Character, ArrayList<Seat>> configuration(Cinema cinema) {
        HashMap<Character, ArrayList<Seat>> configuration = new HashMap<Character, ArrayList<Seat>>();
        if (cinema.getSeatConfiguration().isWithDandC()) {
            for (int i = 65; i < 65 + cinema.getSeatConfiguration().getRows(); i++) {
                char row = (char) i;
                ArrayList<Seat> rowSeats = new ArrayList<>();
                if (i > 65 + cinema.getSeatConfiguration().getRows() - 3) {
                    for (int j = 0; j < cinema.getSeatConfiguration().getColumns()/2; j++) {
                        rowSeats.add(new Seat(row, j + 1, false, SeatEnums.SeatType.COUPLE));
                    }
                } else if (i == 65 + cinema.getSeatConfiguration().getRows() - 3) {
                    for (int j = 0; j < cinema.getSeatConfiguration().getColumns(); j++) {
                        rowSeats.add(new Seat(row, j + 1, false, SeatEnums.SeatType.DELUXE));
                    }
                } else {
                    for (int j = 0; j < cinema.getSeatConfiguration().getColumns(); j++) {
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
                    for (int j = 0; j < cinema.getSeatConfiguration().getColumns(); j++) {
                        rowSeats.add(new Seat(row, j + 1, false, SeatEnums.SeatType.NORMAL));
                    }
                    configuration.put(row, rowSeats);
                }
                return configuration;
            } else {
                for (int i = 65; i < 65 + cinema.getSeatConfiguration().getRows(); i++) {
                    char row = (char) i;
                    ArrayList<Seat> rowSeats = new ArrayList<>();
                    for (int j = 0; j < cinema.getSeatConfiguration().getColumns(); j++) {
                        rowSeats.add(new Seat(row, j + 1, false, SeatEnums.SeatType.DELUXE));
                    }
                    configuration.put(row, rowSeats);
                }
                return configuration;
            }
        }
    }

    /**
     * The ShowTime constructor to create a new showtime record.
     *
     * @param date The date of the showtime.
     * @param cinema The cinema of the showtime.
     * @param startTime The start time of the showtime.
     * @param endTime The end time of the showtime.
     * @param movie The movie of the showtime.
     */
    public ShowTime(LocalDate date, Cinema cinema, LocalTime startTime, LocalTime endTime, Movie movie) {
        this.date = date;
        this.cinema = cinema;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movie = movie;
        this.seatOccupancy = configuration(cinema);
    }

    /**
     * The getDate method to get the date of the showtime.
     *
     * @return The date of the showtime as a LocalDate record.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * The getCinema method to get the cinema of the showtime.
     *
     * @return The cinema record of the showtime.
     */
    public Cinema getCinema() {
        return cinema;
    }

    /**
     * The getStartTime method to get the start time of the showtime.
     *
     * @return The start time of the showtime as a LocalTime record.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * The getEndTime method to get the end time of the showtime.
     *
     * @return The end time of the showtime as a LocalTime record.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * The getMovie method to get movie of the showtime.
     *
     * @return The movie record of the showtime.
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * The setMovie changes the movie.
     * @param movie The new movie.
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * The getSeatOccupancy method to get the seat occupancy of the showtime.
     *
     * @return Array lists of the seats in the rows accessed by the row character.
     */

    public HashMap<Character, ArrayList<Seat>> getSeatOccupancy() {
        return seatOccupancy;
    }

    /**
     * The printShowTime method to print the simplified information of a showtime.
     */
    public void printShowTime() {
        System.out.println("(" + this.startTime.toString() + " - " + this.endTime.toString() + ") " + this.movie.getTitle());
    }

    /**
     * Custom toString method to get the text representation of the showtime.
     *
     * @return The text representation of the showtime.
     */
    @Override
    public String toString() {
        return "Showtime:\n" +
                "Movie=" + this.movie.getTitle() + "\n" +
                "Cinema Number=" + this.getCinema().getNumber() + "\n" +
                "Start Time=" + this.getStartTime() + "\n" +
                "End Time=" + this.getEndTime();
    }

    /**
     * The ByStartTime is used for sorting the showtimes by their start time in ascending order.
     */
    public static class ByStartTime implements Comparator<ShowTime> {

        /**
         * Custom compare method to compare two showtimes based on their start time.
         *
         * @param o1 the first showtime to be compared.
         * @param o2 the second showtime to be compared.
         * @return Negative integer if first showtime start time is before the second one. Positive if greater. 0 for equal
         */
        @Override
        public int compare(ShowTime o1, ShowTime o2) {
            return o1.getStartTime().compareTo(o2.getStartTime());
        }
    }
}
