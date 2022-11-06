package pages;

import data_managers.CineplexManager;
import data_managers.MovieManager;
import models.cinemas.Cineplex;
import models.cinemas.ShowTime;
import models.movies.Movie;
import models.movies.MovieEnums;

import javax.sound.sampled.FloatControl;
import java.sql.Time;
import java.time.LocalDate;
import java.util.*;

public class BookingPages {
    public static void bookingPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Search by Movie\n" +
                    "       2 - Search by Cinema\n" +
                    "       3 - Search by Genre\n" +
                    "       4 - Back to User Portal" );
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    searchByMoviePage(MovieManager.readMovies());
                    break;
                case 2:
                    searchByCineplexPage();
                    break;
                case 3:
                    searchByGenrePage();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }

    public static void searchByCineplexPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            ArrayList<Cineplex> cineplexes = CineplexManager.readCineplexes();
            System.out.println("Cinemas:");
            for (int i = 0; i < cineplexes.size(); i++) {
                System.out.print("*");
                cineplexes.get(i).printCineplex();
            }
            System.out.println("Enter the name of the cineplex:");
            String cineplexName = scanner.nextLine();
            Cineplex cineplex = CineplexManager.getCineplex(cineplexName);
            if (cineplex == null) {
                PageElements.printConsoleMessage("The cinema doesn't exist!");
                return;
            }
            ArrayList<LocalDate> scheduleDates = new ArrayList<>();
            scheduleDates.addAll(cineplex.getSchedules().keySet());
            System.out.println("Available schedules:");
            for (int i = 0; i < scheduleDates.size(); i++) {
                System.out.println("*"+scheduleDates.get(i).toString());
            }
            System.out.println("Enter the date you want to check (yyyy-mm-dd):");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            ArrayList<ShowTime> schedule = cineplex.getSchedules().get(date);
            if (schedule == null || schedule.size()==0) {
                PageElements.printConsoleMessage("The cinema hasn't updated their schedule for the date you have selected!");
                return;
            }
            System.out.println("The schedule for " + date.toString() + ":");
            Collections.sort(schedule, new ShowTime.ByStartTime());
            for (int i = 0; i < schedule.size(); i++) {
                ShowTime showTime = schedule.get(i);
                System.out.print(i + 1 + ") ");
                showTime.printShowTime();
            }
            System.out.print("Select the showtime you want to book (enter -1 to go back): ");
            int showtimeChoice = scanner.nextInt();
            scanner.nextLine();
            if (showtimeChoice == -1) break;
            makeBooking(schedule.get(showtimeChoice-1));
        }
    }

    public static void searchByMoviePage(ArrayList<Movie> movies) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            for (int i = 0; i < movies.size(); i++) {
                Movie movie = movies.get(i);
                if (movie.getStatus() == MovieEnums.MovieStatus.PREVIEW || movie.getStatus() == MovieEnums.MovieStatus.NOW_SHOWING) {
                    System.out.print("*");
                    movie.printMovie();
                }
            }
            System.out.println("Select the movie you want to watch: ");
            String movieTitle = scanner.nextLine();
            Movie movie = MovieManager.getMovie(movieTitle);
            if (movie == null) {
                PageElements.printConsoleMessage("The movie doesn't exist!");
                return;
            }
            System.out.println("Enter the date you want to watch the movie (yyyy-mm-dd):");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            ArrayList<Cineplex> cineplexes = CineplexManager.readCineplexes();
            System.out.println("Available projections for that date");
            HashMap<Cineplex, ArrayList<ShowTime>> availableOptions = new HashMap<>();
            for (int i = 0; i < cineplexes.size(); i++) {
                Cineplex cineplex = cineplexes.get(i);
                if (cineplex.getSchedules().get(date) != null) {
                    ArrayList<ShowTime> schedule = cineplex.getSchedules().get(date);
                    ArrayList<ShowTime> availableShowtimes = new ArrayList<>();
                    for (int j = 0; j < schedule.size(); j++) {
                        ShowTime showTime = schedule.get(j);
                        if (showTime.getMovie().equals(movie)) {
                            availableShowtimes.add(showTime);
                        }
                    }
                    if (availableShowtimes.size() != 0) availableOptions.put(cineplex, availableShowtimes);
                }
            }
            if (availableOptions.isEmpty()) {
                System.out.println("None");
                return;
            }
            for (Cineplex cineplex: availableOptions.keySet()) {
                System.out.print("Available in  ");
                cineplex.printCineplex();
                ArrayList<ShowTime> available = availableOptions.get(cineplex);
                for (int i = 0; i < available.size(); i++) {
                    ShowTime showTime = available.get(i);
                    System.out.print((i+1)  + ")");
                    showTime.printShowTime();
                }
                System.out.println("");
            }
            System.out.println("Select the showtime you want to book");
            System.out.print("Cinema: ");
            String cName = scanner.nextLine();
            System.out.print("Option: ");
            int o = scanner.nextInt();
            scanner.nextLine();
            ShowTime option = null;
            for (Cineplex cineplex: availableOptions.keySet()) {
                if (cineplex.getName().equals(cName)) {
                    option = availableOptions.get(cineplex).get(o-1);
                }
            }
            if (option == null) {
                System.out.println("No such option!");
                return;
            }
            makeBooking(option);
        }
    }

    public static void searchByGenrePage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            System.out.println("Enter the genre you want to watch:");
            String wantedGenre = scanner.nextLine();
            ArrayList<Movie> moviesWanted = new ArrayList<>();
            ArrayList<Movie> movies = MovieManager.readMovies();
            for (int i = 0; i < movies.size(); i++) {
                ArrayList<String> movieGenres = movies.get(i).getGenre();
                for (int j = 0; j < movieGenres.size(); j++) {
                    if (movieGenres.get(j).equals(wantedGenre)) {
                        moviesWanted.add(movies.get(i));
                    }
                }
            }
            searchByMoviePage(moviesWanted);
        }
    }

    public static void makeBooking(ShowTime showTime) {
        PageElements.printHeader();
        System.out.println(showTime.toString());
    }
}
