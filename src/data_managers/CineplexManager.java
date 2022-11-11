package data_managers;

import models.cinemas.Cineplex;
import pages.PageElements;

import java.io.*;
import java.util.ArrayList;

/**
 * The CineplexManager class takes care of interacting with the file where the cineplexes are stored.
 * It performs writing, updating, reading and searching in the cineplexes' storage.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class CineplexManager {

    /**
     * The CINEPLEXES_PATH constant points to the file where cineplexes are stored.
     */
    private static final String CINEPLEXES_PATH = "src/data/cineplexes.txt";


    /**
     * The readCineplexes function reads all cineplex records from the cineplexes' storage.
     *
     * @return An array list of the cineplex records from the storage. Empty array list if not any.
     */
    public static ArrayList<Cineplex> readCineplexes() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CINEPLEXES_PATH));
            ArrayList<Cineplex> movies = (ArrayList<Cineplex>) ois.readObject();
            ois.close();
            return movies;
        } catch (FileNotFoundException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Cineplexes can't be read!");
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            PageElements.printConsoleMessage("Error!");
        }
        return new ArrayList<Cineplex>();
    }

    /**
     * The writeCineplex function adds a cineplex record to the cineplexes' storage.
     *
     * @param cineplex The cineplex record to be written in the storage.
     * @return true if the cineplex record is added successfully. false if writing failed.
     */
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

    /**
     * The getCineplex function searches for a cineplex with the same name in the storage and provides their record.
     *
     * @param name The name of the cineplex that we search
     * @return The record of the cineplex with the same name. null if a cineplex with this name does not exist.
     */
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

    /**
     * The updateCineplex function searches if the provided cineplex record exists and if so updates their entry.
     *
     * @param cineplex The cineplex record to be updated in the storage.
     * @return true if the cineplex record is updated successfully. false if updating failed.
     */
    public static boolean updateCineplex (Cineplex cineplex) {
        ArrayList<Cineplex> allCineplexes = readCineplexes();
        Cineplex cineplexToUpdated = getCineplex(cineplex.getName());
        if (cineplexToUpdated == null) {
            PageElements.printConsoleMessage("No such cineplex!");
            return false;
        } else {
            allCineplexes.remove(cineplexToUpdated);
            allCineplexes.add(cineplex);
            File file = new File(CINEPLEXES_PATH);
            if(file.exists()) file.delete();
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

    /**
     * The deleteCineplex function searches if the provided cineplex record exists and if so deletes their entry.
     *
     * @param cineplex The cineplex record to be deleted in the storage.
     * @return true if the cineplex record is deleted successfully. false if deletion failed.
     */
    public static boolean deleteCineplex (Cineplex cineplex) {
        ArrayList<Cineplex> allCineplexes = readCineplexes();
        Cineplex cineplexToUpdated = getCineplex(cineplex.getName());
        if (cineplexToUpdated == null) {
            PageElements.printConsoleMessage("No such cineplex!");
            return false;
        } else {
            allCineplexes.remove(cineplexToUpdated);
            File file = new File(CINEPLEXES_PATH);
            if(file.exists()) file.delete();
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
