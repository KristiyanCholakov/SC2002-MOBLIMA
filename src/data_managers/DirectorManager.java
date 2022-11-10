package data_managers;

import models.movies.Director;
import models.movies.Movie;
import pages.PageElements;

import java.io.*;
import java.util.ArrayList;

/**
 * The DirectorManager class takes care of interacting with the file where the directors are stored.
 * It performs writing, updating, reading and searching in the directors' storage.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class DirectorManager {

    /**
     * The DIRECTORS_PATH constant points to the file where directors are stored.
     */
    private static final String DIRECTORS_PATH = "src/data/directors.txt";

    /**
     * The readDirectors function reads all director records from the directors' storage.
     *
     * @return An array list of the director records from the storage. Empty array list if not any.
     */
    public static ArrayList<Director> readDirectors () {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DIRECTORS_PATH));
            ArrayList<Director> directors = (ArrayList<Director>) ois.readObject();
            ois.close();
            return directors;
        } catch (FileNotFoundException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Directors can't be read!");
        } catch (EOFException e) {
            PageElements.printConsoleMessage("File is empty. This will be the first Director.");
        } catch (IOException | ClassNotFoundException e) {
            PageElements.printConsoleMessage("Error!");
        }
        return new ArrayList<Director>();
    }

    /**
     * The writeDirector function adds a director record to the directors' storage.
     *
     * @param director The director record to be written in the storage.
     * @return true if the director record is added successfully. false if writing failed.
     */
    public static boolean writeDirector (Director director) {
        File file = new File(DIRECTORS_PATH);
        ArrayList<Director> allDirectors= readDirectors();
        allDirectors.add(director);
        if(file.exists()) file.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DIRECTORS_PATH));
            out.writeObject(allDirectors);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("Director Added!");
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Director is not saved to the database.");
            return false;
        }
    }

    /**
     * The getDirector function searches for a director with the same first and last name in the storage and provides their record.
     *
     * @param fName The first name of the director that we search
     * @param lName The last name of the director that we search
     * @return The record of the director with the same names. null if a director with these names does not exist.
     */
    public static Director getDirector (String fName, String lName) {
        ArrayList<Director> allDirectors = readDirectors();
        for (int i = 0; i < allDirectors.size(); i++) {
            Director currentDirector = allDirectors.get(i);
            if (currentDirector.getfName().equals(fName) && currentDirector.getlName().equals(lName)) {
                return currentDirector;
            }
        }
        return null;
    }


    /**
     * The updateDirector function searches if the provided director record exists and if so updates their entry.
     *
     * @param director The director record to be updated in the storage.
     * @return true if the director record is updated successfully. false if updating failed.
     */
    public static boolean updateDirector (Director director) {
        ArrayList<Director> allDirectors = readDirectors();
        Director directorToUpdate = getDirector(director.getfName(), director.getlName());
        if (directorToUpdate == null) {
            PageElements.printConsoleMessage("No such director!");
            return false;
        } else {
            allDirectors.remove(directorToUpdate);
            allDirectors.add(director);
            File file = new File(DIRECTORS_PATH);
            if(file.exists()) file.delete();
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DIRECTORS_PATH));
            out.writeObject(allDirectors);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("Director Updated!");
            ArrayList<Movie> movies = MovieManager.readMovies();
            for (int i = 0; i < movies.size(); i++) {
                Movie movie = movies.get(i);
                Director d = movie.getDirector();
                if (d.equals(director)) {
                    movie.setDirector(director);
                    MovieManager.updateMovie(movie);
                }
            }
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Director is not updated to the database.");
            return false;
        }
    }

    /**
     * The deleteDirector function searches if the provided director record exists and if so deletes their entry.
     *
     * @param director The director record to be deleted in the storage.
     * @return true if the director record is deleted successfully. false if deletion failed.
     */
    public static boolean deleteDirector (Director director) {
        File file = new File(DIRECTORS_PATH);
        ArrayList<Director> allDirectors = readDirectors();
        Director directorToDeleted = getDirector(director.getfName(), director.getlName());
        if (directorToDeleted == null) {
            PageElements.printConsoleMessage("No such director!");
            return false;
        } else {
            allDirectors.remove(directorToDeleted);
        }
        if(file.exists()) file.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DIRECTORS_PATH));
            out.writeObject(allDirectors);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("Director Deleted!");
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Director is not deleted from the database.");
            return false;
        }
    }
}
