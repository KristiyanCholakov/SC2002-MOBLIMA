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
    private Seat seat;

    public Booking(LocalDate bookedOn, LocalDate date, Cineplex cineplex, ShowTime showTime, Seat seat) {
        this.bookedOn = bookedOn;
        this.date = date;
        this.cineplex = cineplex;
        this.showTime = showTime;
        this.seat = seat;
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

    @Override
    public boolean equals(Object obj) {
        Booking b = (Booking) obj;
        return b.bookedOn.equals(this.bookedOn) && b.cineplex.equals(this.cineplex) && b.date.equals(this.date) && b.showTime.equals(this.showTime) &&
                b.seat.equals(this.seat);
    }
}
