package models.movies;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

public class Movie implements Serializable {
    private String title;
    private String genre;
    private LocalTime duration;
    private MovieEnums.MovieStatus status;
    private MovieEnums.MovieType type;
    private MovieEnums.MovieRestriction restriction;
    private String synopsis;
    private Director director;
    private ArrayList<Actor> cast;
    private double rating;
    private ArrayList<Review> reviews;

    public Movie(String title, String genre, LocalTime duration, MovieEnums.MovieStatus status, MovieEnums.MovieType type, MovieEnums.MovieRestriction restriction, String synopsis, Director director, ArrayList<Actor> cast, double rating, ArrayList<Review> reviews) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.status = status;
        this.type = type;
        this.restriction = restriction;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.rating = rating;
        this.reviews = reviews;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public MovieEnums.MovieStatus getStatus() {
        return status;
    }

    public MovieEnums.MovieType getType() {
        return type;
    }

    public MovieEnums.MovieRestriction getRestriction() {
        return restriction;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public Director getDirector() {
        return director;
    }

    public ArrayList<Actor> getCast() {
        return cast;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public void setStatus(MovieEnums.MovieStatus status) {
        this.status = status;
    }

    public void setType(MovieEnums.MovieType type) {
        this.type = type;
    }

    public void setRestriction(MovieEnums.MovieRestriction restriction) {
        this.restriction = restriction;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public void setCast(ArrayList<Actor> cast) {
        this.cast = cast;
    }
}
