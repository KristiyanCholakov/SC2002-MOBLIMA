package pages;

import constants.Regexes;
import data_managers.UserManager;
import models.User;

import java.util.Scanner;

public class UserPage {

    public static void userPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        while (true){}
    }

    public static void userLoginPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();

        System.out.println("LOGIN\n");
        System.out.print("Email/Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        User loggedUser = UserManager.checkCredentials(username, password);
        if (loggedUser != null) {
           PageElements.printConsoleMessage("Successful Login!");
           userPage();
        }
    }

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
        String username = scanner.next();
        if (!username.matches(Regexes.username_regex)) {
            PageElements.printConsoleMessage("Error: The username is not in wanted format.");
            return;
        }
        System.out.print("Email: ");
        String email = scanner.next();
        if (!email.matches(Regexes.email_regex)) {
            PageElements.printConsoleMessage("Error: The email is not in wanted format.");
            return;
        }
        System.out.print("Password: ");
        String password1 = scanner.next();
        if (!password1.matches(Regexes.password_regex)) {
            PageElements.printConsoleMessage("Error: The password is not in wanted format.");
            return;
        }
        System.out.print("Confirm Password: ");
        String password2 = scanner.next();
        if (!password1.equals(password2)) {
            PageElements.printConsoleMessage("Error: The passwords do not match.");
            return;
        }
        System.out.print("First Name: ");
        String f_name = scanner.next();
        System.out.print("Last Name: ");
        String l_name = scanner.next();

        User user = new User(f_name, l_name, username, email, password1);
        int exists = UserManager.ifUserExists(user);
        if (exists == 0) {
            if (UserManager.addUser(user)) return;
        } else if (exists == 1) {
            PageElements.printConsoleMessage("Error: The user already exists.");
        }
    }
}
