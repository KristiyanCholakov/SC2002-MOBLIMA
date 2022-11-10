package pages.admin;

import data_managers.PricesManager;
import models.Prices;
import pages.PageElements;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  The PricesPages class holds the functionality connected with the prices pages for the admin.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class PricesPages {

    /**
     * The changePricesPage method gives the opportunity to choose which prices the admin wants to be changed.
     * If the choice is invalid asks for another. If it is valid redirects to the corresponding page.
     */
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
        int choice;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            choice = -1;
            PageElements.printConsoleMessage("You have to enter a number!");
        }
        scanner.nextLine();
        Prices prices = PricesManager.readPrices().get(0);
        ArrayList<Double> arrayList;
        switch (choice) {
            case 1:
                try {
                    arrayList = inputRegularMoviePrices();
                    prices.setRegularPrices(new Prices.RegularMoviePrices(arrayList.get(0), arrayList.get(1), arrayList.get(2),
                            arrayList.get(3), arrayList.get(4), arrayList.get(5), arrayList.get(6), arrayList.get(7), arrayList.get(8), arrayList.get(9)));

                } catch (InputMismatchException e) {
                    PageElements.printConsoleMessage("You have entered the wrong format!");
                    return;
                }
                break;
            case 2:
                try {
                    arrayList = inputSpecialMoviePrices();
                    prices.setThreeDPrices(new Prices.ThreeDMoviesPrices(arrayList.get(0), arrayList.get(1), arrayList.get(2),
                            arrayList.get(3), arrayList.get(4), arrayList.get(5)));
                } catch (InputMismatchException e) {
                    PageElements.printConsoleMessage("You have entered the wrong format!");
                    return;
                }
                break;
            case 3:
                try {
                    arrayList = inputSpecialMoviePrices();
                    prices.setFourDXPrices(new Prices.FourDXMoviesPrices(arrayList.get(0), arrayList.get(1), arrayList.get(2),
                            arrayList.get(3), arrayList.get(4), arrayList.get(5)));
                } catch (InputMismatchException e) {
                    PageElements.printConsoleMessage("You have entered the wrong format!");
                    return;
                }
                break;
            case 4:
                try {
                    arrayList = inputSpecialMoviePrices();
                    prices.setImaxPrices(new Prices.ImaxMoviesPrices(arrayList.get(0), arrayList.get(1), arrayList.get(2),
                            arrayList.get(3), arrayList.get(4), arrayList.get(5)));
                } catch (InputMismatchException e) {
                    PageElements.printConsoleMessage("You have entered the wrong format!");
                    return;
                }
                break;
            case 5:
                try {
                    System.out.println("Enter new added price for blockbuster:");
                    double b = scanner.nextDouble();
                    scanner.nextLine();
                    prices.setBlockbusterAddedPrice(b);
                } catch (InputMismatchException e) {
                    PageElements.printConsoleMessage("You have entered the wrong format!");
                    return;
                }
                break;
            case 6:
                return;
            default:
                PageElements.printConsoleMessage("Invalid Choice!");
        }
        PricesManager.deletePrices();
        PricesManager.writePrices(prices);
    }

    /**
     * The inputRegularMoviePrices asks for the new values of the prices for the regular movies.
     *
     * @return The array list with the new regular movie prices if all new values in wanted format. If not empty array list.
     */
    public static ArrayList<Double> inputRegularMoviePrices () {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> arrayList = new ArrayList<>();
        System.out.println("Senior Price:");
        double seniorPrice = scanner.nextDouble();
        scanner.nextLine();
        arrayList.add(seniorPrice);
        arrayList.addAll(inputSpecialMoviePrices());
        System.out.println("Loyalty Tier 1 Price:");
        double l1Price= scanner.nextDouble();
        scanner.nextLine();
        arrayList.add(l1Price);
        System.out.println("Loyalty Tier 2 Price:");
        double l2Price= scanner.nextDouble();
        scanner.nextLine();
        arrayList.add(l2Price);
        System.out.println("Loyalty Tier 3 Price:");
        double l3Price= scanner.nextDouble();
        scanner.nextLine();
        arrayList.add(l3Price);
        return arrayList;
    }

    /**
     * The inputSpecialMoviePrices asks for the new values of the prices for the corresponding special movies.
     *
     * @return The array list with the corresponding new special movie prices if all new values in wanted format. If not empty array list.
     */
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

    /**
     * The seePricesPage method shows all the prices for the different movie types.
     */
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
        Prices.ImaxMoviesPrices ip = prices.getImaxPrices();
        System.out.println("\nIMAX PRICES:");
        System.out.println("Student Price: " + ip.getStudentPrice());
        System.out.println("Mon-Wed Price: " + ip.getMonWedPrice());
        System.out.println("Thu Price: " + ip.getThuPrice());
        System.out.println("Fri Before 6pm Price: " + ip.getFridayB6Price());
        System.out.println("Fri After 6pm Price: " + ip.getFridayA6Price());
        System.out.println("Weekend Price: " + ip.getWeekendPrice());
        System.out.println("\nBLOCKBUSTER ADDED PRICE: " + prices.getBlockbusterAddedPrice());
    }
}
