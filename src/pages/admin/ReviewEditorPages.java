package pages.admin;

import data_managers.MovieManager;
import data_managers.UserManager;
import models.accounts.User;
import models.movies.Movie;
import models.movies.Review;
import pages.PageElements;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  The ReviewEditorPages class holds the functionality connected with the review editing pages for the admin.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class ReviewEditorPages {

    /**
     * The reviewEditorPage method gives the opportunity to choose what the admin wants to do.
     * If the choice is invalid asks for another. If it is valid redirects to the corresponding page.
     */
    public static void reviewEditorPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        boolean running = true;
        while (running) {
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Delete Review\n" +
                    "       2 - Back to Editor Portal");
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
                    deleteReviewsPage();
                    break;
                case 2:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }

    /**
     * The deleteReviewsPage method allows the admins to delete a particular review based on the movie it is about and
     * user that published it.
     * If the review exists, it is removed from the storage. If there is not such a review, redirects to the previous page.
     */
    public static void deleteReviewsPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        System.out.println("Enter the title of the movie the review is about:");
        String movieTitle = scanner.nextLine();
        Movie movie = MovieManager.getMovie(movieTitle);
        if (movie == null) {
            PageElements.printConsoleMessage("The movie doesn't exits.");
            return;
        }
        System.out.println("Enter the email/username of the publisher of the review:");
        String username = scanner.nextLine();
        User user = UserManager.getUser(username);
        if (user == null) {
            PageElements.printConsoleMessage("The user doesn't exits.");
            return;
        }
        ArrayList<Review> reviews = movie.getReviews();
        Review reviewToDelete = null;
        for (int i = 0; i < reviews.size(); i++) {
            Review review = reviews.get(i);
            if (review.getPublisher().equals(user)) {
                reviewToDelete = review;
                break;
            }
        }
        if (reviewToDelete == null) {
            PageElements.printConsoleMessage("The selected used hasn't reviews the movie.");
            return;
        }
        movie.deleteReview(reviewToDelete);
        user.removeReview(reviewToDelete);
        UserManager.updateUser(user);
        MovieManager.updateMovie(movie);
    }
}
