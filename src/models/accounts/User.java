package models.accounts;

import models.Person;
import models.movies.Review;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class User extends Person {
    private String username;
    private String email;
    private String password;

    private LocalDate birthdate;

    private ArrayList<Booking> bookings;

    private ArrayList<Review> reviews;

    public User(String fName, String lName, String username, String email, String password, LocalDate birthdate) {
        super(fName, lName);
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.bookings = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public int getYears() {
        return  this.birthdate.until(LocalDate.now()).getYears();
    }

    public void addBooking (Booking booking) {
        this.bookings.add(booking);
    }

    public void removeBooking (Booking booking) {
        this.bookings.remove(booking);
    }

    public void addReview (Review review) {
        this.reviews.add(review);
    }

    public void removeReview (Review review) {
        this.reviews.remove(review);
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return user.email.equals(this.email);
    }

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
