package pages.user;

import data_managers.*;
import models.Holiday;
import models.Prices;
import models.accounts.Booking;
import models.accounts.User;
import models.cinemas.*;
import models.movies.Actor;
import models.movies.Movie;
import models.movies.MovieEnums;
import pages.MainPage;
import pages.PageElements;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
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
                    "       4 - Back to User Portal");
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
            System.out.println("*" + scheduleDates.get(i).toString());
        }
        System.out.println("Enter the date you want to check (yyyy-mm-dd):");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        ArrayList<ShowTime> schedule = cineplex.getSchedules().get(date);
        if (schedule == null || schedule.size() == 0) {
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
        if (showtimeChoice == -1) return;
        makeBooking(schedule.get(showtimeChoice - 1), cineplex, date);

    }

    public static void searchByMoviePage(ArrayList<Movie> movies) {
        Scanner scanner = new Scanner(System.in);
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
        for (Cineplex cineplex : availableOptions.keySet()) {
            System.out.print("Available in ");
            cineplex.printCineplex();
            ArrayList<ShowTime> available = availableOptions.get(cineplex);
            for (int i = 0; i < available.size(); i++) {
                ShowTime showTime = available.get(i);
                System.out.print((i + 1) + ")");
                showTime.printShowTime();
            }
            System.out.println("");
        }
        System.out.println("Select the showtime you want to book");
        System.out.print("Cineplex: ");
        String cName = scanner.nextLine();
        System.out.print("Option: ");
        int o = scanner.nextInt();
        scanner.nextLine();
        ShowTime option = null;
        for (Cineplex cineplex : availableOptions.keySet()) {
            if (cineplex.getName().equals(cName)) {
                option = availableOptions.get(cineplex).get(o - 1);
            }
        }
        if (option == null) {
            System.out.println("No such option!");
            return;
        }
        makeBooking(option, CineplexManager.getCineplex(cName), date);
    }

    public static void searchByGenrePage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        PageElements.printHeader();
        System.out.println("Enter the genre you want to watch:");
        String wantedGenre = scanner.nextLine();
        ArrayList<Movie> moviesWanted = new ArrayList<>();
        ArrayList<Movie> movies = MovieManager.readMovies();
        for (int i = 0; i < movies.size(); i++) {
            ArrayList<String> movieGenres = movies.get(i).getGenres();
            for (int j = 0; j < movieGenres.size(); j++) {
                if (movieGenres.get(j).equals(wantedGenre)) {
                    moviesWanted.add(movies.get(i));
                }
            }
        }
        searchByMoviePage(moviesWanted);
    }

    public static void makeBooking(ShowTime showTime, Cineplex cineplex, LocalDate date) {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        Movie movie = showTime.getMovie();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Duration: " + movie.getDuration().toString());
        System.out.println("Restriction: " + movie.getRestriction().getRestriction() + " - " + movie.getRestriction().getDescription());
        System.out.println("Status: " + movie.getStatus().getDescription());
        System.out.println("Genre: " + movie.genresToString());
        System.out.println("Synopsis: " + movie.getSynopsis());
        System.out.println("Directed by: " + movie.getDirector().getfName() + " " + movie.getDirector().getlName());
        System.out.println("Main Actors:");
        for (int i = 0; i < movie.getCast().size(); i++) {
            Actor actor = movie.getCast().get(i);
            System.out.println("    *" + actor.getfName() + " " + actor.getlName());
        }
        Period period = MainPage.getCurrentUser().getBirthdate().until(LocalDate.now());
        int yearsOld = period.getYears();
        if (yearsOld < movie.getRestriction().getMinAge()) {
            PageElements.printConsoleMessage("The user is too young to watch the movie.\n" + movie.getRestriction().getDescription());
            return;
        }
        PageElements.printLine();
        printSeatOccupation(showTime);
        System.out.println("Select your seat:");
        System.out.print("Row: ");
        Character row = scanner.nextLine().charAt(0);
        System.out.print("Column: ");
        int column = scanner.nextInt() - 1;
        scanner.nextLine();
        Seat selectedSeat = showTime.getSeatOccupancy().get(row).get(column);
        double price = getPrice(showTime, date, MainPage.getCurrentUser(), selectedSeat);
        System.out.println("TOTAL PRICE: " + price);
        if (selectedSeat.bookSeat()) {
            PageElements.printConsoleMessage("You have successfully booked the seat.");
            HashMap<LocalDate, ArrayList<ShowTime>> schedules = cineplex.getSchedules();
            ArrayList<ShowTime> schedule = schedules.get(date);
            for (int i = 0; i < schedule.size(); i++) {
                ShowTime s = schedule.get(i);
                if (s.getStartTime().equals(showTime.getStartTime()) &&
                        s.getMovie().equals(showTime.getMovie()) && s.getCinema().getNumber() == showTime.getCinema().getNumber()) {
                    schedule.get(i).getSeatOccupancy().get(row).get(column).bookSeat();
                }
            }
            schedules.put(date, schedule);
            cineplex.setSchedules(schedules);
            CineplexManager.updateCineplex(cineplex);
            Booking booking = new Booking(LocalDate.now(), date, cineplex, showTime, selectedSeat, price);
            Movie m = MovieManager.getMovie(movie.getTitle());
            m.setTicketsSold(m.getTicketsSold()+1);
            MovieManager.updateMovie(m);
            MainPage.getCurrentUser().addBooking(booking);
            UserManager.updateUser(MainPage.getCurrentUser());
        } else {
            System.out.println("The seat is already occupied!");
        }
    }

    public static void printSeatOccupation(ShowTime showTime) {
        Cinema cinema = showTime.getCinema();
        int columns = cinema.getSeatConfiguration().getColumns();
        int breakAt;
        if (cinema.getSeatConfiguration() == CinemaEnums.SeatConfiguration.BIG_CINEMA)
            breakAt = CinemaEnums.SeatConfiguration.BIG_CINEMA.getColumns() / 3;
        else breakAt = cinema.getSeatConfiguration().getColumns() / 2;
        int alignLength = columns * 3 + 8 - "Screen".length() + (columns - 1) / breakAt * 2;
        System.out.println(" ".repeat(alignLength / 2) + "SCREEN" + " ".repeat(alignLength / 2));
        for (Character column : showTime.getSeatOccupancy().keySet()) {
            System.out.print(column + "   ");
            ArrayList<Seat> seatsInRow = showTime.getSeatOccupancy().get(column);
            for (int i = 0; i < seatsInRow.size(); i++) {
                Seat seat = seatsInRow.get(i);
                if (seat.getType() == SeatEnums.SeatType.COUPLE) {
                    if (i * 2 % breakAt == 0 && i * 2 != 0) System.out.print("  ");
                    System.out.print(seat.printSeat());
                } else {
                    if (i % breakAt == 0 && i != 0) System.out.print("  ");
                    System.out.print(seat.printSeat());
                }
            }
            System.out.println("   " + column);
        }
        System.out.print("    ");
        for (int i = 1; i <= columns; i++) {
            if ((i - 1) % breakAt == 0 && (i - 1) != 0) System.out.print("  ");
            if (i < 10) System.out.print(i + "  ");
            else System.out.print(i + " ");
        }
        System.out.println();
    }

    public static double getPrice(ShowTime showTime, LocalDate date, User user, Seat seat) {
        //todo determine the price
        System.out.println("Your price is based on: ");
        System.out.println("*The movie is " + showTime.getMovie().getType().getType());
        Prices prices = PricesManager.readPrices().get(0);
        double added = 0;
        if (showTime.getMovie().isBlockbuster()) {
            System.out.println("*The movie is a blockbuster");
            added += prices.getBlockbusterAddedPrice();
        }
        if (seat.getType() != SeatEnums.SeatType.NORMAL) {
            System.out.println("*Your seat is a " + seat.getType().getType() + " seat");
            added += seat.getType().getAddPrice();
        }
        if (showTime.getMovie().getType() == MovieEnums.MovieType.TWO_D) {
            if (user.getBookings().size() > 9) {
                if (user.getBookings().size() > 29) {
                    System.out.println("*Your are a loyalty client tier 3 (more than 29 bookings)");
                    return prices.getRegularPrices().getLoyalTier2Price() + added;
                } else if (user.getBookings().size() > 19) {
                    System.out.println("*Your are a loyalty client tier 2 (more than 19 bookings)");
                    return prices.getRegularPrices().getLoyalTier1Price() + added;
                }
                System.out.println("*Your are a loyalty client tier 3 (more than 9 bookings)");
                return prices.getRegularPrices().getLoyalTier3Price() + added;
            } else if (user.getYears() < 18 && isHoliday(date) == null && (date.getDayOfWeek() != DayOfWeek.SATURDAY || date.getDayOfWeek() != DayOfWeek.SUNDAY) && showTime.getStartTime().isBefore(LocalTime.parse("18:00:00"))) {
                System.out.println("*Your are a student");
                return prices.getRegularPrices().getStudentPrice() + added;
            } else if (user.getYears() >= 55  && isHoliday(date) == null  && (date.getDayOfWeek() != DayOfWeek.SATURDAY || date.getDayOfWeek() != DayOfWeek.SUNDAY) && showTime.getStartTime().isBefore(LocalTime.parse("18:00:00"))) {
                System.out.println("*Your are a senior");
                return prices.getRegularPrices().getSeniorPrice() + added;
            } else {
                if (date.getDayOfWeek() == DayOfWeek.MONDAY || date.getDayOfWeek() == DayOfWeek.TUESDAY || date.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                    System.out.println("*It is Monday - Wednesday");
                    return prices.getRegularPrices().getMonWedPrice() + added;
                } else if (date.getDayOfWeek() == DayOfWeek.THURSDAY) {
                    System.out.println("*It is Thursday");
                    return prices.getRegularPrices().getThuPrice() + added;
                } else if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                    if (showTime.getStartTime().isBefore(LocalTime.parse("18:00:00"))) {
                        System.out.println("*It is Friday Before 6pm");
                        return prices.getRegularPrices().getFridayB6Price() + added;
                    } else {
                        System.out.println("*It is Friday After 6pm");
                        return prices.getRegularPrices().getFridayA6Price() + added;
                    }
                }
            }
        } else {
            Prices.SpecialMoviePrices specialPrices = null;
            if (showTime.getMovie().getType() == MovieEnums.MovieType.THREE_D) {
                specialPrices = prices.getThreeDPrices();
            } else if (showTime.getMovie().getType() == MovieEnums.MovieType.FOUR_DX) {
                specialPrices = prices.getFourDXPrices();
            } else if (showTime.getMovie().getType() == MovieEnums.MovieType.IMAX) {
                specialPrices = prices.getImaxPrices();
            }
            if (user.getYears() < 18 && isHoliday(date) == null && (date.getDayOfWeek() != DayOfWeek.SATURDAY || date.getDayOfWeek() != DayOfWeek.SUNDAY) && showTime.getStartTime().isBefore(LocalTime.parse("18:00:00"))) {
                System.out.println("*Your are a student");
                return specialPrices.getStudentPrice() + added;
            } else {
                if (date.getDayOfWeek() == DayOfWeek.MONDAY || date.getDayOfWeek() == DayOfWeek.TUESDAY || date.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                    System.out.println("*It is Monday - Wednesday");
                    return specialPrices.getMonWedPrice() + added;
                } else if (date.getDayOfWeek() == DayOfWeek.THURSDAY) {
                    System.out.println("*It is Thursday");
                    return specialPrices.getThuPrice() + added;
                } else if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                    if (showTime.getStartTime().isBefore(LocalTime.parse("18:00:00"))) {
                        System.out.println("*It is Friday Before 6pm");
                        return specialPrices.getFridayB6Price() + added;
                    } else {
                        System.out.println("*It is Friday After 6pm");
                        return specialPrices.getFridayA6Price() + added;
                    }
                }
            }
        }
        return -1;
    }

    public static Holiday isHoliday (LocalDate date) {
        ArrayList<Holiday> holidays = HolidayManager.readHolidays();
        for (int i = 0; i < holidays.size(); i++) {
            Holiday holiday = holidays.get(i);
            if (holiday.is(date)) {
                return holiday;
            }
        }
        return null;
    }
}
