package pages.user;

import data_managers.CineplexManager;
import data_managers.MovieManager;
import data_managers.UserManager;
import models.accounts.Booking;
import models.accounts.User;
import models.cinemas.Cineplex;
import models.movies.Movie;
import models.movies.Review;
import pages.MainPage;
import pages.PageElements;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  The UserAccountPages class holds the functionality connected with the user account pages.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class UserAccountPages {
    /**
     * The myAccountPage method gives the opportunity to choose what the user wants to do.
     * If the choice is invalid asks for another. If it is valid redirects to the corresponding page.
     */
    public static void myAccountPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - See my Booking\n" +
                    "       2 - See my Reviews\n" +
                    "       3 - Change Password\n" +
                    "       4 - Back to Previous Page" );
            System.out.print("Choice: ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                choice = -1;
                PageElements.printConsoleMessage("You have to enter a number!");
            }
            scanner.nextLine();
            switch (choice) {
                case 1:
                    try {
                        userBookingsPage();
                    } catch (DateTimeParseException e) {
                        PageElements.printConsoleMessage("You have to enter the date/time in a valid format!");
                    } catch (InputMismatchException e ) {
                        PageElements.printConsoleMessage("You have to enter a valid input!");
                    }
                    break;
                case 2:
                    try {
                        userReviewsPage();
                    } catch (DateTimeParseException e) {
                        PageElements.printConsoleMessage("You have to enter the date/time in a valid format!");
                    } catch (InputMismatchException e ) {
                        PageElements.printConsoleMessage("You have to enter a valid input!");
                    }
                    break;
                case 3:
                    try {
                        changePasswordPage();
                    } catch (DateTimeParseException e) {
                        PageElements.printConsoleMessage("You have to enter the date/time in a valid format!");
                    } catch (InputMismatchException e ) {
                        PageElements.printConsoleMessage("You have to enter a valid input!");
                    }
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }
    /**
     * The userBookingsPage method allows users to view their current bookings and they also have the option of cancelling a booking.
     * If the user selects -1, no bookings will be cancelled. Else, they can specify which booking they want to be cancelled.
     */
    public static void userBookingsPage () {
        Scanner scanner = new Scanner(System.in);
        User user = MainPage.getCurrentUser();
        ArrayList<Booking> bookings = user.getBookings();
        if (bookings.size() == 0) {
            System.out.println("You don't have any bookings!");
            return;
        }
        for (int i = 0; i < bookings.size(); i++) {
            Booking booking = bookings.get(i);
            System.out.println((i+1) + ")");
            System.out.println(booking.toString());
        }
        PageElements.printLine();
        System.out.println("Enter the booking you want to cancel: (enter -1 if you don't want to cancel any).");
        int bookingIdx = scanner.nextInt()-1;
        scanner.nextLine();
        if (bookingIdx < 0) {
            return;
        }
        if (bookingIdx >= bookings.size()) {
            PageElements.printConsoleMessage("Wrong booking choice!");
            return;
        }
        Booking canceled = bookings.get(bookingIdx);
        if (canceled.getDate().isBefore(LocalDate.now())) {
            PageElements.printConsoleMessage("You cannot cancel bookings that have already passed!");
            return;
        }
        user.removeBooking(canceled);
        Cineplex cineplex = CineplexManager.getCineplex(canceled.getCineplex().getName());
        if (cineplex.cancelBooking(canceled.getDate(), canceled.getShowTime(), canceled.getSeat())) {
            PageElements.printConsoleMessage("Successfully Canceled!");
        } else {
            PageElements.printConsoleMessage("Error! Seat not canceled!");
            return;
        }
        Movie movie = MovieManager.getMovie(canceled.getShowTime().getMovie().getTitle());
        movie.setTicketsSold(movie.getTicketsSold()-1);
        MovieManager.updateMovie(movie);
        CineplexManager.updateCineplex(cineplex);
        UserManager.updateUser(user);
    }
    /**
     * The userReviewsPage method allows users to view their current reviews and they also have the option of deleting a review.
     * If the user selects -1, no reviews will be cancelled. Else, they can specify which review they want to be cancelled.
     */
    public static void userReviewsPage () {
        Scanner scanner = new Scanner(System.in);
        User user = MainPage.getCurrentUser();
        ArrayList<Review> reviews = user.getReviews();
        if (reviews.size() == 0) {
            System.out.println("You don't have any reviews!");
            return;
        }
        for (int i = 0; i < reviews.size(); i++) {
            Review review = reviews.get(i);
            System.out.println((i+1) + ")");
            System.out.println("Movie: " + review.getMovie().getTitle());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Comment:\n" + review.getComment() + "\n");
        }
        PageElements.printLine();
        System.out.println("Enter the review you want to delete: (enter -1 if you don't want to cancel any)");
        int reviewIdx = scanner.nextInt()-1;
        scanner.nextLine();
        if (reviewIdx < 0) {
            return;
        }
        if (reviewIdx >= reviews.size()) {
            PageElements.printConsoleMessage("Wrong review choice!");
            return;
        }

        Review canceled = reviews.get(reviewIdx);
        user.removeReview(canceled);
        Movie movie = MovieManager.getMovie(canceled.getMovie().getTitle());
        movie.deleteReview(canceled);
        MovieManager.updateMovie(movie);
        UserManager.updateUser(user);
    }
    /**
     * The changePasswordPage method allows users to change their current password.
     * Users must enter their old password before changing their password.
     * New password must also follow the required format specified.
     */
    public static void changePasswordPage() {
        PageElements.printHeader();
        Scanner scanner = new Scanner(System.in);
        String currentPass = MainPage.getCurrentUser().getPassword();
        System.out.println("Enter your previous password:");
        if (!currentPass.equals(scanner.nextLine())) {
            PageElements.printConsoleMessage("The password is wrong!");
            return;
        }
        System.out.println("*** Password Must:\n" +
                "       contain 1 number (0-9)\n" +
                "       contain 1 uppercase letters\n" +
                "       contain 1 lowercase letters\n" +
                "       contain 1 non-alpha numeric number\n" +
                "       be 8-16 characters long\n");
        System.out.println("Enter your new password:");
        String newPass = scanner.nextLine();
        System.out.println("Confirm your new password:");
        if (!newPass.equals(scanner.nextLine())) {
            PageElements.printConsoleMessage("The passwords don't match!");
            return;
        }
        MainPage.getCurrentUser().setPassword(newPass);
        UserManager.updateUser(MainPage.getCurrentUser());
        PageElements.printConsoleMessage("Password Changed!");
    }
}
