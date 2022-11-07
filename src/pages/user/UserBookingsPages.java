package pages.user;

import data_managers.CineplexManager;
import data_managers.UserManager;
import models.accounts.Booking;
import models.accounts.User;
import models.cinemas.Cineplex;
import pages.MainPage;
import pages.PageElements;

import java.util.ArrayList;
import java.util.Scanner;

public class UserBookingsPages {
    public static void userBookingsPage () {
        Scanner scanner = new Scanner(System.in);
        User user = MainPage.getCurrentUser();
        ArrayList<Booking> bookings = user.getBookings();
        if (bookings.size() == 0) {
            System.out.println("You don't have any bookings!");
            return;
        }
        for (int i = 0; i < bookings.size(); i++) {
            Booking booking = bookings.get(i);
            System.out.print((i+1) + ")");
            System.out.println(booking.printBooking());
        }
        PageElements.printLine();
        System.out.println("Enter the booking you want to cancel: (enter -1 if you don't want to cancel any.");
        int bookingIdx = scanner.nextInt()-1;
        scanner.nextLine();
        if (bookingIdx == -1) {
            return;
        }
        if (bookingIdx >= bookings.size()) {
            PageElements.printConsoleMessage("Wrong booking choice!");
            return;
        }
        Booking canceled = bookings.get(bookingIdx);
        bookings.remove(canceled);
        Cineplex cineplex = CineplexManager.getCineplex(canceled.getCineplex().getName());
        if (cineplex.cancelBooking(canceled.getDate(), canceled.getShowTime(), canceled.getSeat())) {
            PageElements.printConsoleMessage("Successfully Canceled!");
        } else {
            PageElements.printConsoleMessage("Error! Seat not canceled!");
            return;
        }
        CineplexManager.updateCineplex(cineplex);
        UserManager.updateUser(user);
    }
}
