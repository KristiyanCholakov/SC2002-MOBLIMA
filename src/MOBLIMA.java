import data_managers.PricesManager;
import models.Prices;
import pages.MainPage;

/**
 *  The MOBLIMA class starts the program.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class MOBLIMA {

    /**
     * Starts the program and load the prices if none are detected.
     * @param args
     */
    public static void main(String[] args) {
        if (PricesManager.readPrices().size() == 0) {
            Prices prices = new Prices();
            PricesManager.writePrices(prices);
        }
        MainPage.mainPage();
    }
}
