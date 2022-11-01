package data_managers;

import models.movies.Director;
import models.movies.Movie;
import pages.PageElements;

import javax.swing.plaf.ComponentInputMapUIResource;
import java.io.*;
import java.util.ArrayList;

public class DirectorManager {

    private static final String DIRECTORS_PATH = "src/data/directors.txt";


    public static ArrayList<Director> readDirectors () {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DIRECTORS_PATH));
            ArrayList<Director> directors = (ArrayList<Director>) ois.readObject();
            ois.close();
            return directors;
        } catch (ClassNotFoundException | IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Director is not read from the database.");
        }
        return new ArrayList<Director>();
    }

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

    public static boolean updateDirector (Director director) {
        File file = new File(DIRECTORS_PATH);
        ArrayList<Director> allDirectors = readDirectors();
        Director directorToUpdate = getDirector(director.getfName(), director.getlName());
        if (directorToUpdate == null) {
            PageElements.printConsoleMessage("No such director!");
            return false;
        } else {
            allDirectors.remove(directorToUpdate);
            allDirectors.add(director);
        }
        if(file.exists()) file.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DIRECTORS_PATH));
            out.writeObject(allDirectors);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("Director Updated!");
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Director is not updated to the database.");
            return false;
        }
    }

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
