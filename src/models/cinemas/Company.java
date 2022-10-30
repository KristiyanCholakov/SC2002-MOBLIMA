package models.cinemas;

import models.cinemas.Cinema;

import java.io.Serializable;
import java.util.ArrayList;

public class Company implements Serializable {
    private String name;
    private ArrayList<Cinema> cinemas;

    public Company(String name, ArrayList<Cinema> cinemas) {
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
