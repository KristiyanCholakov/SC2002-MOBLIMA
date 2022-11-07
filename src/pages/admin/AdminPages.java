package pages.admin;

import data_managers.AdminManager;
import data_managers.PricesManager;
import models.Prices;
import models.accounts.Admin;
import pages.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class AdminPages {

    public static void adminLoginPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        System.out.println("ADMIN LOGIN\n");
        System.out.print("Email/Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();
        Admin loggedUser = AdminManager.checkCredentials(username, password);
        if (loggedUser != null) {
            PageElements.printConsoleMessage("Successful Login!");
            adminPage();
        }
    }

    public static void adminPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Editor Page\n" +
                    "       2 - Browsing Page\n" +
                    "       3 - Change Prices\n" +
                    "       4 - See Prices\n" +
                    "       5 - Back to Start Page");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    adminEditPage();
                    break;
                case 2:
                    adminShowPage();
                    break;
                case 3:
                    changePricesPage();
                case 4:
                    seePricesPage();
                case 5:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }

    public static void adminShowPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Show Movies\n" +
                    "       2 - Show Cineplexes\n" +
                    "       3 - Show Schedules\n" +
                    "       4 - Show Reviews\n" +
                    "       5 - Back to Admin Portal");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    BrowsingPages.showMoviesPage();
                    break;
                case 2:
                    BrowsingPages.showCineplexesPage();
                    break;
                case 3:
                    BrowsingPages.showSchedules();
                    break;
                case 4:
                    BrowsingPages.showReviewsPage();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }

    public static void adminEditPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Movies Editor\n" +
                    "       2 - Cineplex Editor\n" +
                    "       3 - Schedule Editor\n" +
                    "       4 - Reviews Editor\n" +
                    "       5 - Back to Admin Portal");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    MovieEditorPages.movieEditorPage();
                    break;
                case 2:
                    CineplexEditorPages.cinemaEditorPage();
                    break;
                case 3:
                    ScheduleEditorPages.showtimeEditorPage();
                    break;
                case 4:
                    ReviewEditorPages.reviewEditorPage();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }

    public static void changePricesPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        System.out.println("Select the prices you want to change:\n" +
                "(Type the number of the choice)\n" +
                "       1 - Regular Movies (2D)\n" +
                "       2 - 3D Movies\n" +
                "       3 - 4DX Movies\n" +
                "       4 - IMAX Movies\n" +
                "       5 - Blockbuster Added Price\n" +
                "       6 - Back to Admin Portal");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        Prices prices = PricesManager.readPrices().get(0);
        ArrayList<Double> arrayList;
        switch (choice) {
            case 1:
                arrayList = inputRegularMoviePrices();
                prices.setRegularPrices(new Prices.RegularMoviePrices(arrayList.get(0), arrayList.get(1), arrayList.get(2),
                        arrayList.get(3), arrayList.get(4), arrayList.get(5), arrayList.get(6), arrayList.get(7), arrayList.get(8), arrayList.get(9)));
                break;
            case 2:
                arrayList = inputRegularMoviePrices();
                prices.setThreeDPrices(new Prices.ThreeDMoviesPrices(arrayList.get(0), arrayList.get(1), arrayList.get(2),
                        arrayList.get(3), arrayList.get(4), arrayList.get(5)));
                break;
            case 3:
                arrayList = inputRegularMoviePrices();
                prices.setFourDXPrices(new Prices.FourDXMoviesPrices(arrayList.get(0), arrayList.get(1), arrayList.get(2),
                        arrayList.get(3), arrayList.get(4), arrayList.get(5)));
                break;
            case 4:
                arrayList = inputRegularMoviePrices();
                prices.setImaxPrices(new Prices.ImaxMoviesPrices(arrayList.get(0), arrayList.get(1), arrayList.get(2),
                        arrayList.get(3), arrayList.get(4), arrayList.get(5)));
                break;
            case 5:
                System.out.println("Enter new added price for blockbuster:");
                double b = scanner.nextDouble();
                scanner.nextLine();
                prices.setBlockbusterAddedPrice(b);
                break;
            case 6:
                return;
            default:
                PageElements.printConsoleMessage("Invalid Choice!");
        }
        PricesManager.deletePrices();
        PricesManager.writePrices(prices);
    }

    public static ArrayList<Double> inputRegularMoviePrices () {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> arrayList = new ArrayList<>();
        System.out.println("Senior Price:");
        double seniorPrice = scanner.nextDouble();
        scanner.nextLine();
        arrayList.add(seniorPrice);
        arrayList.addAll(inputRegularMoviePrices());
        System.out.println("Loyalty Tier 1 Price:");
        double l1Price= scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Loyalty Tier 2 Price:");
        double l2Price= scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Loyalty Tier 3 Price:");
        double l3Price= scanner.nextDouble();
        scanner.nextLine();
        arrayList.add(l1Price);
        arrayList.add(l2Price);
        arrayList.add(l3Price);
        return arrayList;
    }

    public static ArrayList<Double> inputSpecialMoviePrices () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Student Price:");
        double studentPrice = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Mon-Wed Price:");
        double monWedPrice= scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Thu Price:");
        double thuPrice= scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Fri Before 6pm Price:");
        double friB6Price= scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Fri After 6pm Price:");
        double friA6Price= scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Weekend Price:");
        double weekendPrice= scanner.nextDouble();
        scanner.nextLine();
        ArrayList<Double> arrayList = new ArrayList<>();
        arrayList.add(studentPrice);
        arrayList.add(monWedPrice);
        arrayList.add(thuPrice);
        arrayList.add(friB6Price);
        arrayList.add(friA6Price);
        arrayList.add(weekendPrice);
        return arrayList;
    }

    public static void seePricesPage () {
        Prices prices = PricesManager.readPrices().get(0);
        System.out.println("Regular (2D) Movies Prices:");
        Prices.RegularMoviePrices rp = prices.getRegularPrices();
        System.out.println("Senior Price: " + rp.getSeniorPrice());
        System.out.println("Student Price: " + rp.getStudentPrice());
        System.out.println("Mon-Wed Price: " + rp.getMonWedPrice());
        System.out.println("Thu Price: " + rp.getThuPrice());
        System.out.println("Fri Before 6pm Price: " + rp.getFridayB6Price());
        System.out.println("Fri After 6pm Price: " + rp.getFridayA6Price());
        System.out.println("Weekend Price: " + rp.getWeekendPrice());
        System.out.println("Loyalty Tier 1 Price: " + rp.getLoyalTier1Price());
        System.out.println("Loyalty Tier 2 Price: " + rp.getLoyalTier2Price());
        System.out.println("Loyalty Tier 3 Price: " + rp.getLoyalTier3Price());
        Prices.ThreeDMoviesPrices tp = prices.getThreeDPrices();
        System.out.println("\n3D PRICES:");
        System.out.println("Student Price: " + tp.getStudentPrice());
        System.out.println("Mon-Wed Price: " + tp.getMonWedPrice());
        System.out.println("Thu Price: " + tp.getThuPrice());
        System.out.println("Fri Before 6pm Price: " + tp.getFridayB6Price());
        System.out.println("Fri After 6pm Price: " + tp.getFridayA6Price());
        System.out.println("Weekend Price: " + tp.getWeekendPrice());
        Prices.FourDXMoviesPrices fp = prices.getFourDXPrices();
        System.out.println("\n4DX PRICES:");
        System.out.println("Student Price: " + fp.getStudentPrice());
        System.out.println("Mon-Wed Price: " + fp.getMonWedPrice());
        System.out.println("Thu Price: " + fp.getThuPrice());
        System.out.println("Fri Before 6pm Price: " + fp.getFridayB6Price());
        System.out.println("Fri After 6pm Price: " + fp.getFridayA6Price());
        System.out.println("Weekend Price: " + fp.getWeekendPrice());
        Prices.RegularMoviePrices ip = prices.getRegularPrices();
        System.out.println("\nIMAX PRICES:");
        System.out.println("Mon-Wed Price: " + ip.getMonWedPrice());
        System.out.println("Thu Price: " + ip.getThuPrice());
        System.out.println("Fri Before 6pm Price: " + ip.getFridayB6Price());
        System.out.println("Fri After 6pm Price: " + ip.getFridayA6Price());
        System.out.println("Weekend Price: " + ip.getWeekendPrice());
        System.out.println("\nBLOCKBUSTER ADDED PRICE: " + prices.getBlockbusterAddedPrice());
    }
}