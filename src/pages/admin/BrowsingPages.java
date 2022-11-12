package pages.admin;

import data_managers.CineplexManager;
import data_managers.HolidayManager;
import data_managers.MovieManager;
import models.Holiday;
import models.cinemas.Cineplex;
import models.cinemas.ShowTime;
import models.movies.Movie;
import models.movies.Review;
import pages.PageElements;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  The BrowsingPages class holds the functionality connected with the browsing pages for the admin.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class BrowsingPages {

    /**
     * The showMoviesPage method prints all the movies available in the storage.
     * Waits for the admin to type "end" to return to the previous page.
     */
    public static void showMoviesPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        System.out.println("MOVIES:");
        ArrayList<Movie> movies = MovieManager.readMovies();
        System.out.println("TOTAL NUMBER OF MOVIES: " + movies.size());
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i).toString()+"\n");
        }
        boolean running = true;
        while (running) {
            System.out.println("Type 'end' to return to Browsing Page");
            if (scanner.nextLine().equals("end")) running = false;
        }
    }

    /**
     * The showCineplexesPage method prints all the cineplexes available in the storage.
     * Waits for the admin to type "end" to return to the previous page.
     */
    public static void showCineplexesPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        System.out.println("CINEPLEXES:");
        ArrayList<Cineplex> cineplexes = CineplexManager.readCineplexes();
        System.out.print("TOTAL NUMBER OF CINEPLEXES: " + cineplexes.size());
        for (int i = 0; i < cineplexes.size(); i++) {
            System.out.println(cineplexes.get(i).toString());
        }
        boolean running = true;
        while (running) {
            System.out.println("Type 'end' to return to Browsing Page");
            if (scanner.nextLine().equals("end")) running = false;
        }
    }

    /**
     * The showSchedules method prints all the schedules available in the storage for a given cineplex.
     * The admin is expected to enter a cineplex name.
     * If valid, shows the schedules. If not, returns to previous page
     * Waits for the admin to type "end" to return to the previous page.
     */
    public static void showSchedules() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        System.out.println("Enter the cineplex name whose schedules you want to see:");
        String cineplexName = scanner.nextLine();
        Cineplex cineplex =  CineplexManager.getCineplex(cineplexName);
        if (cineplex == null) {
            PageElements.printConsoleMessage("No such cineplex!");
            return;
        }
        for (LocalDate date: cineplex.getSchedules().keySet()) {
            System.out.println("The schedule for " + date.toString());
            ArrayList<ShowTime> schedule = cineplex.getSchedules().get(date);
            for (int i = 0; i < schedule.size(); i++) {
                ShowTime showTime = schedule.get(i);
                System.out.println(showTime.toString()+"\n");
            }
        };
        boolean running = true;
        while (running) {
            System.out.println("Type 'end' to return to Browsing Page");
            if (scanner.nextLine().equals("end")) running = false;
        }
    }

    /**
     * The showReviewsPage prints all the reviews for the corresponding movies in the storage.
     * Waits for the admin to type "end" to return to the previous page.
     */
    public static void showReviewsPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        ArrayList<Movie> movies = MovieManager.readMovies();
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i).getTitle());
            ArrayList<Review> reviews = movies.get(i).getReviews();
            for (int j = 0; j < reviews.size(); j++) {
                reviews.get(j).printReview();
                System.out.println(reviews.get(j).getPublisher().getEmail());
                System.out.println("");
            }
            PageElements.printLine();
        }
        boolean running = true;
        while (running) {
            System.out.println("Type 'end' to return to Browsing Page");
            if (scanner.nextLine().equals("end")) running = false;
        }
    }

    /**
     * The showHolidaysPage prints all the holidays in the storage.
     * Waits for the admin to type "end" to return to the previous page.
     */
    public static void showHolidaysPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        ArrayList<Holiday> holidays = HolidayManager.readHolidays();
        for (int i = 0; i < holidays.size(); i++) {
            System.out.println(holidays.get(i).getName());
            System.out.println("From " + holidays.get(i).getStartDate().toString() + ", To " + holidays.get(i).getEndDate());
            System.out.println("Added Price: " + holidays.get(i).getAdditionalPrice());
            PageElements.printLine();
        }
        boolean running = true;
        while (running) {
            System.out.println("Type 'end' to return to Browsing Page");
            if (scanner.nextLine().equals("end")) running = false;
        }
    }
}
