package models.movies;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *  The Movie class representing a movie with its attributes, methods and construction.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class Movie implements Serializable {

    /**
     * An attribute holding the title of the movie.
     */
    private String title;

    /**
     * An attribute holding the genres of the movie.
     */
    private ArrayList<String> genres;

    /**
     * An attribute holding the duration of the movie.
     */
    private LocalTime duration;

    /**
     * An attribute holding the status of the movie.
     */
    private MovieEnums.MovieStatus status;

    /**
     * An attribute holding if the movie is a blockbuster.
     */
    private boolean blockbuster;

    /**
     * An attribute holding the type of the movie.
     */
    private MovieEnums.MovieType type;

    /**
     * An attribute holding the restriction of the movie.
     */
    private MovieEnums.MovieRestriction restriction;

    /**
     * An attribute holding the synopsis of the movie.
     */
    private String synopsis;

    /**
     * An attribute holding the director of the movie.
     */
    private Director director;

    /**
     * An attribute holding the cast of the movie.
     */
    private ArrayList<Actor> cast;

    /**
     * An attribute holding the reviews of the movie.
     */
    private ArrayList<Review> reviews;

    /**
     * An attribute holding the number of ticked sold for the movie.
     */
    private int ticketsSold;

    /**
     * The Movie constructor creates a new movie record.
     * @param title The title of the movie.
     * @param genres The genres of the movie.
     * @param duration The duration of the movie.
     * @param status The status of the movie.
     * @param blockbuster If the movie is a blockbuster.
     * @param type The type of the movie.
     * @param restriction The restriction of the movie.
     * @param synopsis The synopsis of the movie.
     * @param director The director of the movie.
     * @param cast The cast of the movie.
     */
    public Movie(String title, ArrayList<String> genres, LocalTime duration, MovieEnums.MovieStatus status, boolean blockbuster, MovieEnums.MovieType type, MovieEnums.MovieRestriction restriction, String synopsis, Director director, ArrayList<Actor> cast) {
        this.title = title;
        this.genres = genres;
        this.duration = duration;
        this.status = status;
        this.blockbuster = blockbuster;
        this.type = type;
        this.restriction = restriction;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.reviews = new ArrayList<>();
        this.ticketsSold = 0;
    }

    /**
     * The getTitle method gets the title of the movie.
     *
     * @return The title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * The getGenres method gets the genres of the movie.
     *
     * @return The genres of the movie
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * The getDuration method gets the duration of the movie.
     *
     * @return The duration of the movie as a LocalTime record.
     */
    public LocalTime getDuration() {
        return duration;
    }

    /**
     * The getStatus method gets the status of the movie.
     *
     * @return The status of the movie as an MovieStatus enum.
     */
    public MovieEnums.MovieStatus getStatus() {
        return status;
    }

    /**
     * The getType method gets the type of the movie.
     *
     * @return The type of the movie as an MovieType enum.
     */
    public MovieEnums.MovieType getType() {
        return type;
    }

    /**
     * The getRestriction method gets the restriction of the movie.
     *
     * @return The restriction of the movie as an MovieRestriction enum.
     */
    public MovieEnums.MovieRestriction getRestriction() {
        return restriction;
    }

    /**
     * The getSynopsis method gets the synopsis of the movie.
     *
     * @return The synopsis of the movie.
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * The getDirector method gets the director of the movie.
     *
     * @return The director record of the movie.
     */
    public Director getDirector() {
        return director;
    }

    /**
     * The getCast gets the cast of the movie.
     *
     * @return The array list of actor record of the movie.
     */
    public ArrayList<Actor> getCast() {
        return cast;
    }

    /**
     * The getRating method gets the rating of the movie by computing the average from all reviews.
     *
     * @return The rating of the movie.
     */
    public double getRating() {
        if (this.reviews.size() == 0) return 0;
        double total = 0;
        for (int i = 0; i < this.reviews.size(); i++) {
            total += reviews.get(i).getRating();
        }
        total = total / this.reviews.size();
        return total;
    }

    /**
     * The getReviews method gets the reviews of the movie.
     *
     * @return The arraylist of reviews about the movie.
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * The getTicketsSold method gets the number of tickets sold for the movie.
     *
     * @return The number of tickets sold for the movie.
     */
    public int getTicketsSold() {
        return ticketsSold;
    }

    /**
     * The isBlockbuster method gets if the movie is a blockbuster.
     *
     * @return true if the movie is a blockbuster. false if it is not.
     */
    public boolean isBlockbuster() {
        return blockbuster;
    }

    /**
     * The setBlockbuster method changes if the movie is a blockbuster.
     *
     * @param blockbuster The new value of whether the movie is a blockbuster.
     */
    public void setBlockbuster(boolean blockbuster) {
        this.blockbuster = blockbuster;
    }

    /**
     * The setTicketsSold method changes the number of tickets sold for the movie.
     *
     * @param ticketsSold The new number of tickets sold for the movie.
     */
    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    /**
     * The setGenre method changes the genre of the movie.
     *
     * @param genre The new genre of the movie.
     */
    public void setGenre(ArrayList<String> genre) {
        this.genres = genre;
    }

    /**
     * The setDuration method changes the duration of the movie.
     *
     * @param duration The new duration of the movie.
     */
    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    /**
     * The setStatus method changes the status of the movie.
     *
     * @param status The new status of the movie.
     */
    public void setStatus(MovieEnums.MovieStatus status) {
        this.status = status;
    }

    /**
     * The setType method changes the type of the movie.
     *
     * @param type The new type of the movie.
     */
    public void setType(MovieEnums.MovieType type) {
        this.type = type;
    }

    /**
     * The setRestriction changes the restriction of the movie.
     *
     * @param restriction The new restriction of the movie.
     */
    public void setRestriction(MovieEnums.MovieRestriction restriction) {
        this.restriction = restriction;
    }

    /**
     * The setSynopsis method changes the synopsis of the movie.
     *
     * @param synopsis The new synopsis of the movie.
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * The setDirector method changes the director of the movie.
     *
     * @param director The new director of the movie.
     */
    public void setDirector(Director director) {
        this.director = director;
    }

    /**
     * The setCast method changes the cast of the movie.
     *
     * @param cast The new cast of the movie.
     */
    public void setCast(ArrayList<Actor> cast) {
        this.cast = cast;
    }

    /**
     * The castToString method gets the text representation of the cast.
     *
     * @return The text representation of the cast.
     */
    public String castToString() {
        String castString = "";
        for (int i = 0; i < this.cast.size(); i++) {
            castString += this.cast.get(i).toString();
            if (i != this.cast.size()-1) castString += "\n";
        }
        return castString;
    }

    /**
     * The genresToString method concatenates the genres to a string.
     *
     * @return String of the genres of the movie.
     */
    public String genresToString() {
        String genresStr = "";
        for (int i = 0; i < this.genres.size(); i++) {
            if (i == 0) genresStr += this.genres.get(i);
            else genresStr += ", " + this.genres.get(i);
        }
        return genresStr;
    }

    /**
     * The addReview method adds a review to the reviews of the movie.
     *
     * @param review The review to be added.
     */
    public void addReview(Review review) {
        this.reviews.add(review);
    }

    /**
     * The deleteReview method deletes a review to the reviews of the movie.
     *
     * @param review The review to be deleted.
     */
    public void deleteReview(Review review) {
        this.reviews.remove(review);
    }

    /**
     * The printMovie method prints a simplified information of the movie.
     */
    public void printMovie() {
        System.out.println(this.title +" (Directed by: " + this.getDirector().getFName() + " " + this.getDirector().getLName() + ") - " + this.genresToString() + " (" + this.duration.toString() + ") -> " + this.getRestriction().getRestriction());
    }

    /**
     * Custom equals method to check if 2 movies are the same based on their titles.
     *
     * @param obj The movie to be compared.
     * @return true if the movies have the same titles. false if not.
     */
    @Override
    public boolean equals(Object obj) {
        Movie movie = (Movie) obj;
        return movie.getTitle().equals(this.title);
    }

    /**
     * Custom toString method to get the text representation of the movie.
     *
     * @return The text representation of the movie record and its attributes.
     */
    @Override
    public String toString() {
        return "Movie:\n" +
                "Title=" + this.title + "\n" +
                "Genre=" + this.genresToString() + "\n" +
                "Synopsis=" + this.synopsis + "\n" +
                "Duration=" + this.duration.toString() + "\n" +
                "Status=" + this.status.getDescription() + "\n" +
                "Blockbuster=" + this.blockbuster + "\n" +
                "Type=" + this.type.getType() + "\n" +
                "Restriction=" + this.restriction.getRestriction() + "\n" +
                "Rating=" + this.getRating() + "\n" +
                "Director=\n" + this.director.toString() + "\n" +
                "Cast=\n" + this.castToString();
    }

    /**
     * The ByRating class is used for sorting the movies by the rating in descending order.
     */
    public static class ByRating implements Comparator<Movie> {

        /**
         * Custom compare method to compare 2 movies based on their rating.
         *
         * @param o1 the first movie to be compared.
         * @param o2 the second movie to be compared.
         * @return 1 if first movie has lower rating than the second movie. -1 if higher. 0 if equal
         */
        @Override
        public int compare(Movie o1, Movie o2) {
            if (o1.getRating() < o2.getRating()) return 1;
            if (o1.getRating() > o2.getRating()) return -1;
            return 0;
        }
    }

    /**
     * The BySales class is used for sorting the movies by the number of sales in descending order.
     */
    public static class BySales implements Comparator<Movie> {

        /**
         * Custom compare method to compare 2 movies based on the number of sales.
         *
         * @param o1 the first movie to be compared.
         * @param o2 the second movie to be compared.
         * @return 1 if first movie has less sales than the second movie. -1 if more. 0 if equal
         */
        @Override
        public int compare(Movie o1, Movie o2) {
            return o2.getTicketsSold() - o1.getTicketsSold();
        }
    }
}
