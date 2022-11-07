package pages.admin;

import data_managers.CineplexManager;
import data_managers.MovieManager;
import models.cinemas.Cinema;
import models.cinemas.Cineplex;
import models.cinemas.ShowTime;
import models.movies.Movie;
import pages.PageElements;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class BrowsingPages {
    public static void showMoviesPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        System.out.println("MOVIES:");
        ArrayList<Movie> movies = MovieManager.readMovies();
        System.out.print("TOTAL NUMBER OF MOVIES: " + movies.size());
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i).toString()+"\n");
        }
        boolean running = true;
        while (running) {
            System.out.println("Type 'end' to return to Browsing Page");
            if (scanner.next().equals("end")) running = false;
        }
    }

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
            if (scanner.next().equals("end")) running = false;
        }
    }

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
    }
}
