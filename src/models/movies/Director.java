package models.movies;

import models.Person;

import java.util.Comparator;

/**
 *  The Director class representing the director record with its attributes, methods and construction.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class Director extends Person {

    /**
     * An attribute of the number of movies from the storage the director has directed.
     */
    private int numberOfMovies;

    /**
     * The Director constructor creates a new director record
     *
     * @param fName The first name of the director.
     * @param lName The last name of the director.
     * @param numberOfMovies The number of the movies from the storage the director has directed.
     */
    public Director(String fName, String lName, int numberOfMovies) {
        super(fName, lName);
        this.numberOfMovies =numberOfMovies;
    }

    /**
     * The getNumberOfMovies method gets the number of the movies from the storage the director has directed.
     * @return
     */
    public int getNumberOfMovies() {
        return numberOfMovies;
    }

    /**
     * The setNumberOfMovies method changes the number of the movies from the storage the director has directed.
     * @param numberOfMovies
     */
    public void setNumberOfMovies(int numberOfMovies) {
        this.numberOfMovies = numberOfMovies;
    }

    /**
     * The ByNumberOfMovies class is used for sorting the directors by the number of movies in descending order.
     */
    public static class ByNumberOfMovies implements Comparator<Director> {

        /**
         * Custom compare method to compare 2 directors based on their number of movies.
         *
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return Negative integer if second director has less movies than the first one. Positive if the more. 0 when equal.
         */
        @Override
        public int compare(Director o1, Director o2) {
            return o2.getNumberOfMovies() - o1.getNumberOfMovies();
        }
    }

    /**
     * Custom toString method to get the text representation of the director.
     *
     * @return The text representation of the director record with its attributes.
     */
    @Override
    public String toString() {
        return "Director:\n" +
                "F_Name=" + super.getFName() + "\n" +
                "L_Name=" + super.getLName() + "\n" +
                "NumberOfMovies=" + this.numberOfMovies;
    }
}
