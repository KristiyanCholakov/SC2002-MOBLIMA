package pages;

import java.util.Scanner;

public class ShowTimeEditorPages {
    public static void showtimeEditorPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        boolean running = true;
        while (running) {
            System.out.println("Select the cineplex you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Cineplex 1\n" +
                    "       2 - Cineplex 2\n" +
                    "       3 - Cineplex 3");
            System.out.print("Choice: ");
            int cineplex = scanner.nextInt();
            if (cineplex < 1 || cineplex > 3) {
                System.out.println("Invalid Choice!");
                continue;
            }

            System.out.println("Select the cinema location you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Cinema location 1\n" +
                    "       2 - Cinema location 2\n" +
                    "       3 - Cinema location 3"); // can change to dynamically name cinema locations
            System.out.print("Choice: ");
            int location = scanner.nextInt();
            if (location < 1 || location > 3) {
                System.out.println("Invalid Choice!");
                continue;
            }

            System.out.println("Select the screen you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Screen 1\n" +
                    "       2 - Screen 2\n" +
                    "       3 - Screen 3"); // can change to dynamically name screens
            System.out.print("Choice: ");
            int screen = scanner.nextInt();
            if (screen < 1 || screen > 3) {
                System.out.println("Invalid Choice!");
                continue;
            }

            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Add showtime\n" +
                    "       2 - Edit showtime\n" +
                    "       3 - Delete showtime\n" +
                    "       4 - Back to Editor Portal");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addShowtimePage();
                    break;
                case 2:
                    editShowtimePage();
                    break;
                case 3:
                    deleteShowtimePage();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }



    private static void addShowtimePage() {
    }

    private static void editShowtimePage() {
    }
    private static void deleteShowtimePage() {
    }
}
