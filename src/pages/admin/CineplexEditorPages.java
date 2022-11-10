package pages.admin;

import data_managers.CineplexManager;
import models.cinemas.*;
import pages.PageElements;

import java.time.LocalDate;
import java.util.*;

/**
 *  The CineplexEditorPages class holds the functionality connected with the cineplex editing pages for the admin.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class CineplexEditorPages {

    /**
     * The cinemaEditorPage method gives the opportunity to choose what the admin wants to do.
     * If the choice is invalid asks for another. If it is valid redirects to the corresponding page.
     */
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
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                choice = -1;
                PageElements.printConsoleMessage("You have to enter a number!");
            }
            scanner.nextLine();
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

    /**
     * The cinemasInput method accepts the cinema values entered from the admin.
     * @return Array list of cinemas if all fields are filled validly. Empty array list if invalid inputs.
     */
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
            CinemaEnums.CinemaType cType;
            switch (cinType) {
                case "Platinum":
                    cType = CinemaEnums.CinemaType.PLATINUM;
                    break;
                case "Normal":
                    cType = CinemaEnums.CinemaType.NORMAL;
                    break;
                default:
                    PageElements.printConsoleMessage("Error: The type is not in wanted format.");
                    return null;
            }
            System.out.println("Cinema Type (S/M/B): ");
            String seatConfStr1 = sc.nextLine();
            boolean seatConfStr2;
            if (cType != CinemaEnums.CinemaType.PLATINUM) {
                System.out.println("With Deluxe and Couple seats (true/false): ");
                seatConfStr2 = sc.nextBoolean();
                sc.nextLine();
            } else {
                seatConfStr2 = false;
            }
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

    /**
     * The addCineplex method allows the admin to add a new cineplex to the storage.
     * Checks if the cineplex exists and if it does, goes back to the previous page (already created).
     * If all fields are filled correctly the cineplex will be added to the storage. If not, will go back to previous page.
     */
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
        ArrayList<Cinema> cinemas = null;
        try {
            cinemas = cinemasInput();
        } catch (InputMismatchException e) {
            PageElements.printConsoleMessage("You have to enter a valid input!");
        }
        if (cinemas == null) {
            return;
        }
        Cineplex cineplex = new Cineplex(name, address, cinemas);
        CineplexManager.writeCineplex(cineplex);
    }


    /**
     * The editCineplex method allows the admin to edit a cineplex based on its name and update it in the storage.
     * Checks if the cineplex exists and if it doesn't, goes back to the previous page (not existing cineplex).
     * If all fields are filled correctly the cineplex will be updated in the storage. If not, will go back to previous page.
     */
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
        int choice;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            choice = -1;
            PageElements.printConsoleMessage("You have to enter a number!");
        }
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.println("Enter the new address: ");
                String address = scanner.nextLine();
                cineplex.setAddress(address);
                break;
            case 2:
                ArrayList<Cinema> cinemas = null;
                try {
                    cinemas = cinemasInput();
                } catch (InputMismatchException e) {
                    PageElements.printConsoleMessage("You have to enter a valid input!");
                }
                if (cinemas == null) {
                    return;
                }
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

    /**
     * The deleteCineplex method allows the admin to delete a specific cineplex based on the cineplex name.
     * If the cineplex doesn't exist goes back to the previous page.
     * If it does deletes the record from the storage.
     */
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
