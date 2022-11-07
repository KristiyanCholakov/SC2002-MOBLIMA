package data_managers;

import models.Holiday;
import models.movies.Movie;
import pages.PageElements;

import java.io.*;
import java.util.ArrayList;

public class HolidayManager {
    private static final String HOLIDAYS_PATH = "src/data/holidays.txt";

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

    public static boolean updateHoliday(Holiday holiday) {
        ArrayList<Holiday> allHolidays = readHolidays();
        Holiday holidayToUpdated = getHoliday(holiday.getName());
        if (holidayToUpdated == null) {
            PageElements.printConsoleMessage("No such holiday!");
            return false;
        } else {
            allHolidays.remove(holidayToUpdated);
            allHolidays.add(holiday);
            File file = new File(HOLIDAYS_PATH);
            if (file.exists()) file.delete();
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(HOLIDAYS_PATH));
            out.writeObject(allHolidays);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("Holiday Updated!");
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Holiday is not updated to the database.");
            return false;
        }
    }

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
