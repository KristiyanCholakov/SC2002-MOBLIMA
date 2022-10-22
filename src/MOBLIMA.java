import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Scanner;

public class MOBLIMA {
    private static final String email_regex = "^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$";
    private static final String password_regex = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$";
    private static final String username_regex = "^(?<username>[[a-z]|[A-Z]|\\d|_]+)$";

    private static Scanner scanner = new Scanner(System.in);

    public static void printHeader() {

        System.out.println("\n------------------------------------------------------------");
        System.out.println("                     WELCOME TO MOBLIMA                     ");
        System.out.println("------------------------------------------------------------\n");
    }

    public static void printLine() {
        System.out.println("\n------------------------------------------------------------\n");
    }

    public static void printEmptySpace() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
    }

    public static String selectLoginOption () {
        System.out.println("Select how you want to enter the system:\n" +
                "(Type the number of the choice)\n" +
                "       1 - Log Into an Existing Account\n" +
                "       2 - Register New Account\n" +
                "       3 - Admin Portal");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                return "login";
            case 2:
                return "register";
            case 3:
                return "admin";
            default:
                return "loginOptions";
        }
    }

    public static User login() {
        System.out.println("LOGIN");
        System.out.print("Email/Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        User loggedUser = DataManager.checkCredentials(username, password);
        if (loggedUser != null) {
            printConsoleMessage("Successful Login!");
            return loggedUser;
        }
        return null;
    }

    public static User register() {
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
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("Password: ");
        String password1 = scanner.next();
        System.out.print("Confirm Password: ");
        String password2 = scanner.next();
        System.out.print("First Name: ");
        String f_name = scanner.next();
        System.out.print("Last Name: ");
        String l_name = scanner.next();
        if (password2.equals(password1)) {
            if (email.matches(email_regex)) {
                if (password1.matches(password_regex)) {
                    if (username.matches(username_regex)) {
                        User user = new User(f_name, l_name, username, email, password1);
                        int exists = DataManager.ifUserExists(user);
                        if (exists == 0) {
                            if (DataManager.addUser(user)) return user;
                            else return null;
                        } else if (exists == 1) {
                            printConsoleMessage("Error: The user already exists.");
                        }
                    } else printConsoleMessage("Error: The username is not in wanted format.");
                } else printConsoleMessage("Error: The password is not in wanted format.");
            } else printConsoleMessage("Error: The email is invalid.");
        } else printConsoleMessage("Error: Passwords don't match.");
        return null;
    }

    public static void printConsoleMessage(String message) {
        MOBLIMA.printLine();
        System.out.println("Console Message:");
        System.out.println(message);
        MOBLIMA.printLine();
    }
}
