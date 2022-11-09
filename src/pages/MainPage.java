package pages;

import models.accounts.User;
import pages.admin.AdminPages;
import pages.user.UserPages;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainPage {
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        MainPage.currentUser = currentUser;
    }

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
