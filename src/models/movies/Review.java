package models.movies;

import models.accounts.User;
import models.cinemas.ShowTime;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;

public class Review implements Serializable {
    private LocalDateTime dateTimePublished;
    private int rating;
    private String comment;
    private User publisher;
    private Movie movie;

    public Review(LocalDateTime dateTimePublished, int rating, String comment, User publisher, Movie movie) {
        this.dateTimePublished = dateTimePublished;
        this.rating = rating;
        this.comment = comment;
        this.publisher = publisher;
        this.movie = movie;
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

    public Movie getMovie() {
        return movie;
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

    public void printReview() {
        System.out.println("Publisher: " + this.publisher.getfName() + " " +this.publisher.getlName() + "\n" +
                "Rating: " + this.getRating() + "\n" +
                "Comment:\n" +
                this.getComment());
    }

    @Override
    public boolean equals(Object obj) {
        Review review = (Review) obj;
        return review.getPublisher().equals(this.publisher) && review.getMovie().equals(this.movie);
    }

    public static class ByRatingAsc implements Comparator<Review> {

        @Override
        public int compare(Review o1, Review o2) {
            return o1.getRating() - o2.getRating();
        }
    }

    public static class ByRatingDesc implements Comparator<Review> {

        @Override
        public int compare(Review o1, Review o2) {
            return o2.getRating() - o1.getRating();
        }
    }

    public static class ByNewest implements Comparator<Review> {

        @Override
        public int compare(Review o1, Review o2) {
            return o2.getDateTimePublished().compareTo(o1.dateTimePublished);
        }
    }
}
