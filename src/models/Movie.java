package models;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private String id;
    private String name;
    private List<String> genre;
    private String duration;
    private String status;
    private String synopsis;
    private Director director;
    private List<Actor> cast;
    private double rating;
    private List<Review> reviews;

    public Movie(String id, String name, List<String> genre, String duration, String status, String synopsis, Director director, List<Actor> cast, double rating, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.duration = duration;
        this.status = status;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.rating = rating;
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "ID=" + this.id + ", " +
                "Name=" + this.name + ", " +
                "Genre=" + this.genre + ", " +
                "Duration=" + this.duration + ", " +
                "Status=" + this.status + ", " +
                "Synopsis=" + this.synopsis;
    }
}
