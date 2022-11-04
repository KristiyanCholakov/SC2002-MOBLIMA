package pages;

import constants.Regexes;
import data_managers.ActorManager;
import data_managers.CineplexManager;
import data_managers.DirectorManager;
import data_managers.MovieManager;
import models.cinemas.*;
import models.movies.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        HashMap<Character, List<Seat>> seatOccupancy = new HashMap<>();
        List<Seat> seats = new ArrayList<>();
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
            Cinema cinema = new Cinema(i+1,cType);
            System.out.println("Enter a date (like m/d/yyyy) to put in Show Times: ");
            LocalDate newDate = dateInput(sc.nextLine());
            System.out.println("Number of projections: ");
            int numOfProj = sc.nextInt();
            System.out.println("Enter showtimes: ");
            for (i = 0; i < numOfProj; i++) {
                // add showtimes of each movie inside arraylist
                LocalTime startT;
                try {
                    System.out.println("Enter Start Time: ");
                    String startTime = sc.nextLine();
                    startT = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HHmm"));
                } catch (DateTimeParseException exception) {
                    System.out.println("Invalid format. Please use 'HHmm'.");
                    return;
                }
                LocalTime endT;
                try {
                    System.out.println("Enter End Time: ");
                    String endTime = sc.nextLine();
                    endT = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HHmm"));
                } catch (DateTimeParseException exception) {
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
                    if (numTerm < 0) {
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
                System.out.print("Synopsis: ");
                String synopsis = sc.nextLine();
                System.out.println("Director: ");
                System.out.print("***First Name: ");
                String directorFName = sc.nextLine();
                System.out.print("***Last Name: ");
                String directorLName = sc.nextLine();
                Director director = DirectorManager.getDirector(directorFName, directorLName);
                if (director == null) {
                    director = new Director(directorFName, directorLName, 1);
                    DirectorManager.writeDirector(director);
                } else {
                    director.setNumberOfMovies(director.getNumberOfMovies() + 1);
                    DirectorManager.updateDirector(director);
                }
                System.out.print("Number of Main Actors: ");
                int n = sc.nextInt();
                sc.nextLine();
                ArrayList<Actor> actors = new ArrayList<Actor>();
                for (int i = 0; i < n; i++) {
                    System.out.println("Actor: ");
                    System.out.print("***First Name: ");
                    String actorFName = sc.nextLine();
                    System.out.print("***Last Name: ");
                    String actorLName = sc.nextLine();
                    Actor actor = ActorManager.getActor(actorFName, actorLName);
                    if (actor == null) {
                        System.out.print("***Number of Oscars: ");
                        int numberOfOscars = sc.nextInt();
                        sc.nextLine();
                        actor = new Actor(actorFName, actorLName, 1, numberOfOscars);
                        ActorManager.writeActor(actor);
                    } else {
                        actor.setNumberOfMovies(actor.getNumberOfMovies() + 1);
                        ActorManager.updateActor(actor);
                    }
                    actors.add(actor);
                }
                double rating = 0.0;
                ArrayList<Review> reviews = new ArrayList<Review>();
                Movie movie = new Movie(movieName, genre, duration, status, type, restriction, synopsis, director, actors, rating, reviews);
                MovieManager.writeMovie(movie);


                System.out.println("Seats until which alphabet(a-z): ");
                char row = sc.next().charAt(0);
                for (char alphabet = 'a'; alphabet <= row; alphabet++) {
                    System.out.println("Each row how many seats: ");
                    int numPerRow = sc.nextInt();
                    for (int i = 0; i < numPerRow; i++) {
                        Seat seat = new Seat(alphabet, i, false);
                        seats.add(seat);
                    }
                    seatOccupancy.put(alphabet, seats);
                    seats.clear();
                }
                ShowTime showtime = new ShowTime(newDate, cinema, startT, endT,movie,seatOccupancy);
                showtimes.add(showtime);
            }

            schedules.put(newDate, showtimes);
            showtimes.clear();

        }
        Cineplex cineplex = new Cineplex(CineplexName,CineplexAddress,schedules,cinemas);
        CineplexManager.writeCineplex(cineplex);

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
