import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Scanner;

public class MOBLIMA {
    private static String masterCode = "master111";
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
                "       3 - Admin Portal\n" +
                "       4 - Master Portal");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                return "login";
            case 2:
                return "register";
            case 3:
                return "adminLogin";
            case 4:
                return "masterLogin";
            default:
                printConsoleMessage("Invalid Choice");
                return "loginOptions";
        }
    }

    public static boolean masterLogin() {
        System.out.println("MASTER LOGIN\n");
        System.out.print("Enter the master's code: ");
        String code = scanner.next();
        if (code != masterCode) MOBLIMA.printConsoleMessage("Wrong code!");
        return (code.equals(masterCode));
    }

    public static User login() {
        System.out.println("LOGIN\n");
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

    public static String masterPortal() {
        System.out.println("Select the action you want:\n" +
                "(Type the number of the choice)\n" +
                "       1 - Create an Admin Account\n" +
                "       2 - Show All Admins\n" +
                "       3 - Back to Start Page");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                return "createAdmin";
            case 2:
                return "showAdmins";
            case 3:
                return "loginOptions";
            default:
                printConsoleMessage("Invalid Choice!");
                return "masterPortal";
        }
    }

    public static String adminPortal() {
        System.out.println("Select the action you want:\n" +
                "(Type the number of the choice)\n" +
                "       1 - Movies Editor\n" +
                "       2 - Cinemas Editor\n" +
                "       3 - Showtime Editor\n" +
                "       4 - Reviews Editor\n" +
                "       5 - Back to Start Page");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                return "movieEditor";
            case 2:
                return "cinemasEditor";
            case 3:
                return "showtimeEditor";
            case 4:
                return "reviewsEditor";
            case 5:
                return "loginOptions";
            default:
                printConsoleMessage("Invalid Choice!");
                return "masterPortal";
        }
    }

    public static void showAdmins() {
        System.out.println("SHOW ADMINS\n");
        DataManager.showAdmins();
    }

    public static boolean createAdmin() {
        System.out.println("CREATE ADMIN\n");
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
                        Admin admin = new Admin(f_name, l_name, username, email, password1);
                        int exists = DataManager.ifAdminExists(admin);
                        if (exists == 0) {
                            return DataManager.addAdmin(admin);
                        } else if (exists == 1) {
                            printConsoleMessage("Error: The admin already exists.");
                        }
                    } else printConsoleMessage("Error: The username is not in wanted format.");
                } else printConsoleMessage("Error: The password is not in wanted format.");
            } else printConsoleMessage("Error: The email is invalid.");
        } else printConsoleMessage("Error: Passwords don't match.");
        return false;
    }

    public static Admin adminLogin() {
        System.out.println("ADMIN LOGIN\n");
        System.out.print("Email/Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        Admin loggedUser = DataManager.checkAdminCredentials(username, password);
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
