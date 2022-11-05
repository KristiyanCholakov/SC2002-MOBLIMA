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

    public static void addCineplex() {
        PageElements.printHeader();
        Scanner sc = new Scanner(System.in);
        System.out.println("Name of new cineplex: ");
        String name = sc.nextLine();
        System.out.println("Address of cineplex: ");
        String address = sc.nextLine();
        if (CineplexManager.getCineplex(name) != null){
            PageElements.printConsoleMessage("Cineplex already exists!");
            return;
        }
        System.out.println("Number of Cinemas: ");
        int cinNum = sc.nextInt();
        sc.nextLine();
        ArrayList<Cinema> cinemas = new ArrayList<>();
        for (int i = 0; i < cinNum; i++) {
            System.out.println("Entering details for Cinema " + (i + 1));
            System.out.println("Cinema Type (Platinum/Normal): ");
            String cinType = sc.nextLine();
            //enter cinema type
            CinemaEnums.CinemaType cType;
            switch (cinType) {
                case "Platinum":
                    cType = CinemaEnums.CinemaType.PLATINUM;
                    break;
                case "Normal":
                    cType = CinemaEnums.CinemaType.NORMAL;
                    break;
                default:
                    PageElements.printConsoleMessage("Error: The status is not in wanted format.");
                    return;
            }
            System.out.println("Cinema Type (S/M/B): ");
            String seatConfStr = sc.nextLine();
            CinemaEnums.SeatConfiguration seatConfig;
            switch (seatConfStr) {
                case "S":
                    seatConfig = CinemaEnums.SeatConfiguration.SMALL_CINEMA;
                    break;
                case "M":
                    seatConfig = CinemaEnums.SeatConfiguration.MEDIUM_CINEMA;
                    break;
                case "B":
                    seatConfig = CinemaEnums.SeatConfiguration.BIG_CINEMA;
                    break;
                default:
                    PageElements.printConsoleMessage("Error: The status is not in wanted format.");
                    return;
            }
            Cinema cinema = new Cinema(i+1,cType, seatConfig);
            cinemas.add(cinema);
        }
        HashMap<LocalDate, ArrayList<ShowTime>> schedules = new HashMap<LocalDate, ArrayList<ShowTime>>();
        Cineplex cineplex = new Cineplex(name, address, schedules, cinemas);
        CineplexManager.writeCineplex(cineplex);
    }



    public static void editCineplex(){


    }
    public static void deleteCineplex(){

    }
}
