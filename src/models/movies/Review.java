package models.movies;

import models.accounts.User;
import models.movies.Movie;

import java.time.LocalDateTime;

public class Review {
    private LocalDateTime dateTimePublished;
    private int rating;
    private String comment;
    private User publisher;
    private Movie movieAbout;

    public Review(LocalDateTime dateTimePublished, int rating, String comment, User publisher, Movie movieAbout) {
        this.dateTimePublished = dateTimePublished;
        this.rating = rating;
        this.comment = comment;
        this.publisher = publisher;
        this.movieAbout = movieAbout;
    }

    public LocalDateTime getDateTimePublished() {
        return dateTimePublished;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public User getPublisher() {
        return publisher;
    }

    public Movie getMovieAbout() {
        return movieAbout;
    }

    public void setDateTimePublished(LocalDateTime dateTimePublished) {
        this.dateTimePublished = dateTimePublished;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    public void setMovieAbout(Movie movieAbout) {
        this.movieAbout = movieAbout;
    }

}
