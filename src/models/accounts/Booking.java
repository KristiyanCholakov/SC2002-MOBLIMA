package models.accounts;

import models.cinemas.Cineplex;
import models.cinemas.Seat;
import models.cinemas.ShowTime;

import java.io.Serializable;
import java.time.LocalDate;

public class Booking implements Serializable {
    private LocalDate bookedOn;
    private LocalDate date;
    private Cineplex cineplex;
    private ShowTime showTime;
    private double cost;
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
