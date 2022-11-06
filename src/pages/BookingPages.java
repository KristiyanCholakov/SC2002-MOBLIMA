package pages;

import data_managers.CineplexManager;
import models.cinemas.Cineplex;
import models.cinemas.ShowTime;

import java.time.LocalDate;
import java.util.*;

public class BookingPages {
    public static void bookingPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Search by Movie\n" +
                    "       2 - Search by Cinema\n" +
                    "       3 - Back to User Portal" );
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    BookingPages.bookingPage();
                    break;
                case 2:
                    ExplorePages.explorePages();
                    break;
                case 3:
                    UserBookingsPages.userBookingsPage();
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
        System.out.println("Enter the name of the cineplex:");
        String cineplexName = scanner.nextLine();
        Cineplex cineplex = CineplexManager.getCineplex(cineplexName);
        if (cineplex == null) {
            PageElements.printConsoleMessage("The cinema doesn't exist!");
            return;
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
            showTime.printShowTime();
        }
    }
}
