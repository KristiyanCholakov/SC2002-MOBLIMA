package models.cinemas;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Cineplex implements Serializable {
    private String name;
    private String address;
    private HashMap<LocalDate, ArrayList<ShowTime>> schedules;
    private ArrayList<Cinema> cinemas;

    public Cineplex(String name, String address, HashMap<LocalDate, ArrayList<ShowTime>> schedules, ArrayList<Cinema> cinemas) {
        this.name = name;
        this.address = address;
        this.schedules = schedules;
        this.cinemas = cinemas;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public HashMap<LocalDate, ArrayList<ShowTime>> getSchedules() {
        return schedules;
    }

    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    public String cinemasToString() {
        String cinemasStr = "";
        for (int i = 0; i < this.cinemas.size(); i++) {
            cinemasStr += this.cinemas.get(i).toString();
            if (i != this.cinemas.size()) cinemasStr+="\n";
        }
        return cinemasStr;
    }

    @Override
    public boolean equals(Object obj) {
        Cineplex cineplex = (Cineplex) obj;
        return (cineplex.getName().equals(this.name));
    }

    @Override
    public String toString() {
        return "Cineplex:\n" +
                "Name=" + this.name + "\n" +
                "Address=" + this.address + "\n" +
                "Cinemas=\n" + this.cinemasToString();
    }
}
