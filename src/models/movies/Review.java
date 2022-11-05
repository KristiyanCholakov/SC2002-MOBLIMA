package models.movies;

import models.accounts.User;

import java.time.LocalDateTime;

public class Review {
    private LocalDateTime dateTimePublished;
    private int rating;
    private String comment;
    private User publisher;

    public Review(LocalDateTime dateTimePublished, int rating, String comment, User publisher) {
        this.dateTimePublished = dateTimePublished;
        this.rating = rating;
        this.comment = comment;
        this.publisher = publisher;
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

    @Override
    public boolean equals(Object obj) {
        Review review = (Review) obj;
        return review.publisher.equals(this.publisher);
    }
}
