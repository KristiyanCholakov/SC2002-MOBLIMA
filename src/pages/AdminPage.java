package pages;

import data_managers.AdminManager;
import models.Admin;

import java.util.Scanner;

public class AdminPage {

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
        PageElements.printHeader();
        boolean running = true;
        while (running) {
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
        PageElements.printHeader();
        boolean running = true;
        while (running) {
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Show Movies\n" +
                    "       2 - Show Cinemas\n" +
                    "       3 - Show Showtime\n" +
                    "       4 - Show Reviews\n" +
                    "       5 - Back to Admin Portal");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showMoviesPage();
                    break;
                case 2:
                    showCinemasPage();
                    break;
                case 3:
                    showShowtimePage();
                    break;
                case 4:
                    showReviewsPage();
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
        PageElements.printHeader();
        boolean running = true;
        while (running) {
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Movies Editor\n" +
                    "       2 - Actors Editor\n" +
                    "       3 - Directors Editor\n" +
                    "       4 - Companies Editor\n" +
                    "       5 - Cinemas Editor\n" +
                    "       6 - Reviews Editor\n" +
                    "       7 - Back to Admin Portal");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    movieEditorPage();
                    break;
                case 2:
                    cinemaEditorPage();
                    break;
                case 3:
                    showtimeEditorPage();
                    break;
                case 4:
                    reviewEditorPage();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }

    public static void movieEditorPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        boolean running = true;
        while (running) {
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Add\n" +
                    "       2 - Edit\n" +
                    "       3 - Delete\n" +
                    "       4 - Back to Editor Portal");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addMoviePage();
                    break;
                case 2:
                    editMoviePage();
                    break;
                case 3:
                    deleteMoviePage();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }


    public static void cinemaEditorPage() {

    }
    public static void showtimeEditorPage() {

    }
    public static void reviewEditorPage() {

    }

    public static void showMoviesPage() {

    }

    public static void showCinemasPage() {

    }
    public static void showShowtimePage() {

    }
    public static void showReviewsPage() {

    }
}
