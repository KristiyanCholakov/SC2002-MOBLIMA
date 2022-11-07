import data_managers.PricesManager;
import models.Prices;
import pages.MainPage;

public class MOBLIMA {
    public static void main(String[] args) {
        if (PricesManager.readPrices().size() == 0) {
            Prices prices = new Prices();
            PricesManager.writePrices(prices);
        }
        MainPage.mainPage();
    }
}
