package models.movies;

import models.accounts.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;

/**
 *  The Review class representing a review with its attributes, methods and construction.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class Review implements Serializable {

    /**
     * An attribute showing the datetime the review was posted.
     */
    private LocalDateTime dateTimePublished;

    /**
     * An attribute holding the rating of the review.
     */
    private int rating;

    /**
     * An attribute holding the comment of the review.
     */
    private String comment;

    /**
     * An attribute holding the publisher of the review.
     */
    private User publisher;

    /**
     * An attribute holding the movie the review is about.
     */
    private Movie movie;

    /**
     * The Review constructor creates a review record.
     *
     * @param dateTimePublished The datetime the review was published.
     * @param rating The rating of the review.
     * @param comment The comment of the review.
     * @param publisher The publisher of the review.
     * @param movie The movie the review is about.
     */
    public Review(LocalDateTime dateTimePublished, int rating, String comment, User publisher, Movie movie) {
        this.dateTimePublished = dateTimePublished;
        this.rating = rating;
        this.comment = comment;
        this.publisher = publisher;
        this.movie = movie;
    }

    /**
     * The getDateTimePublished gets the datetime the review was published.
     *
     * @return The datetime the review was published as a LocalDateTime record.
     */
    public LocalDateTime getDateTimePublished() {
        return dateTimePublished;
    }

    /**
     * The getRating gets the rating of the review.
     *
     * @return The rating of the review.
     */
    public int getRating() {
        return rating;
    }

    /**
     * The getComment gets the comment of the review.
     *
     * @return The comment of the review.
     */
    public String getComment() {
        return comment;
    }

    /**
     * The getPublisher gets the publisher of the review.
     *
     * @return The publisher record of the review.
     */
    public User getPublisher() {
        return publisher;
    }

    /**
     * The getMovie method gets the movie the review is about.
     *
     * @return The movie record the review is about.
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * The setMovie method changes the movie.
     *
     * @param movie The new movie.
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * The printReview prints a simplified representation of the review.
     */
    public void printReview() {
        System.out.println("Publisher: " + this.publisher.getFName() + " " +this.publisher.getLName() + "\n" +
                "Rating: " + this.getRating() + "\n" +
                "Comment:\n" +
                this.getComment());
    }

    /**
     * Custom equals method to check if 2 reviews are the same based on their publishers and movies.
     *
     * @param obj A review to be compared.
     * @return true if the reviews have the same publishers and movies. false if not.
     */
    @Override
    public boolean equals(Object obj) {
        Review review = (Review) obj;
        return review.getPublisher().equals(this.publisher) && review.getMovie().equals(this.movie);
    }

    /**
     * The ByRatingAsc class is used for sorting the reviews by their rating in ascending order.
     */
    public static class ByRatingAsc implements Comparator<Review> {

        /**
         * Custom compare method to compare reviews based on their ratings.
         *
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return positive integer if first review has higher rating. negative if lower rating. 0 if equal.
         */
        @Override
        public int compare(Review o1, Review o2) {
            return o1.getRating() - o2.getRating();
        }
    }

    /**
     * The ByRatingDesc class is used for sorting the reviews by their rating in descending order.
     */
    public static class ByRatingDesc implements Comparator<Review> {

        /**
         * Custom compare method to compare reviews based on their ratings.
         *
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return positive integer if second review has higher rating. negative if lower rating. 0 if equal.
         */
        @Override
        public int compare(Review o1, Review o2) {
            return o2.getRating() - o1.getRating();
        }
    }

    /**
     * The ByNewest class is used for sorting the reviews by their published datetime in descending order.
     */
    public static class ByNewest implements Comparator<Review> {

        /**
         * Custom compare method to compare reviews based on published datetime.
         *
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return positive integer if second datetime is after the first. negative if before rating. 0 if equal.
         */
        @Override
        public int compare(Review o1, Review o2) {
            return o2.getDateTimePublished().compareTo(o1.dateTimePublished);
        }
    }
}
