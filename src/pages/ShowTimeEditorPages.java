package pages;

import models.cinemas.ShowTime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShowTimeEditorPages {
    public static void showtimeEditorPage() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<ShowTime> showTime = new ArrayList<>();
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
                PageElements.printConsoleMessage("Invalid Choice. Enter a valid choice.");
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
                PageElements.printConsoleMessage("Invalid Choice. Enter a valid choice.");
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
                PageElements.printConsoleMessage("Invalid Choice. Enter a valid choice.");
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
                    PageElements.printConsoleMessage("Invalid Choice. Enter a valid choice.");
            }
        }
    }



    private static void addShowtimePage() {
        LocalDate date = handleDate();

        System.out.println("Enter screen number");
        System.out.println("Enter start time");
        System.out.println("Enter end time");
        System.out.println("Enter movie name");

    }
    private static String getUserInput(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }
    //dd-mm-yyyy intended format
    private static LocalDate handleDate() {
        try{
            System.out.println("Enter date");
            String date = getUserInput();
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch(DateTimeParseException exception) {
            System.out.println("Invalid format. Please use 'DD-MM-YYYY'.");
            return handleDate();
        }
    }

    private static void editShowtimePage() {
    }
    private static void deleteShowtimePage() {
    }
    public static void main(String[] args){
        System.out.println(handleDate());
    }
}
