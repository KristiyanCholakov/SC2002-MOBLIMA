package pages;

import models.cinemas.Cinema;
import models.cinemas.Cineplex;
import models.cinemas.ShowTime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CineplexEditorPages {
    public static void cinemaEditorPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Add Cineplex\n" +
                    "       2 - Edit Cineplex\n" +
                    "       3 - Delete Cineplex\n" +
                    "       4 - Back to Editor Portal" );

            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addCineplexes();
                    break;
                case 2:
                    editCinema();
                    break;
                case 3:
                    deleteCinema();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }

    }
    public static void addCineplexes(){
        ArrayList<Cinema> cinemas = null;
        HashMap<LocalDate, ArrayList<ShowTime>> schedules = new HashMap<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Name of new cineplex: ");
        String CineplexName = sc.nextLine();
        // 3 cinemas under each cineplex
        for (int i = 0; i < 3; i++){
            System.out.println("Name of " + (i+1) +"st cinema under it: ");
            String cinemaName = sc.nextLine();
            String cinemaAddress = sc.nextLine();
            while (true){
                //add showtimes
                System.out.println("Enter a date (like m/d/yyyy): ");
                LocalDate newDate = dateInput(sc.nextLine());
            }

        }
        //Cineplex cineplex = new Cineplex(cineplexName);

    }
    public static void addCinemas(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Name of cinema: ");
        String cinemaName = sc.nextLine();
        System.out.println("Address of cinema: ");


    }
    public static LocalDate dateInput(String userInput) {

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate date = LocalDate.parse(userInput, dateFormat);


        System.out.println(date);
        return date ;
    }

    public static void editCinema(){

    }
    public static void deleteCinema(){

    }
}
