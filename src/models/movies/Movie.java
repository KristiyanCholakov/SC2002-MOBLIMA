package models.movies;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

public class Movie implements Serializable {
    private String title;
    private ArrayList<String> genres;
    private LocalTime duration;
    private MovieEnums.MovieStatus status;
    private MovieEnums.MovieType type;
    private MovieEnums.MovieRestriction restriction;
    private String synopsis;
    private Director director;
    private ArrayList<Actor> cast;
    private double rating;
    private ArrayList<Review> reviews;

    public Movie(String title, ArrayList<String> genres, LocalTime duration, MovieEnums.MovieStatus status, MovieEnums.MovieType type, MovieEnums.MovieRestriction restriction, String synopsis, Director director, ArrayList<Actor> cast, double rating, ArrayList<Review> reviews) {
        this.title = title;
        this.genres = genres;
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

    public ArrayList<String> getGenre() {
        return genres;
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
        double total = 0;
        for (int i = 0; i < this.reviews.size(); i++) {
            total += reviews.get(i).getRating();
        }
        total /= this.reviews.size();
        return total;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genres = genre;
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

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public String castToString() {
        String castString = "";
        for (int i = 0; i < this.cast.size(); i++) {
            castString += this.cast.get(i).toString();
            if (i != this.cast.size()-1) castString += "\n";
        }
        return castString;
    }

    public String genresToString() {
        String genresStr = "";
        for (int i = 0; i < this.genres.size(); i++) {
            if (i == 0) genresStr += this.genres.get(i);
            else genresStr += ", " + this.genres.get(i);
        }
        return genresStr;
    }

    public void printMovie() {
        System.out.println(this.title + " - " + this.genresToString() + " (" + this.duration.toString() + ") -> " + this.getRestriction().getRestriction());
    }

    @Override
    public boolean equals(Object obj) {
        Movie movie = (Movie) obj;
        return movie.getTitle().equals(this.title);
    }

    @Override
    public String toString() {
        return "Movie:\n" +
                "Title=" + this.title + "\n" +
                "Genre=" + this.genresToString() + "\n" +
                "Synopsis=" + this.synopsis + "\n" +
                "Duration=" + this.duration.toString() + "\n" +
                "Status=" + this.status.getDescription() + "\n" +
                "Type=" + this.type.getType() + "\n" +
                "Restriction=" + this.restriction.getRestriction() + "\n" +
                "Rating=" + this.getRating() + "\n" +
                "Director=\n" + this.director.toString() + "\n" +
                "Cast=\n" + this.castToString();
    }
}
