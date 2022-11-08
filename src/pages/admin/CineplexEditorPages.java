package pages.admin;

import constants.Regexes;
import data_managers.ActorManager;
import data_managers.CineplexManager;
import data_managers.DirectorManager;
import data_managers.MovieManager;
import models.cinemas.*;
import models.movies.*;
import pages.PageElements;

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
                    "       4 - Back to Editor Portal");
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

    public static ArrayList<Cinema> cinemasInput() {
        Scanner sc = new Scanner(System.in);
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
                    return null;
            }
            System.out.println("Cinema Type (S/M/B): ");
            String seatConfStr1 = sc.nextLine();
            System.out.println("With Deluxe and Couple seats (true/false): ");
            boolean seatConfStr2 = sc.nextBoolean();
            CinemaEnums.SeatConfiguration seatConfig;
            if (seatConfStr2) {
                switch (seatConfStr1) {
                    case "S":
                        seatConfig = CinemaEnums.SeatConfiguration.SMALL_CINEMA_C_D;
                        break;
                    case "M":
                        seatConfig = CinemaEnums.SeatConfiguration.MEDIUM_CINEMA_C_D;
                        break;
                    case "B":
                        seatConfig = CinemaEnums.SeatConfiguration.BIG_CINEMA_C_D;
                        break;
                    default:
                        PageElements.printConsoleMessage("Error: The status is not in wanted format.");
                        return null;
                }
            } else {
                switch (seatConfStr1) {
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
                        return null;
                }
            }
            Cinema cinema = new Cinema(i + 1, cType, seatConfig);
            cinemas.add(cinema);
        }
        return cinemas;
    }

    public static void addCineplex() {
        PageElements.printHeader();
        Scanner sc = new Scanner(System.in);
        System.out.println("Name of new cineplex: ");
        String name = sc.nextLine();
        System.out.println("Address of cineplex: ");
        String address = sc.nextLine();
        if (CineplexManager.getCineplex(name) != null) {
            PageElements.printConsoleMessage("Cineplex already exists!");
            return;
        }
        ArrayList<Cinema> cinemas = cinemasInput();
        Cineplex cineplex = new Cineplex(name, address, cinemas);
        CineplexManager.writeCineplex(cineplex);
    }


    public static void editCineplex() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();

        System.out.println("Enter the name of the cineplex to be updated!");
        String name = scanner.nextLine();
        Cineplex cineplex = CineplexManager.getCineplex(name);
        if (cineplex == null) {
            PageElements.printConsoleMessage("The Cineplex doesn't exist!");
            return;
        }
        System.out.println("Select what you want to edit:\n" +
                "(Type the number of the choice)\n" +
                "       1 - Address \n" +
                "       2 - Cinemas\n" +
                "       3 - Back to Cineplex Editor");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.println("Enter the new address: ");
                String address = scanner.nextLine();
                cineplex.setAddress(address);
                break;
            case 2:
                ArrayList<Cinema> cinemas = cinemasInput();
                HashMap<LocalDate, ArrayList<ShowTime>> schedules = new HashMap<LocalDate, ArrayList<ShowTime>>();
                cineplex.setCinemas(cinemas);
                cineplex.setSchedules(schedules);
                break;
            case 3:
                return;
            default:
                PageElements.printConsoleMessage("Error: The type is not in wanted format.");
                return;
        }
        CineplexManager.updateCineplex(cineplex);
    }

    public static void deleteCineplex() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();

        System.out.println("Enter the name of the cineplex to be deleted!");
        String name = scanner.nextLine();
        Cineplex cineplex = CineplexManager.getCineplex(name);
        if (cineplex == null) {
            PageElements.printConsoleMessage("The Cineplex doesn't exist!");
            return;
        }
        CineplexManager.deleteCineplex(cineplex);
    }
}
