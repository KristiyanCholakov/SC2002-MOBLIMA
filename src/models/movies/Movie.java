package models.movies;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private String name;
    private List<String> genre;
    private String duration;
    private String status;
    private String synopsis;
    private Director director;
    private List<Actor> cast;
    private double rating;
    private List<Review> reviews;

    public Movie(String name, List<String> genre, String duration, String status, String synopsis, Director director, List<Actor> cast, double rating, List<Review> reviews) {
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

    public String getName() {
        return name;
    }

    public List<String> getGenre() {
        return genre;
    }

    public String getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public Director getDirector() {
        return director;
    }

    public List<Actor> getCast() {
        return cast;
    }

    public double getRating() {
        return rating;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
