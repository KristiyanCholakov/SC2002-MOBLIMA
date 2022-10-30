package models.cinemas;

import java.io.Serializable;

public class Seat implements Serializable {
    private String row;
    private int column;
    private boolean occupied;

    public Seat(String row, int column, boolean occupied) {
        this.row = row;
        this.column = column;
        this.occupied = occupied;
    }

    public String getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public boolean bookSeat() {
        if (!occupied) {
            occupied = true;
            return true;
        }
        return false;
    }

    public boolean cancelSeat() {
        if (occupied) {
            occupied = false;
            return true;
        }
        return false;
    }
}
