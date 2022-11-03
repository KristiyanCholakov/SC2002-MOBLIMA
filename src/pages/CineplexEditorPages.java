package pages;

import data_managers.CineplexManager;
import models.cinemas.Cinema;
import models.cinemas.CinemaEnums;
import models.cinemas.ShowTime;
import models.movies.MovieEnums;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static pages.ShowTimeEditorPages.addShowtimePage;

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
                    addCineplex();
                    break;
                case 2:
                    editCineplex();
                    break;
                case 3:
                    deleteCineplex();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }

    }

//    add cineplex
//            add cinemas
//            choose a date for the schedule
//            add schedule
//                add showtime
//                    enter movie
//                    enter cinema
//                    enter tim
//    "Enter cineplex:" +
//            "entering the cineplex -> name, location, ..."
//            "add cinemas (rooms)"
//            "enter the number of cinemas in the cineplex -> 5" +
//                        "enter cinema-> name, type (deluxe, normal,...)"
//                        "doint this 4 more times"
//            "enter a date for a schedule->1.1.2000"
//                        "enter the number of projections (showtimes) per this date->20"
//                                "add showtime->movie, cinema(roomm), start time, end time"
//                                "do this 19 more times...."

    public static void addCineplex(){
        ArrayList<Cinema> cinemas = new ArrayList<>();
        ArrayList<ShowTime> showtimes = new ArrayList<>();
        HashMap<LocalDate, ArrayList<ShowTime>> schedules = new HashMap<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Name of new cineplex: ");
        String CineplexName = sc.nextLine();
        System.out.println("Address of cineplex: ");
        String CineplexAddress = sc.nextLine();
        if (CineplexManager.getCineplex(CineplexName) != null){
            PageElements.printConsoleMessage("Cineplex already exists!");
            return;
        }
        // 3 cinemas under each cineplex
        System.out.println("Number of Cinemas: ");
        int cinNum = sc.nextInt();
        for (int i = 0; i < cinNum; i++){
            System.out.println("Cinema Type (Platinum/Normal): ");
            String cinType = sc.nextLine();
            //enter cinema type
            CinemaEnums.CinemaType cType;
            switch (cinType) {
                case "Platinum":
                    cType = CinemaEnums.CinemaType.Platinum;
                    break;
                case "Normal":
                    cType = CinemaEnums.CinemaType.Normal;
                    break;
                default:
                    PageElements.printConsoleMessage("Error: The status is not in wanted format.");
                    return;
            }
            System.out.println("Enter a date (like m/d/yyyy) to put in Show Times: ");
            LocalDate newDate = dateInput(sc.nextLine());
            System.out.println("Number of projections: ");
            int numOfProj = sc.nextInt();
            System.out.println("Enter showtimes: ");
            for (int i = 0; i < numOfProj; i++){
                // add showtimes of each movie inside arraylist
                showtimes.add();
            }
            schedules.put(newDate, showtimes);
            showtimes.clear();
        }
        for (int i = 0; i < cinNum; i++){
            Cinema

        }
        //Cineplex cineplex = new Cineplex(cineplexName);

    }
    public static LocalDate dateInput(String userInput) {

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate date = LocalDate.parse(userInput, dateFormat);


        System.out.println(date);
        return date ;
    }

    public static void editCineplex(){


    }
    public static void deleteCineplex(){

    }
}
