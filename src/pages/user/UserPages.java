package pages.user;

import constants.Regexes;
import data_managers.UserManager;
import models.accounts.User;
import pages.MainPage;
import pages.PageElements;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  The UserPages class holds the functionality connected with the user pages.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class UserPages {

    /**
     * The userPage method gives the opportunity to choose what the user wants to do.
     * If the choice is invalid asks for another. If it is valid redirects to the corresponding page.
     */
    public static void userPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Make a Booking\n" +
                    "       2 - Explore\n" +
                    "       3 - My Account\n" +
                    "       4 - Review a Movie\n" +
                    "       5 - Back to Main Page" );
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
                    BookingPages.bookingPage();
                    break;
                case 2:
                    ExplorePages.explorePages();
                    break;
                case 3:
                    UserAccountPages.myAccountPage();
                    break;
                case 4:
                    ReviewPages.reviewPage();
                    break;
                case 5:
                    running = false;
                    MainPage.setCurrentUser(null);
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }

    /**
     * The userLoginPage method represents the login for the users.
     * Checks if the user exists based on the entered password and username/email.
     * If exists then redirects to userPage, go back to mainPage if not.
     */
    public static void userLoginPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();

        System.out.println("LOGIN\n");
        System.out.print("Email/Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        User loggedUser = UserManager.checkCredentials(username, password);
        if (loggedUser != null) {
            PageElements.printConsoleMessage("Successful Login!");
            MainPage.setCurrentUser(loggedUser);
            userPage();
        }
    }

    /**
     * The userRegisterPage method allows new users to register their accounts.
     * If all fields of the registration are filled correctly. The new account is added to the storage.
     * Else, the user is redirected to the main page.
     */
    public static void userRegisterPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();

        System.out.println("REGISTRATION\n");
        System.out.println("*** Username must contain only letters, digits or '_'");
        System.out.println("*** Password Must:\n" +
                "       contain 1 number (0-9)\n" +
                "       contain 1 uppercase letters\n" +
                "       contain 1 lowercase letters\n" +
                "       contain 1 non-alpha numeric number\n" +
                "       be 8-16 characters long\n");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        if (!username.matches(Regexes.USERNAME_REGEX)) {
            PageElements.printConsoleMessage("Error: The username is not in wanted format.");
            return;
        }
        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (!email.matches(Regexes.EMAIL_REGEX)) {
            PageElements.printConsoleMessage("Error: The email is not in wanted format.");
            return;
        }
        System.out.print("Password: ");
        String password1 = scanner.nextLine();
        if (!password1.matches(Regexes.PASSWORD_REGEX)) {
            PageElements.printConsoleMessage("Error: The password is not in wanted format.");
            return;
        }
        System.out.print("Confirm Password: ");
        String password2 = scanner.nextLine();
        if (!password1.equals(password2)) {
            PageElements.printConsoleMessage("Error: The passwords do not match.");
            return;
        }
        System.out.print("Birthdate (yyyy-mm-dd): ");
        String birthday = scanner.nextLine();
        if (!birthday.matches(Regexes.BIRTHDAY_REGEX)) {
            PageElements.printConsoleMessage("Error: The birthday is not in wanted format.");
            return;
        }
        System.out.print("First Name: ");
        String f_name = scanner.nextLine();
        System.out.print("Last Name: ");
        String l_name = scanner.nextLine();

        User user = new User(f_name, l_name, username, email, password1, LocalDate.parse(birthday));
        if (!UserManager.ifUserExists(user)) {
            UserManager.writeUser(user);
        } else {
            PageElements.printConsoleMessage("Error: The user already exists.");
        }
    }
}
