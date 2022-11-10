package models.accounts;

import models.Person;
import models.movies.Review;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *  The User class representing the user record with its attributes, methods and construction.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class User extends Person {

    /**
     * An attribute showing the username of the user.
     */
    private String username;

    /**
     * An attribute showing the email of the user.
     */
    private String email;

    /**
     * An attribute showing the password of the user.
     */
    private String password;

    /**
     * An attribute showing the birthdate of the user.
     */
    private LocalDate birthdate;

    /**
     * An attribute showing bookings of the user.
     */
    private ArrayList<Booking> bookings;

    /**
     * An attribute showing the reviews of the user.
     */
    private ArrayList<Review> reviews;

    /**
     * The User constructor creates a new user. Identified by their first and last name, username, email, password and
     * birthdate.
     *
     * @param fName The first name of the user.
     * @param lName The last name of the user.
     * @param username The username of teh user.
     * @param email The email of the user.
     * @param password The password of the user.
     * @param birthdate The birthdate of the user.
     */
    public User(String fName, String lName, String username, String email, String password, LocalDate birthdate) {
        super(fName, lName);
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.bookings = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    /**
     * The getBookings method gets the bookings of the user.
     *
     * @return Array list of the bookings of the user
     */
    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    /**
     * The getUsername method gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * The getEmail method gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * The getPassword method gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * The getReviews method gets the reviews of the user.
     *
     * @return Array list of the reviews of the user.
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * The setPassword method changes the password of the user.
     *
     * @param password The new password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * The getBirthdate method gets the birthdate of the user.
     *
     * @return The birthdate of the user as a LocalDate record.
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }


    /**
     * The getYears method calculates the years of the user.
     *
     * @return The years of the user.
     */
    public int getYears() {
        return  this.birthdate.until(LocalDate.now()).getYears();
    }

    /**
     * The addBooking method adds a booking to the array list of booking of the user.
     *
     * @param booking The new booking to be added.
     */
    public void addBooking (Booking booking) {
        this.bookings.add(booking);
    }

    /**
     * The removeBooking method removes a booking to the array list of booking of the user.
     *
     * @param booking The new booking to be removed.
     */
    public void removeBooking (Booking booking) {
        this.bookings.remove(booking);
    }

    /**
     * The addReview method adds a review to the array list of reviews of the user.
     *
     * @param review The new review to be added.
     */
    public void addReview (Review review) {
        this.reviews.add(review);
    }

    /**
     * The removeReview method removes a booking to the array list of reviews of the user.
     *
     * @param review The review to be removed.
     */
    public void removeReview (Review review) {
        this.reviews.remove(review);
    }

    /**
     * Custom equals method to determine if two users are the same based on comparing their emails.
     *
     * @param obj Another user to be compared.
     * @return true if the users are the same based on the custom requirement. false if not.
     */
    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return user.email.equals(this.email);
    }

    /**
     * Custom toString function to get the user record as a string
     *
     * @return A string of the user with their most important attributes.
     */
    @Override
    public String toString() {
        return "User:\n" +
                "Username=" + this.username + "\n" +
                "Email=" + this.email + "\n" +
                "Password=" + this.password + "\n" +
                "F_Name=" + super.getfName() + "\n" +
                "L_Name=" + super.getlName() + "\n" +
                "Birthday=" + getBirthdate().toString();
    }
}
