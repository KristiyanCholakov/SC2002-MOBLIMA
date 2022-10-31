package pages;

import java.util.Scanner;

public class MovieEditorPages {
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

    public static void addMoviePage() {

    }

    public static void editMoviePage() {

    }

    public static void deleteMoviePage() {

    }
}
