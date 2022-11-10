package data_managers;

import models.Prices;
import pages.PageElements;

import java.io.*;
import java.util.ArrayList;

/**
 * The PricesManager class takes care of interacting with the file where the prices are stored.
 * It performs writing, reading and searching in the prices' storage.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class PricesManager {

    /**
     * The PRICES_PATH constant points to the file where prices are stored.
     */
    private static final String PRICES_PATH = "src/data/prices.txt";

    /**
     * The readPrices function reads all prices records from the prices' storage.
     *
     * @return An array list of the prices from the storage. Empty array list if not any.
     */
    public static ArrayList<Prices> readPrices() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRICES_PATH));
            ArrayList<Prices> prices = (ArrayList<Prices>) ois.readObject();
            ois.close();
            return prices;
        } catch (FileNotFoundException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Prices can't be read!");
        } catch (EOFException e) {
            PageElements.printConsoleMessage("File was empty. This is the first item in it.");
        } catch (IOException | ClassNotFoundException e) {
            PageElements.printConsoleMessage("Error!");
        }
        return new ArrayList<Prices>();
    }

    /**
     * The writePrices function adds a prices record to the prices' storage.
     *
     * @param prices The prices record to be written in the storage.
     * @return true if the prices record is added successfully. false if writing failed.
     */
    public static boolean writePrices (Prices prices) {
        File file = new File(PRICES_PATH);
        ArrayList<Prices> allPrices = readPrices();
        if (allPrices.size() < 1) {
            allPrices.add(prices);
            if (file.exists()) file.delete();
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PRICES_PATH));
            out.writeObject(allPrices);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("Prices Added!");
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Prices are not saved to the database.");
            return false;
        }
    }

    /**
     * The deletePrices function deletes the prices record.
     *
     * @return true if the prices record is deleted successfully. false if deletion failed.
     */
    public static boolean deletePrices() {
        ArrayList<Prices> allPrices = readPrices();
        if (allPrices.size() > 0) {
            allPrices.remove(0);
            File file = new File(PRICES_PATH);
            if (file.exists()) file.delete();
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(PRICES_PATH));
            out.writeObject(allPrices);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("Prices Deleted!");
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Prices are not deleted from the database.");
            return false;
        }
    }
}
