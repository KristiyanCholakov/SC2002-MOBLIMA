package pages.admin;

import data_managers.HolidayManager;
import models.Holiday;
import pages.PageElements;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  The HolidayEditorPages class holds the functionality connected with the holiday editing pages for the admin.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class HolidayEditorPages {

    /**
     * The holidayEditorPage method gives the opportunity to choose what the admin wants to do.
     * If the choice is invalid asks for another. If it is valid redirects to the corresponding page.
     */
    public static void holidayEditorPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Add Holiday\n" +
                    "       2 - Delete Holiday\n" +
                    "       3 - Back to Editor Portal");
            System.out.print("Choice: ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                choice = -1;
                PageElements.printConsoleMessage("You have to enter a number!");
            }
            scanner.nextLine();
            switch (choice) {
                case 1:
                    try {
                        addHolidayPage();
                    } catch (DateTimeParseException e) {
                        PageElements.printConsoleMessage("You haven't entered the date with the wanted format!");
                    } catch (InputMismatchException e ) {
                        PageElements.printConsoleMessage("You have to enter a valid input!");
                    }
                    break;
                case 2:
                    deleteHolidayPage();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }

    /**
     * The addHolidayPage allows the admin to add a new holiday record to the storage.
     * If the holiday already exists or the fields are filled invalidly, returns to the previous page.
     * Else the holiday record is added.
     */
    public static void addHolidayPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        System.out.println("Enter the name of the holiday:");
        String name = scanner.nextLine();
        if (HolidayManager.getHoliday(name) != null) {
            System.out.println("The holiday already exists!");
            return;
        }
        System.out.println("Enter Start Date (yyyy-mm-dd):");
        LocalDate startData = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter End Date (yyyy-mm-dd):");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Additional Price:");
        double addPrice = scanner.nextDouble();
        Holiday holiday = new Holiday(name, startData, endDate, addPrice);
        HolidayManager.writeHoliday(holiday);
    }

    /**
     * The deleteHolidayPage method is used for deleting a holiday record in the storage based on the holiday name.
     * If the holiday exists in the storage, its record is deleted. Else, returns to the previous page.
     */
    public static void deleteHolidayPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        System.out.println("Enter the name of the holiday:");
        Holiday holiday = HolidayManager.getHoliday(scanner.nextLine());
        if (holiday == null) {
            System.out.println("The holiday doesn't exist!");
            return;
        }
        HolidayManager.deleteHoliday(holiday);
    }
}
