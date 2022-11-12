package models.cinemas;

import java.io.Serializable;

/**
 *  The Seat class representing the seat record with its attributes, methods and construction.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class Seat implements Serializable {

    /**
     * An attribute to hold the row of the seat.
     */
    private Character row;

    /**
     * An attribute to hold the column of the seat.
     */
    private int column;

    /**
     * An attribute to hold the type of the seat.
     */
    private SeatEnums.SeatType type;

    /**
     * An attribute to show if the seat is occupied.
     */
    private boolean occupied;

    /**
     * The Seat constructor to create a new seat record.
     *
     * @param row The row of the seat.
     * @param column The column of the seat.
     * @param occupied If the seat is occupied.
     * @param type The type of the seat.
     */
    public Seat(Character row, int column, boolean occupied, SeatEnums.SeatType type) {
        this.row = row;
        this.column = column;
        this.occupied = occupied;
        this.type = type;
    }

    /**
     * The getRow method to get the row of the seat.
     *
     * @return The row of the seat.
     */
    public Character getRow() {
        return row;
    }

    /**
     * The getColumn to get the column of the seat.
     *
     * @return The column of the seat.
     */
    public int getColumn() {
        return column;
    }

    /**
     * The isOccupied to check if the seat is occupied.
     *
     * @return true if the seat is occupied. false if not.
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * The getType method to get the type of the seat.
     *
     * @return An enum of the seat type
     */
    public SeatEnums.SeatType getType() {
        return type;
    }

    /**
     * The bookSeat method to book the seat.
     *
     * @return true if the seat is successfully booked. false if booking unsuccessful.
     */
    public boolean bookSeat() {
        if (!occupied) {
            this.occupied = true;
            return true;
        }
        return false;
    }

    /**
     * The cancelSeat method to cancel the seat.
     *
     * @return true if the seat is successfully canceled. false if canceling unsuccessful.
     */
    public boolean cancelSeat() {
        if (this.occupied) {
            this.occupied = false;
            return true;
        }
        return false;
    }

    /**
     * The printSeat method gets the text representation if a seat is occupied based on the seat type.
     *
     * @return The text representation if a seat is occupied based on the seat type.
     */
    public String printSeat() {
        switch (this.type) {
            case COUPLE:
                if (this.occupied) return "[XXXX]";
                else {
                    String place = "" + this.row + this.column;
                    return "[" + place + (" ".repeat((4-place.length()))) + "]";
                }
            case DELUXE:
                if (this.occupied) return "{X}";
                else return "{ }";
            case NORMAL:
                if (this.occupied) return "[X]";
                else return "[ ]";
            default:
                return "error";
        }
    }

    /**
     * Custom equals function to check if a seat is the same with another. Based on the row and column number.
     *
     * @param o Another seat to be compared
     * @return true if the seat match on the base condition. false if seats not the same.
     */
    @Override
    public boolean equals(Object o) {
        Seat s = (Seat) o;
        return s.getColumn() == this.column && s.getRow() == this.row;
    }
}
