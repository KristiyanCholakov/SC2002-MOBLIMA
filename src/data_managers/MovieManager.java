package data_managers;

import models.accounts.User;
import models.movies.Movie;
import pages.PageElements;

import java.io.*;
import java.util.ArrayList;

public class MovieManager {

    private static final String MOVIES_PATH = "src/data/movies.txt";


    public static ArrayList<Movie> readMovies () {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MOVIES_PATH));
            ArrayList<Movie> movies = (ArrayList<Movie>) ois.readObject();
            ois.close();
            return movies;
        } catch (ClassNotFoundException | IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! User is not saved to the database.");
        }
        return new ArrayList<Movie>();
    }

    public static boolean writeMovie (Movie movie) {
        File file = new File(MOVIES_PATH);
        ArrayList<Movie> allMovies = readMovies();
        allMovies.add(movie);
        if(file.exists()) file.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(MOVIES_PATH));
            out.writeObject(allMovies);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("Movie Added!");
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! User is not saved to the database.");
            return false;
        }
    }

    public static Movie getMovie(String name) {
        ArrayList<Movie> allMovies = readMovies();
        for (int i = 0; i < allMovies.size(); i++) {
            Movie currentMovie = allMovies.get(i);
            if (currentMovie.getName().equals(name)) {
                return currentMovie;
            }
        }
        return null;
    }

    public static boolean updateMovie (Movie movie) {
        ArrayList<Movie> allMovies = readMovies();
        for (int i = 0; i < allMovies.size(); i++) {
            Movie currentMovie = allMovies.get(i);
            if (currentMovie.getName().equals(movie.getName())) {
                allMovies.remove(currentMovie);
                allMovies.add(movie);
            }
        }
        return false;
    }
}
