package data_managers;

import models.Holiday;
import pages.PageElements;

import java.io.*;
import java.util.ArrayList;

/**
 * The HolidayManager class takes care of interacting with the file where the holidays are stored.
 * It performs writing, reading and searching in the holidays' storage.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class HolidayManager {

    /**
     * The HOLIDAYS_PATH constant points to the file where holidays are stored.
     */
    private static final String HOLIDAYS_PATH = "src/data/holidays.txt";

    /**
     * The readHolidays function reads all holiday records from the holidays' storage.
     *
     * @return An array list of the holiday records from the storage. Empty array list if not any.
     */
    public static ArrayList<Holiday> readHolidays() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HOLIDAYS_PATH));
            ArrayList<Holiday> holidays = (ArrayList<Holiday>) ois.readObject();
            ois.close();
            return holidays;
        } catch (FileNotFoundException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Holiday can't be read!");
        } catch (EOFException e) {
            PageElements.printConsoleMessage("File was empty. This is the first item in it.");
        } catch (IOException | ClassNotFoundException e) {
            PageElements.printConsoleMessage("Error!");
        }
        return new ArrayList<Holiday>();
    }

    /**
     * The writeHoliday function adds a holiday record to the holidays' storage.
     *
     * @param holiday The holiday record to be written in the storage.
     * @return true if the holiday record is added successfully. false if writing failed.
     */
    public static boolean writeHoliday(Holiday holiday) {
        File file = new File(HOLIDAYS_PATH);
        ArrayList<Holiday> allHolidays = readHolidays();
        allHolidays.add(holiday);
        if (file.exists()) file.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(HOLIDAYS_PATH));
            out.writeObject(allHolidays);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("Holiday Added!");
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Holiday is not saved to the database.");
            return false;
        }
    }

    /**
     * The getHoliday function searches for a holiday with the same name in the storage and provides their record.
     *
     * @param name The name of the holiday that we search
     * @return The record of the holiday with the same name. null if a holiday with this name does not exist.
     */
    public static Holiday getHoliday(String name) {
        ArrayList<Holiday> allHoliday = readHolidays();
        for (int i = 0; i < allHoliday.size(); i++) {
            Holiday currentHoliday = allHoliday.get(i);
            if (currentHoliday.getName().equals(name)) {
                return currentHoliday;
            }
        }
        return null;
    }

    /**
     * The deleteHoliday function searches if the provided holiday record exists and if so deletes their entry.
     *
     * @param holiday The holiday record to be deleted in the storage.
     * @return true if the holiday record is deleted successfully. false if deletion failed.
     */
    public static boolean deleteHoliday(Holiday holiday) {
        ArrayList<Holiday> allHolidays = readHolidays();
        Holiday holidayToUpdated = getHoliday(holiday.getName());
        if (holidayToUpdated == null) {
            PageElements.printConsoleMessage("No such holiday!");
            return false;
        } else {
            allHolidays.remove(holidayToUpdated);
            File file = new File(HOLIDAYS_PATH);
            if (file.exists()) file.delete();
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(HOLIDAYS_PATH));
            out.writeObject(allHolidays);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("Holiday Deleted!");
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Holiday is not deleted from the database.");
            return false;
        }
    }
}
