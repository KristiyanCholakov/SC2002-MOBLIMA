package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Cinema implements Serializable {
    private String name;
    private String address;
    private HashMap<LocalDate, ArrayList<Screening>> schedules;
    private ArrayList<Screen> screens;

    public Cinema(String name, String address, HashMap<LocalDate, ArrayList<Screening>> schedules, ArrayList<Screen> screens) {
        this.name = name;
        this.address = address;
        this.schedules = schedules;
        this.screens = screens;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public HashMap<LocalDate, ArrayList<Screening>> getSchedules() {
        return schedules;
    }

    public ArrayList<Screen> getScreens() {
        return screens;
    }
}
