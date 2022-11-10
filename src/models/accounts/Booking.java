package models.accounts;

import models.cinemas.Cineplex;
import models.cinemas.Seat;
import models.cinemas.ShowTime;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *  The Booking class representing the booking record with its attributes and construction.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class Booking implements Serializable {

    /**
     * An attribute showing the date on which the booking was made.
     */
    private LocalDate bookedOn;

    /**
     * An attribute showing the date of showtime from the booking.
     */
    private LocalDate date;

    /**
     * An attribute showing the cineplex for which the booking is for.
     */
    private Cineplex cineplex;

    /**
     * An attribute showing the showtime for which the booking is for.
     */
    private ShowTime showTime;

    /**
     * An attribute showing the cost of the booking.
     */
    private double cost;

    /**
     * An attribute showing the seat for which the booking is for.
     */
    private Seat seat;

    /**
     * The Booking constructor creates a new booking. Identified by the date on which the booking was made, the date for
     * which the booking is for, the cineplex which the booking is for, the showtime which the booking is for, the seat
     * of the booking and the cost of the booking.
     *
     * @param bookedOn The date the booking was made.
     * @param date The data the booking is for.
     * @param cineplex The cineplex the booking is for.
     * @param showTime The showtime the booking is for.
     * @param seat The seat the booking is for.
     * @param cost The cost of the booking.
     */
    public Booking(LocalDate bookedOn, LocalDate date, Cineplex cineplex, ShowTime showTime, Seat seat, double cost) {
        this.bookedOn = bookedOn;
        this.date = date;
        this.cineplex = cineplex;
        this.showTime = showTime;
        this.seat = seat;
        this.cost = cost;
    }

    /**
     * The getBookedOn method gets the date the booking was made.
     *
     * @return The date the booking was made as a LocalDate record.
     */
    public LocalDate getBookedOn() {
        return bookedOn;
    }

    /**
     * The getDate method gets the date booking if for.
     *
     * @return The date the booking is for as a LocalDate record.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * The getCineplex method gets the cineplex of the booking.
     *
     * @return The cineplex record the booking is for.
     */
    public Cineplex getCineplex() {
        return cineplex;
    }

    /**
     * The getShowTime method gets the showtime of the booking.
     *
     * @return The showtime record the booking is for.
     */
    public ShowTime getShowTime() {
        return showTime;
    }

    /**
     * The getSeat method gets the seat of the booking.
     *
     * @return The seat record the booking is for.
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * The getCost method gets the cost of the booking.
     *
     * @return The cost of the booking.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Custom toString method returns part of the main information of the booking.
     *
     * @return String with significant information about the booking
     */
    @Override
    public String toString() {
        return "Booked On: " + this.bookedOn.toString() + ", " + "Movie: " + this.showTime.getMovie().getTitle() + ", Cineplex: " + this.cineplex.getName()
                + ", Seat: " + this.seat.getRow() + this.getSeat().getColumn()+ ", Date: " + this.date.toString() + ", PRICE: " + this.cost;
    }


    /**
     * Custom equals method to compare the booking records with another objects. Used for comparing 2 bookings based on
     * the date they were made on, the cineplex they were made for, the showtime they were made for and their seat.
     *
     * @param obj Another booking record to be compared.
     * @return true if the custom condition is met. false if not.
     */
    @Override
    public boolean equals(Object obj) {
        Booking b = (Booking) obj;
        return b.bookedOn.equals(this.bookedOn) && b.cineplex.equals(this.cineplex) && b.date.equals(this.date) && b.showTime.equals(this.showTime) &&
                b.seat.equals(this.seat);
    }
}
