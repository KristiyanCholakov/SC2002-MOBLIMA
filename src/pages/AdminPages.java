package pages;

import data_managers.AdminManager;
import models.accounts.Admin;

import java.util.Scanner;

public class AdminPages {

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

    public static void adminPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Editor Page\n" +
                    "       2 - Browsing Page\n" +
                    "       3 - Back to Start Page");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    adminEditPage();
                    break;
                case 2:
                    adminShowPage();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }

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
                    "       5 - Back to Admin Portal");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
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
//                    showReviewsPage();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }

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
                    "       5 - Back to Admin Portal");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
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
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }
}