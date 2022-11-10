package models.cinemas;

import java.io.Serializable;

/**
 *  The Cinema class representing the cinema record with its attributes, methods and construction.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class Cinema implements Serializable {

    /**
     * An attribute showing the number of the cinema room.
     */
    private int number;

    /**
     * An attribute showing the type of the cinema room.
     */
    private CinemaEnums.CinemaType type;

    /**
     * An attribute showing the seat configuration of the cinema room.
     */
    private CinemaEnums.SeatConfiguration seatConfiguration;

    /**
     * The Cinema constructor to create a new cinema record.
     *
     * @param number The room number of the cinema.
     * @param type The room type of the cinema.
     * @param configuration The seat configuration of the cinema.
     */
    public Cinema(int number, CinemaEnums.CinemaType type, CinemaEnums.SeatConfiguration configuration) {
        this.number = number;
        this.type = type;
        this.seatConfiguration = configuration;
    }

    /**
     * The getNumber method gets the number of the cinema's room.
     *
     * @return The room number of the cinema.
     */
    public int getNumber() {
        return number;
    }

    /**
     * The getType method gets the type of the cinema's room.
     *
     * @return The room type of the cinema.
     */
    public CinemaEnums.CinemaType getType() {
        return type;
    }

    /**
     * The getSeatConfiguration method gets the seat configuration of the cinema.
     *
     * @return The seat configuration of the cinema.
     */
    public CinemaEnums.SeatConfiguration getSeatConfiguration() {
        return seatConfiguration;
    }

    /**
     * Custom toString method to represent the cinema record as a string.
     *
     * @return String representing the cinema record with its attributes.
     */
    @Override
    public String toString() {
        return "Cinema:\n" +
                "Number=" + this.number + "\n" +
                "Type=" + this.type.getType() + "\n" +
                "SeatConfiguration=" + this.seatConfiguration.getDescription();
    }
}
