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

}
