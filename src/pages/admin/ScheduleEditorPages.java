package pages.admin;

import data_managers.CineplexManager;
import data_managers.MovieManager;
import models.cinemas.Cinema;
import models.cinemas.Cineplex;
import models.cinemas.ShowTime;
import models.movies.Movie;
import pages.PageElements;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  The ScheduleEditorPages class holds the functionality connected with the schedule editing pages for the admin.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class ScheduleEditorPages {

    /**
     * The showtimeEditorPage method gives the opportunity to choose what the admin wants to do.
     * If the choice is invalid asks for another. If it is valid redirects to the corresponding page.
     */
    public static void showtimeEditorPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Add Schedule\n" +
                    "       2 - Edit Schedule\n" +
                    "       3 - Delete Schedule\n" +
                    "       4 - Back to Editor Portal" );
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
                    try {
                        addSchedulePage();
                    } catch (DateTimeParseException e) {
                        PageElements.printConsoleMessage("You have to enter the date/time in a valid format!");
                    } catch (InputMismatchException e ) {
                        PageElements.printConsoleMessage("You have to enter a valid input!");
                    }
                    break;
                case 2:
                    try {
                        editSchedulePage();
                    } catch (DateTimeParseException e) {
                        PageElements.printConsoleMessage("You have to enter the date/time in a valid format!");
                    } catch (InputMismatchException e ) {
                        PageElements.printConsoleMessage("You have to enter a valid input!");
                    }
                    break;
                case 3:
                    try {
                        deleteSchedulePage();
                    } catch (DateTimeParseException e) {
                        PageElements.printConsoleMessage("You have to enter the date/time in a valid format!");
                    } catch (InputMismatchException e ) {
                        PageElements.printConsoleMessage("You have to enter a valid input!");
                    }
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
     * The inputSchedule method the admin has to enter the schedule details.
     *
     * @param cineplex The cineplex the schedule is about.
     * @param date The date of the schedule.
     * @return Empty array list if invalid inputs. Array list with the new showtimes for the schedule.
     */
    public static ArrayList<ShowTime> inputSchedule(Cineplex cineplex, LocalDate date) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of showtimes for " + date.toString() + " ");
        int n = sc.nextInt();
        sc.nextLine();
        ArrayList<ShowTime> showTimes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter the details for showtime " + (i+1));
            System.out.println("Enter the cinema number where the movie will be projected: ");
            int cinemaN = sc.nextInt();
            sc.nextLine();
            if (cinemaN-1 > cineplex.getCinemas().size()) {
                PageElements.printConsoleMessage("No such cinema!");
                return null;
            }
            Cinema cinema;
            cinema = cineplex.getCinemas().get(cinemaN-1);
            System.out.println("Enter the movie to be shown:");
            String movieTitle = sc.nextLine();
            Movie movie = MovieManager.getMovie(movieTitle);
            if (movie == null) {
                PageElements.printConsoleMessage("No such movie. Add the movie first!");
                return null;
            }
            System.out.println("Enter the starting time (hh:mm):");
            LocalTime startTime= LocalTime.parse(sc.nextLine() + ":00");
            LocalTime duration =  movie.getDuration();
            LocalTime endTime = startTime.plusHours(duration.getHour()).plusMinutes(duration.getMinute());
            ShowTime showTime = new ShowTime(date, cinema, startTime, endTime, movie);
            showTimes.add(showTime);
        }
        return showTimes;
    }

    /**
     * The addSchedulePage method is used for adding a particular date schedule to a cineplex.
     * If the cineplex exists and the schedule date is not taken, the new schedule will be added to the cineplex.
     * Else, admin is returned to the previous page.
     */
    public static void addSchedulePage() {
        PageElements.printHeader();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the cineplex you want to add the schedule to: ");
        String cineplexName = sc.nextLine();
        Cineplex cineplex = CineplexManager.getCineplex(cineplexName);
        if (cineplex == null) {
            PageElements.printConsoleMessage("No such cineplex, add the cineplex first!");
            return;
        }
        System.out.println("Enter the date you want to add the schedule to (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(sc.nextLine());
        if (cineplex.getSchedules().containsKey(date)) {
            PageElements.printConsoleMessage("The schedule already exists! Choose edit if you want to change it.");
            return;
        }
        ArrayList<ShowTime> schedule = inputSchedule(cineplex, date);
        if (schedule == null) return;
        cineplex.getSchedules().put(date, schedule);
        CineplexManager.updateCineplex(cineplex);
    }

    /**
     * The editSchedulePage method is used for editing a particular date schedule to a cineplex.
     * If the cineplex exists and the schedule date exists too, the new schedule will be edited and cineplex update.
     * Else, admin is returned to the previous page.
     */
    public static void editSchedulePage() {
        PageElements.printHeader();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the cineplex whose schedule you want to change: ");
        String cineplexName = sc.nextLine();
        Cineplex cineplex = CineplexManager.getCineplex(cineplexName);
        if (cineplex == null) {
            PageElements.printConsoleMessage("No such cineplex, add the cineplex first!");
            return;
        }
        System.out.println("Enter the date whose schedule you want to change (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(sc.nextLine());
        if (!cineplex.getSchedules().containsKey(date)) {
            PageElements.printConsoleMessage("The schedule doesn't exists! Choose add to add it first.");
            return;
        }
        ArrayList<ShowTime> schedule = inputSchedule(cineplex, date);
        if (schedule == null) return;
        cineplex.getSchedules().put(date, schedule);
        CineplexManager.updateCineplex(cineplex);
    }

    /**
     * The deleteSchedulePage method is used for deleting a particular date schedule to a cineplex.
     * If the cineplex exists and the schedule date exists too, the new schedule will be deleted and cineplex update.
     * Else, admin is returned to the previous page.
     */
    public static void deleteSchedulePage() {
        PageElements.printHeader();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the cineplex you whose schedule you want to change: ");
        String cineplexName = sc.nextLine();
        Cineplex cineplex = CineplexManager.getCineplex(cineplexName);
        if (cineplex == null) {
            PageElements.printConsoleMessage("No such cineplex, add the cineplex first!");
            return;
        }
        System.out.println("Enter the date whose schedule you want to change (yyyy-mm-dd): ");
        LocalDate date = LocalDate.parse(sc.nextLine());
        if (!cineplex.getSchedules().containsKey(date)) {
            PageElements.printConsoleMessage("The schedule doesn't exists! Choose add to add it first.");
            return;
        }
        cineplex.getSchedules().remove(date);
        CineplexManager.updateCineplex(cineplex);
    }
}
