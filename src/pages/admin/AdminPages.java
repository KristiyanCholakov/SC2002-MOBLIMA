package pages.admin;

import data_managers.AdminManager;
import models.accounts.Admin;
import pages.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  The AdminPages class holds the functionality connected with the admin pages.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class AdminPages {

    /**
     * The adminLoginPage method represents the login for the admins.
     * Checks if the admin exists based on the entered password and username/email.
     * If exists then redirects to AdminPage, go back to mainPage if not.
     */
    public static void adminLoginPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        System.out.println("ADMIN LOGIN\n");
        System.out.print("Email/Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        Admin loggedUser = AdminManager.checkCredentials(username, password);
        if (loggedUser != null) {
            PageElements.printConsoleMessage("Successful Login!");
            adminPage();
        }
    }

    /**
     * The adminPage method gives the opportunity to choose what the admin wants to do.
     * If the choice is invalid asks for another. If it is valid redirects to the corresponding page.
     */
    public static void adminPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Editor Page\n" +
                    "       2 - Browsing Page\n" +
                    "       3 - Change Prices\n" +
                    "       4 - See Prices\n" +
                    "       5 - Back to Start Page");
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
                    adminEditPage();
                    break;
                case 2:
                    adminShowPage();
                    break;
                case 3:
                    PricesPages.changePricesPage();
                    break;
                case 4:
                    PricesPages.seePricesPage();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }

    /**
     * The adminShowPage method gives the opportunity to choose what the admin wants to be shown.
     * If the choice is invalid asks for another. If it is valid redirects to the corresponding page.
     */
    public static void adminShowPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Show Movies\n" +
                    "       2 - Show Cineplexes\n" +
                    "       3 - Show Schedules\n" +
                    "       4 - Show Reviews\n" +
                    "       5 - Show Holidays\n" +
                    "       6 - Back to Admin Portal");
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
                    BrowsingPages.showMoviesPage();
                    break;
                case 2:
                    BrowsingPages.showCineplexesPage();
                    break;
                case 3:
                    BrowsingPages.showSchedules();
                    break;
                case 4:
                    BrowsingPages.showReviewsPage();
                    break;
                case 5:
                    BrowsingPages.showHolidaysPage();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }

    /**
     * The adminEditPage method gives the opportunity to choose what the admin wants to be edited.
     * If the choice is invalid asks for another. If it is valid redirects to the corresponding page.
     */
    public static void adminEditPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Movies Editor\n" +
                    "       2 - Cineplex Editor\n" +
                    "       3 - Schedule Editor\n" +
                    "       4 - Reviews Editor\n" +
                    "       5 - Holidays Editor\n" +
                    "       6 - Back to Admin Portal");
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
                    MovieEditorPages.movieEditorPage();
                    break;
                case 2:
                    CineplexEditorPages.cinemaEditorPage();
                    break;
                case 3:
                    ScheduleEditorPages.showtimeEditorPage();
                    break;
                case 4:
                    ReviewEditorPages.reviewEditorPage();
                    break;
                case 5:
                    HolidayEditorPages.holidayEditorPage();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }
}