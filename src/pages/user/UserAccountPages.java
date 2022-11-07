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

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UserAccountPages {
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
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    userBookingsPage();
                    break;
                case 2:
                    userReviewsPage();
                    break;
                case 3:
                    changePasswordPage();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }

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
            System.out.println(booking.printBooking());
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
            review.printReview();
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
