package pages.user;

import data_managers.ActorManager;
import data_managers.DirectorManager;
import data_managers.MovieManager;
import models.movies.Actor;
import models.movies.Director;
import models.movies.Movie;
import models.movies.Review;
import pages.PageElements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExplorePages {
    public static void explorePages () {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - See Reviews about a Movie\n" +
                    "       2 - Top 5 Movies by Rating\n" +
                    "       3 - Top 5 Movies by Ticket Sales\n" +
                    "       4 - See Actors\n" +
                    "       5 - See Directors\n" +
                    "       6 - Back to User Portal");
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
                    movieReviewsPage();
                    break;
                case 2:
                    top5MoviesPage("Rating");
                    break;
                case 3:
                    top5MoviesPage("Tickets Sold");
                    break;
                case 4:
                    exploreActors();
                    break;
                case 5:
                    exploreDirectors();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }

    public static void movieReviewsPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        System.out.println("Enter the title of the movie whose reviews you want to see:");
        Movie movie = MovieManager.getMovie(scanner.nextLine());
        if (movie == null) {
            PageElements.printConsoleMessage("No such movie!");
            return;
        }
        if (movie.getReviews().size() == 0) {
            PageElements.printConsoleMessage("No reviews for this movie!");
            return;
        }
        System.out.println("Select the sorting of the reviews:\n" +
                "(Type the number of the choice)\n" +
                "       1 - Ascending By Rating\n" +
                "       2 - Descending By Rating\n" +
                "       3 - Newest");
        System.out.print("Choice: ");
        int order;
        try {
            order = scanner.nextInt();
        } catch (InputMismatchException e) {
            order = -1;
            PageElements.printConsoleMessage("You have to enter a number!");
        }
        scanner.nextLine();
        ArrayList<Review> reviews = movie.getReviews();
        if (order == 1) {
            Collections.sort(reviews, new Review.ByRatingAsc());
        } else if (order == 2){
            Collections.sort(reviews, new Review.ByRatingDesc());
        } else {
            Collections.sort(reviews, new Review.ByNewest());
        }
        for (int i = 0; i < reviews.size(); i++) {
            reviews.get(i).printReview();
        }
    }

    public static void top5MoviesPage(String by) {
        PageElements.printHeader();
        ArrayList<Movie> movies = MovieManager.readMovies();
        if (by.equals("Rating")) {
            Collections.sort(movies, new Movie.ByRating());
        } else {
            Collections.sort(movies, new Movie.BySales());
        }
        int x;
        if ( movies.size() < 5) x = movies.size();
        else x = 5;
        for (int i = 0; i < x; i++) {
            System.out.println((i+1) + ")");
            movies.get(i).printMovie();
            if (by.equals("Rating"))  System.out.println(by + ": " + movies.get(i).getRating());
            else System.out.println(by + ": " + movies.get(i).getTicketsSold());
        }
    }

    public static void exploreActors() {
        PageElements.printHeader();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Actor> actors = ActorManager.readActors();
        System.out.println("Select the sorting of the reviews:\n" +
                "(Type the number of the choice)\n" +
                "       1 - Descending By Number of Movies\n" +
                "       2 - Descending By Number of Oscars\n");
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
                Collections.sort(actors, new Actor.ByNumberOfMovies());
                break;
            case 2:
                Collections.sort(actors, new Actor.ByNumberOfOscars());
                break;
            default:
                PageElements.printConsoleMessage("Invalid Choice!");
                return;
        }
        System.out.println("ACTORS:\n");
        for (int i = 0; i < actors.size(); i++) {
            Actor  actor = actors.get(i);
            System.out.println((i+1) + ") " + actor.getFullName() + ", Oscars: " + actor.getNumberOfOscars() + " and Movies: " + actor.getNumberOfMovies());
        }
    }

    public static void exploreDirectors() {
        PageElements.printHeader();
        ArrayList<Director> directors = DirectorManager.readDirectors();
        Collections.sort(directors, new Director.ByNumberOfMovies());
        System.out.println("DIRECTORS:\n");
        for (int i = 0; i < directors.size(); i++) {
            Director actor = directors.get(i);
            System.out.println((i+1) + ") " + actor.getFullName() + ", Movies: " + actor.getNumberOfMovies());
        }
    }
}
