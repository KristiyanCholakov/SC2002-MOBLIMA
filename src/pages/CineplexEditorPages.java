package pages;

import constants.Regexes;
import data_managers.CineplexManager;
import data_managers.MovieManager;
import models.cinemas.Cinema;
import models.cinemas.CinemaEnums;
import models.cinemas.ShowTime;
import models.movies.MovieEnums;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
                try {
                    System.out.println("Enter Start Time: ");
                    String startTime = sc.nextLine();
                    LocalTime startT = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HHmm"));
                } catch(DateTimeParseException exception) {
                    System.out.println("Invalid format. Please use 'HHmm'.");
                    return;
                }
                try {
                    System.out.println("Enter End Time: ");
                    String endTime = sc.nextLine();
                    LocalTime endT = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HHmm"));
                } catch(DateTimeParseException exception) {
                    System.out.println("Invalid format. Please use 'HHmm'.");
                    return;
                }
                System.out.println("Enter Movie Name: ");
                String movieName = sc.nextLine();
                int numTerm = 3;
                while ((MovieManager.getMovie(movieName) != null) == true) {
                    PageElements.printConsoleMessage("Movie already exists! Try again! Enter Movie Name: ");
                    PageElements.printConsoleMessage("No. of Tries left before termination: " + numTerm);
                    movieName = sc.nextLine();
                    numTerm -= 1;
                    if (numTerm < 0){
                        return;
                    }
                }
                System.out.println("Genre: ");
                String genre = sc.nextLine();
                System.out.print("Duration (hh:mm:ss): ");
                String durationStr = sc.nextLine();
                if (!durationStr.matches(Regexes.duration_regex)) {
                    PageElements.printConsoleMessage("Error: The duration is not in wanted format.");
                    return;
                }
                LocalTime duration = LocalTime.parse(durationStr);
                System.out.print("Status (CS, P, NS): ");
                String statusStr = sc.nextLine();
                MovieEnums.MovieStatus status;
                switch (statusStr) {
                    case "CS":
                        status = MovieEnums.MovieStatus.COMING_SOON;
                        break;
                    case "P":
                        status = MovieEnums.MovieStatus.PREVIEW;
                        break;
                    case "NS":
                        status = MovieEnums.MovieStatus.NOW_SHOWING;
                        break;
                    default:
                        PageElements.printConsoleMessage("Error: The status is not in wanted format.");
                        return;

                }
                System.out.print("Type (2D, 3D, 4DX, IMAX): ");
                String typeStr = sc.nextLine();
                MovieEnums.MovieType type;
                switch (typeStr) {
                    case "2D":
                        type = MovieEnums.MovieType.TWO_D;
                        break;
                    case "3D":
                        type = MovieEnums.MovieType.THREE_D;
                        break;
                    case "4DX":
                        type = MovieEnums.MovieType.FOUR_DX;
                        break;
                    case "IMAX":
                        type = MovieEnums.MovieType.IMAX;
                        break;
                    default:
                        PageElements.printConsoleMessage("Error: The type is not in wanted format.");
                        return;
                }
                System.out.print("Restriction (G, PG, PG13, NC16, M18, R21): ");
                String restrictionStr = sc.nextLine();
                MovieEnums.MovieRestriction restriction;
                switch (restrictionStr) {
                    case "G":
                        restriction = MovieEnums.MovieRestriction.G;
                        break;
                    case "PG":
                        restriction = MovieEnums.MovieRestriction.PG;
                        break;
                    case "PG13":
                        restriction = MovieEnums.MovieRestriction.PG13;
                        break;
                    case "NC16":
                        restriction = MovieEnums.MovieRestriction.NC16;
                        break;
                    case "M18":
                        restriction = MovieEnums.MovieRestriction.M18;
                        break;
                    case "R21":
                        restriction = MovieEnums.MovieRestriction.R21;
                        break;
                    default:
                        PageElements.printConsoleMessage("Error: The restriction is not in wanted format.");
                        return;
                }


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
