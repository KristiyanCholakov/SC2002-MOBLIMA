package models.cinemas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Cinema implements Serializable {
    int number;
    CinemaEnums.CinemaType type;

    CinemaEnums.SeatConfiguration seatConfiguration;

    public Cinema(int number, CinemaEnums.CinemaType type, CinemaEnums.SeatConfiguration configuration) {
        this.number = number;
        this.type = type;
        this.seatConfiguration = configuration;
    }

    public int getNumber() {
        return number;
    }

    public CinemaEnums.CinemaType getType() {
        return type;
    }

    public CinemaEnums.SeatConfiguration getSeatConfiguration() {
        return seatConfiguration;
    }

    @Override
    public String toString() {
        return "Cinema:\n" +
                "Number=" + this.number + "\n" +
                "Type=" + this.type.getType() + "\n" +
                "SeatConfiguration=" + this.seatConfiguration.getDescription();
    }
}
