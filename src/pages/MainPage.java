package pages;

import models.accounts.User;
import pages.admin.AdminPages;
import pages.user.UserPages;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  The MainPage class holds the functionality connected with the main page for all users.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class MainPage {

    /**
     * An attribute to store the current user logged in if needed afterwards.
     */
    private static User currentUser;

    /**
     * The getCurrentUser gets the current user.
     *
     * @return The current user.
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * The setCurrentUser changes the current user.
     * @param currentUser The new current user.
     */
    public static void setCurrentUser(User currentUser) {
        MainPage.currentUser = currentUser;
    }

    /**
     * The mainPage method gives the opportunity to choose what the user wants to do.
     * If the choice is invalid asks for another. If it is valid redirects to the corresponding page.
     */
    public static void mainPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            System.out.println("Select how you want to enter the system:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Log Into an Existing Account\n" +
                    "       2 - Register New Account\n" +
                    "       3 - Admin Portal\n" +
                    "       4 - Master Portal\n" +
                    "       5 - Exit the System");
            System.out.print("Choice: ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                PageElements.printConsoleMessage("You have to enter a number!");
                choice = -1;
            }
            scanner.nextLine();
            switch (choice) {
                case 1:
                    UserPages.userLoginPage();
                    break;
                case 2:
                    UserPages.userRegisterPage();
                    break;
                case 3:
                    AdminPages.adminLoginPage();
                    break;
                case 4:
                    MasterPage.masterLoginPage();
                    break;
                case 5:
                    PageElements.printConsoleMessage("MOBLIMA has been terminated.");
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice. Enter a valid choice.");
                    break;
            }
        }
    }
}
