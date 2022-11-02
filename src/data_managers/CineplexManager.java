package data_managers;

import models.cinemas.Cineplex;
import pages.PageElements;

import java.io.*;
import java.util.ArrayList;

public class CineplexManager {
    private static final String CINEPLEXES_PATH = "src/data/cineplexes.txt";


    public static ArrayList<Cineplex> readCineplexes() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CINEPLEXES_PATH));
            ArrayList<Cineplex> movies = (ArrayList<Cineplex>) ois.readObject();
            ois.close();
            return movies;
        } catch (FileNotFoundException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Cineplexes can't be read!");
        } catch (EOFException e) {
            PageElements.printConsoleMessage("File was empty. This is the first item in it.");
        } catch (IOException | ClassNotFoundException e) {
            PageElements.printConsoleMessage("Error!");
        }
        return new ArrayList<Cineplex>();
    }

    public static boolean writeCineplex (Cineplex cineplex) {
        File file = new File(CINEPLEXES_PATH);
        ArrayList<Cineplex> allCineplexes = readCineplexes();
        allCineplexes.add(cineplex);
        if(file.exists()) file.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(CINEPLEXES_PATH));
            out.writeObject(allCineplexes);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("Cineplex Added!");
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Cineplex is not saved to the database.");
            return false;
        }
    }

    public static Cineplex getCineplex(String name) {
        ArrayList<Cineplex> allCineplexes = readCineplexes();
        for (int i = 0; i < allCineplexes.size(); i++) {
            Cineplex currentCineplex = allCineplexes.get(i);
            if (currentCineplex.getName().equals(name)) {
                return currentCineplex;
            }
        }
        return null;
    }

    public static boolean updateCineplex (Cineplex cineplex) {
        ArrayList<Cineplex> allCineplexes = readCineplexes();
        Cineplex cineplexToUpdated = getCineplex(cineplex.getName());
        if (cineplexToUpdated == null) {
            PageElements.printConsoleMessage("No such cineplex!");
            return false;
        } else {
            allCineplexes.remove(cineplexToUpdated);
            allCineplexes.add(cineplex);
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(CINEPLEXES_PATH));
            out.writeObject(allCineplexes);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("Cineplex Updated!");
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Cineplex is not updated to the database.");
            return false;
        }
    }
}
