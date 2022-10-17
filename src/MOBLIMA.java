import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Scanner;

public class MOBLIMA {
    private static final String email_regex = "^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$";
    private static final String password_regex = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$";
    private static final String username_regex = "^(?<username>[[a-z]|[A-Z]|\\d|_]+)$";

    private static Scanner scanner = new Scanner(System.in);

    public static void printHeader() {

        System.out.println("------------------------------------------------------------");
        System.out.println("                     WELCOME TO MOBLIMA                     ");
        System.out.println("------------------------------------------------------------\n");
    }

    public static void printLine() {
        System.out.println("\n------------------------------------------------------------\n");
    }

    public static boolean login() {
        System.out.println("LOGIN");
        System.out.print("Email/Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        int validCredentials = DataManager.checkCredentials(username, password);
        if (validCredentials == 1) {
            System.out.println("Successful Login!");
            return true;
        } else if (validCredentials == 0){
            System.out.println("Wrong Credentials!");
        }
        return false;
    }

    public static boolean register() {
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
                            return DataManager.addUser(user);
                        } else if (exists == 1) {
                            System.out.println("Error: The user already exists.");
                        }
                    } else System.out.println("Error: The username is not in wanted format.");
                } else System.out.println("Error: The password is not in wanted format.");
            } else System.out.println("Error: The email is invalid.");
        } else System.out.println("Error: Passwords don't match.");
        return false;
    }
}
