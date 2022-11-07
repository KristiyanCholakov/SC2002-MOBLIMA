package data_managers;

import models.Holiday;
import models.Prices;
import pages.PageElements;

import java.io.*;
import java.util.ArrayList;

public class PricesManager {
    private static final String PRICES_PATH = "src/data/prices.txt";

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
