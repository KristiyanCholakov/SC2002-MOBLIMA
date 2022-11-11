package pages.user;

import data_managers.MovieManager;
import data_managers.UserManager;
import models.movies.Movie;
import models.movies.Review;
import pages.MainPage;
import pages.PageElements;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  The ReviewPage class holds the functionality connected with the review pages.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class ReviewPages {

    /**
     * The reviewPage method allows users to review a movie.
     * Checks if the user already made a review for the specified movie based on the current reviews for that movie.
     * If user already made a review, they have the choice to delete the old review or overwrite the old review.
     */
    public static void reviewPage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of movie you want to review");
        Movie movie = MovieManager.getMovie(scanner.nextLine());
        if (movie == null) {
            System.out.println("No such movie");
            return;
        }
        boolean alreadyWrote = false;
        Review review = null;
        ArrayList<Review> reviews = movie.getReviews();
        for (int i = 0; i < reviews.size(); i++) {
            if (reviews.get(i).getPublisher().equals(MainPage.getCurrentUser())) {
                alreadyWrote = true;
                review = reviews.get(i);
            }
        }
        if (alreadyWrote) {
            System.out.println("You have already written a review. Choose action\n" +
                    "       1 - Delete it\n" +
                    "       2 - Delete old one and write new review\n" +
                    "       3 - Return to Previous Page");
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
                    movie.deleteReview(review);
                    MainPage.getCurrentUser().removeReview(review);
                    break;
                case 2:
                    movie.deleteReview(review);
                    MainPage.getCurrentUser().removeReview(review);
                    Review newReview = reviewInput(movie);
                    if (newReview == null) return;
                    movie.addReview(newReview);
                    MainPage.getCurrentUser().addReview(newReview);
                    break;
                case 3:
                    return;
                default:
                    PageElements.printConsoleMessage("Invalid Choice. Enter a valid choice.");
                    break;
            }
        } else {
            Review newReview = reviewInput(movie);
            if (newReview == null) return;
            movie.addReview(newReview);
            MainPage.getCurrentUser().addReview(newReview);
        }
        UserManager.updateUser(MainPage.getCurrentUser());
        MovieManager.updateMovie(movie);
    }

    /**
     * The reviewInput method represents the required format the users should input when making a review.
     * It returns a copy of the review if it is successfully made, else it returns null
     */
    public static Review reviewInput(Movie movie) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the your rating about the movie: ");
        int rating;
        try {
            rating = scanner.nextInt();
            if (rating < 1 || rating > 5) {
                PageElements.printConsoleMessage("The rating should be in the range from 1 to 5.");
            }
        } catch (InputMismatchException e) {
            PageElements.printConsoleMessage("The rating should be an integer.");
            return null;
        }
        scanner.nextLine();
        System.out.print("Enter the your comment about the movie: ");
        String comment = scanner.nextLine();
        return new Review(LocalDateTime.now(), rating, comment, MainPage.getCurrentUser(), movie);
    }
}
