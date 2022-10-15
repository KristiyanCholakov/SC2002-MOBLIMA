package moblima;

import java.util.Scanner;

public class Login {
    public int login() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Username: ");
        String username = scan.next();
        System.out.println("Password: ");
        String password = scan.next();
        if (username.equals("staff123") && password.equals("12345678")) return 1;
        System.out.println("Username or Password is incorrect.");
        return 0;
    }
}
