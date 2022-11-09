package pages.admin;

import data_managers.HolidayManager;
import models.Holiday;
import pages.PageElements;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HolidayEditorPages {
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

    public static void addHolidayPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        System.out.println("Enter the name of the holiday:");
        String name = scanner.nextLine();
        if (HolidayManager.getHoliday(name) != null) {
            System.out.println("The holiday already exists!");
            return;
        }
        System.out.println("Enter Start Data (yyyy-mm-dd):");
        LocalDate startData = LocalDate.parse(scanner.nextLine());
        System.out.println("Enter Start Data (yyyy-mm-dd):");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Additional Price:");
        double addPrice = scanner.nextDouble();
        Holiday holiday = new Holiday(name, startData, endDate, addPrice);
        HolidayManager.writeHoliday(holiday);
    }

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
