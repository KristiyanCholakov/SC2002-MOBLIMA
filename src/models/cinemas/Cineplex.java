package models.cinemas;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable {
    private String name;
    private ArrayList<Cinema> cinemas;

    public Cineplex(String name, ArrayList<Cinema> cinemas) {
        this.name = name;
        this.cinemas = cinemas;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }
}
