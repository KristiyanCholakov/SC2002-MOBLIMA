package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Screening implements Serializable {
    private LocalDate date;
    private Screen screen;
    private LocalTime startTime;
    private LocalTime endTime;
    private Movie movie;
}
