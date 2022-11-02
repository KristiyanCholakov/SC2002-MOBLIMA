package data_managers;

import models.movies.Director;
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
        } catch (FileNotFoundException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Movies can't be read!");
        } catch (EOFException e) {
            PageElements.printConsoleMessage("File was empty. This is the first item in it.");
        } catch (IOException | ClassNotFoundException e) {
            PageElements.printConsoleMessage("Error!");
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
            PageElements.printConsoleMessage("Error: Invalid Path! Movie is not saved to the database.");
            return false;
        }
    }

    public static Movie getMovie(String title) {
        ArrayList<Movie> allMovies = readMovies();
        for (int i = 0; i < allMovies.size(); i++) {
            Movie currentMovie = allMovies.get(i);
            if (currentMovie.getTitle().equals(title)) {
                return currentMovie;
            }
        }
        return null;
    }

    public static boolean updateMovie (Movie movie) {
        ArrayList<Movie> allMovies = readMovies();
        Movie movieToUpdated = getMovie(movie.getTitle());
        if (movieToUpdated == null) {
            PageElements.printConsoleMessage("No such movie!");
            return false;
        } else {
            allMovies.remove(movieToUpdated);
            allMovies.add(movie);
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(MOVIES_PATH));
            out.writeObject(allMovies);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("Movie Updated!");
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Movie is not updated to the database.");
            return false;
        }
    }
}
