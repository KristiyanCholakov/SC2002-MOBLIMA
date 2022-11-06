package pages;

import data_managers.CineplexManager;
import data_managers.MovieManager;
import models.cinemas.Cinema;
import models.cinemas.Cineplex;
import models.cinemas.ShowTime;
import models.movies.Movie;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ScheduleEditorPages {
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
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addSchedulePage();
                    break;
                case 2:
                    editSchedulePage();
                    break;
                case 3:
                    deleteSchedulePage();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }

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

    public static void editSchedulePage() {
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
        ArrayList<ShowTime> schedule = inputSchedule(cineplex, date);
        if (schedule == null) return;
        cineplex.getSchedules().put(date, schedule);
        CineplexManager.updateCineplex(cineplex);
    }

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
        ArrayList<ShowTime> schedule = new ArrayList<>();
        cineplex.getSchedules().put(date, schedule);
        CineplexManager.updateCineplex(cineplex);
    }
}
