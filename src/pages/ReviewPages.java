package pages;

import data_managers.MovieManager;
import models.movies.Movie;
import models.movies.Review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ReviewPages {

    public static void reviewPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Enter the title of movie you want to review");
            Movie movie = MovieManager.getMovie(scanner.nextLine());
            if (movie == null) {
                System.out.println("No such movie");
                return;
            }
            boolean alreadyWrote = false;
            int prevReviewIndex = -1;
            ArrayList<Review> reviews = movie.getReviews();
            for (int i = 0; i < reviews.size(); i++) {
                if(reviews.get(i).getPublisher().equals(MainPage.getCurrentUser())) {
                    alreadyWrote = true;
                    prevReviewIndex = i;
                }
            }
            if (alreadyWrote) {
                System.out.println("You have already written a review. Choose action\n" +
                        "       1 - Delete it\n" +
                        "       2 - Delete old one and write new review\n" +
                        "       3 - Return to Previous Page");
                System.out.print("Choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        reviews.remove(prevReviewIndex);
                        break;
                    case 2:
                        reviews.remove(prevReviewIndex);
                        Review review = reviewInput();
                        reviews.add(review);
                        break;
                    case 3:
                        return;
                    default:
                        PageElements.printConsoleMessage("Invalid Choice. Enter a valid choice.");
                        break;
                }
            } else {
                Review review = reviewInput();
                reviews.add(review);
            }
            movie.setReviews(reviews);
            MovieManager.updateMovie(movie);
        }
    }

    public static Review reviewInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the your rating about the movie: ");
        int rating = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the your comment about the movie: ");
        String comment = scanner.nextLine();
        return new Review(LocalDateTime.now(),rating, comment, MainPage.getCurrentUser());
    }
}
