package pages;

import data_managers.MovieManager;
import models.movies.Movie;

import java.util.ArrayList;
import java.util.Scanner;

public class BrowsingPages {
    public static void showMoviesPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        System.out.println("MOVIES:");
        ArrayList<Movie> movies = MovieManager.readMovies();
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i).toString());
        }
        boolean running = true;
        while (running) {
            System.out.println("Type 'end' to return to Browsing Page");
            if (scanner.next().equals("end")) running = false;
        }
    }
}
