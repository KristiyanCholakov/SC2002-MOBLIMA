package models.cinemas;

import java.io.Serializable;
import java.util.Objects;

public class Seat implements Serializable {
    private Character row;
    private int column;
    private SeatEnums.SeatType type;
    private boolean occupied;

    public Seat(Character row, int column, boolean occupied, SeatEnums.SeatType type) {
        this.row = row;
        this.column = column;
        this.occupied = occupied;
        this.type = type;
    }

    public Character getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public SeatEnums.SeatType getType() {
        return type;
    }

    public boolean bookSeat() {
        if (!occupied) {
            occupied = true;
            return true;
        }
        return false;
    }

    public boolean cancelSeat() {
        if (this.occupied) {
            this.occupied = false;
            return true;
        }
        return false;
    }

    public String printSeat() {
        switch (this.type) {
            case COUPLE:
                if (this.occupied) return "[XXXX]";
                else return "[    ]";
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

    @Override
    public boolean equals(Object o) {
        Seat s = (Seat) o;
        return s.getColumn() == this.column && s.getRow() == this.row;
    }
}
