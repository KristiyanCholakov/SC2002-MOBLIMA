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

    public Booking(LocalDate bookedOn, LocalDate date, Cineplex cineplex, ShowTime showTime, Seat seat, double cost) {
        this.bookedOn = bookedOn;
        this.date = date;
        this.cineplex = cineplex;
        this.showTime = showTime;
        this.seat = seat;
        this.cost = cost;
    }

    public LocalDate getBookedOn() {
        return bookedOn;
    }

    public LocalDate getDate() {
        return date;
    }

    public Cineplex getCineplex() {
        return cineplex;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public Seat getSeat() {
        return seat;
    }

    public double getCost() {
        return cost;
    }

    public String printBooking() {
        return "Booked On: " + this.bookedOn.toString() + ", " + "Movie: " + this.showTime.getMovie().getTitle() + ", Cineplex: " + this.cineplex.getName()
                + ", Seat: " + this.seat.getRow() + this.getSeat().getColumn()+ ", Date: " + this.date.toString() + ", PRICE: " + this.cost;
    }

    @Override
    public boolean equals(Object obj) {
        Booking b = (Booking) obj;
        return b.bookedOn.equals(this.bookedOn) && b.cineplex.equals(this.cineplex) && b.date.equals(this.date) && b.showTime.equals(this.showTime) &&
                b.seat.equals(this.seat);
    }
}
