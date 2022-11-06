package pages;

import java.util.Scanner;

public class MainPage {

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
            int choice = scanner.nextInt();
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
