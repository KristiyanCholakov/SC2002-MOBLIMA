package models;

import java.io.Serializable;

/**
 *  The Prices class will store all the different prices.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class Prices implements Serializable {

    /**
     * An attribute holding the added price if a movie is a blockbuster.
     */
    private double blockbusterAddedPrice;

    /**
     * An attribute holding the regular prices for 2D movies.
     */
    private RegularMoviePrices regularPrices;

    /**
     * An attribute holding the prices for 3D movies.
     */
    private ThreeDMoviesPrices threeDPrices;

    /**
     * An attribute holding the prices for 4DX movies.
     */
    private FourDXMoviesPrices fourDXPrices;

    /**
     * An attribute holding the prices for IMAX movies.
     */
    private ImaxMoviesPrices imaxPrices;

    /**
     * The RegularMoviePrices class holds the price attributes for 2D movies.
     */
    public static class RegularMoviePrices extends SpecialMoviePrices {

        /**
         * An attribute holding the price for seniors.
         */
        private double seniorPrice;

        /**
         * An attribute holding the price for people with loyal tier 1.
         */
        private double loyalTier1Price;

        /**
         * An attribute holding the price for people with loyal tier 2.
         */
        private double loyalTier2Price;

        /**
         * An attribute holding the price for people with loyal tier 3.
         */
        private double loyalTier3Price;

        /**
         * The RegularMoviePrices for creating custom prices for 2D movies.
         *
         * @param seniorPrice The price for seniors
         * @param studentPrice The price for students
         * @param monWedPrice The monday to wednesday price
         * @param thuPrice The thursday price
         * @param fridayB6Price The friday before 6pm price
         * @param fridayA6Price The friday after 6pm price
         * @param weekendPrice The weekedn price
         * @param loyalTier1Price The loyal tier 1 price
         * @param loyalTier2Price The loyal tier 2 price
         * @param loyalTier3Price The loyal tier 3 price
         */
        public RegularMoviePrices(double seniorPrice, double studentPrice, double monWedPrice, double thuPrice, double fridayB6Price, double fridayA6Price, double weekendPrice, double loyalTier1Price, double loyalTier2Price, double loyalTier3Price) {
            super(studentPrice, monWedPrice, thuPrice, fridayB6Price, fridayA6Price, weekendPrice);
            this.loyalTier1Price = loyalTier1Price;
            this.loyalTier2Price = loyalTier2Price;
            this.loyalTier3Price = loyalTier3Price;
            this.seniorPrice = seniorPrice;
        }

        /**
         * A default constructor for the default prices.
         */
        public RegularMoviePrices() {
            super(7, 8.5, 9.5, 9.5, 11, 11);
            this.seniorPrice = 4;
            this.loyalTier1Price = 7;
            this.loyalTier2Price = 8.5;
            this.loyalTier3Price = 9.5;
        }

        /**
         * The getSeniorPrice method gets the price for seniors.
         *
         * @return The price for seniors.
         */
        public double getSeniorPrice() {
            return seniorPrice;
        }

        /**
         * The getLoyalTier1Price method gets the price for loyal tier 1.
         *
         * @return The price for loyal tier 1.
         */
        public double getLoyalTier1Price() {
            return loyalTier1Price;
        }

        /**
         * The getLoyalTier2Price method gets the price for loyal tier 2.
         *
         * @return The price for loyal tier 2.
         */
        public double getLoyalTier2Price() {
            return loyalTier2Price;
        }

        /**
         * The getLoyalTier3Price method gets the price for loyal tier 3.
         *
         * @return The price for loyal tier 3.
         */
        public double getLoyalTier3Price() {
            return loyalTier3Price;
        }
    }

    /**
     * The ThreeDMoviesPrices class holds the price attributes for 3D movies.
     */
    public static class ThreeDMoviesPrices extends SpecialMoviePrices {

        /**
         * The ThreeDMoviesPrices for creating custom prices for 3D movies.
         *
         * @param studentPrice The price for students
         * @param monWedPrice The monday to wednesday price
         * @param thuPrice The thursday price
         * @param fridayB6Price The friday before 6pm price
         * @param fridayA6Price The friday after 6pm price
         * @param weekendPrice The weekedn price
         */
        public ThreeDMoviesPrices(double studentPrice, double monWedPrice, double thuPrice, double fridayB6Price, double fridayA6Price, double weekendPrice) {
            super(studentPrice, monWedPrice, thuPrice, fridayB6Price, fridayA6Price, weekendPrice);
        }

        /**
         * A default constructor for the default prices.
         */
        public ThreeDMoviesPrices() {
            super(9, 11, 11, 15, 15, 15);
        }
    }

    /**
     * The FourDXMoviesPrices class holds the price attributes for 3D movies.
     */
    public static class FourDXMoviesPrices extends SpecialMoviePrices{

        /**
         * The FourDXMoviesPrices for creating custom prices for 4DX movies.
         *
         * @param studentPrice The price for students
         * @param monWedPrice The monday to wednesday price
         * @param thuPrice The thursday price
         * @param fridayB6Price The friday before 6pm price
         * @param fridayA6Price The friday after 6pm price
         * @param weekendPrice The weekedn price
         */
        public FourDXMoviesPrices(double studentPrice, double monWedPrice, double thuPrice, double fridayB6Price, double fridayA6Price, double weekendPrice) {
            super(studentPrice, monWedPrice, thuPrice, fridayB6Price, fridayA6Price, weekendPrice);
        }

        /**
         * A default constructor for the default prices.
         */
        public FourDXMoviesPrices() {
            super(12, 14, 14, 14, 18, 18);
        }
    }

    /**
     * The ImaxMoviesPrices class holds the price attributes for 3D movies.
     */
    public static class ImaxMoviesPrices extends SpecialMoviePrices{

        /**
         * The ImaxMoviesPrices for creating custom prices for IMAX movies.
         *
         * @param studentPrice The price for students
         * @param monWedPrice The monday to wednesday price
         * @param thuPrice The thursday price
         * @param fridayB6Price The friday before 6pm price
         * @param fridayA6Price The friday after 6pm price
         * @param weekendPrice The weekedn price
         */
        public ImaxMoviesPrices(double studentPrice, double monWedPrice, double thuPrice, double fridayB6Price, double fridayA6Price, double weekendPrice) {
            super(studentPrice, monWedPrice, thuPrice, fridayB6Price, fridayA6Price, weekendPrice);
        }

        /**
         * A default constructor for the default prices.
         */
        public ImaxMoviesPrices() {
            super(11, 13, 13, 17, 17, 17);
        }
    }

    /**
     * The SpecialMoviePrices abstract class holds the price attributes for the special movies (3D, 4DX, IMAX).
     */
    public abstract static class SpecialMoviePrices implements Serializable {

        /**
         * An attribute holding the student price.
         */
        private double studentPrice;

        /**
         * An attribute holding the monday to wednesday price.
         */
        private double monWedPrice;

        /**
         * An attribute holding the thursday price.
         */
        private double thuPrice;

        /**
         * An attribute holding the friday before 6pm price.
         */
        private double fridayB6Price;

        /**
         * An attribute holding the friday after 6pm price.
         */
        private double fridayA6Price;

        /**
         * An attribute holding the weekend price.
         */
        private double weekendPrice;

        /**
         * The SpecialMoviePrices for assigning custom prices;
         *
         * @param studentPrice The price for students
         * @param monWedPrice The monday to wednesday price
         * @param thuPrice The thursday price
         * @param fridayB6Price The friday before 6pm price
         * @param fridayA6Price The friday after 6pm price
         * @param weekendPrice The weekedn price
         */
        public SpecialMoviePrices(double studentPrice, double monWedPrice, double thuPrice, double fridayB6Price, double fridayA6Price, double weekendPrice) {
            this.studentPrice = studentPrice;
            this.monWedPrice = monWedPrice;
            this.thuPrice = thuPrice;
            this.fridayB6Price = fridayB6Price;
            this.fridayA6Price = fridayA6Price;
            this.weekendPrice = weekendPrice;
        }

        /**
         * The getStudentPrice gets the price for students.
         *
         * @return The price for students.
         */
        public double getStudentPrice() {
            return studentPrice;
        }

        /**
         * The getMonWedPrice gets the price for monday to wednesday.
         *
         * @return The price for monday to wednesday.
         */
        public double getMonWedPrice() {
            return monWedPrice;
        }

        /**
         * The getThuPrice gets the price for thursday.
         *
         * @return The price for thursday.
         */
        public double getThuPrice() {
            return thuPrice;
        }

        /**
         * The getFridayB6Price gets the price for friday before 6pm.
         *
         * @return The price for friday before 6pm.
         */
        public double getFridayB6Price() {
            return fridayB6Price;
        }

        /**
         * The getFridayA6Price gets the price for friday after 6pm.
         *
         * @return The price for friday after 6pm.
         */
        public double getFridayA6Price() {
            return fridayA6Price;
        }

        /**
         * The getWeekendPrice gets the price for weekends.
         *
         * @return The price for weekends.
         */
        public double getWeekendPrice() {
            return weekendPrice;
        }
    }

    /**
     * The Prices constructor for creating the prices with custom values.
     *
     * @param blockbusterAddedPrice The added price for blockbusters.
     * @param regularPrices The prices for the 2D movies.
     * @param threeDPrices The prices for the 3D movies.
     * @param fourDXPrices The prices for the 4DX movies.
     * @param imaxPrices The prices for the IMAX movies.
     */
    public Prices(double blockbusterAddedPrice, RegularMoviePrices regularPrices, ThreeDMoviesPrices threeDPrices, FourDXMoviesPrices fourDXPrices, ImaxMoviesPrices imaxPrices) {
        this.blockbusterAddedPrice = blockbusterAddedPrice;
        this.regularPrices = regularPrices;
        this.threeDPrices = threeDPrices;
        this.fourDXPrices = fourDXPrices;
        this.imaxPrices = imaxPrices;
    }

    /**
     * The default constructor for the prices with default values.
     */
    public Prices() {
        this.blockbusterAddedPrice = 1;
        this.regularPrices = new RegularMoviePrices();
        this.threeDPrices = new ThreeDMoviesPrices();
        this.fourDXPrices = new FourDXMoviesPrices();
        this.imaxPrices = new ImaxMoviesPrices();
    }

    /**
     * The getBlockbusterAddedPrice gets the blockbuster added price.
     *
     * @return The added price for blockbuster movies.
     */
    public double getBlockbusterAddedPrice() {
        return blockbusterAddedPrice;
    }

    /**
     * The getRegularPrices gets the prices for 2D movies.
     *
     * @return The prices for 2D movies.
     */
    public RegularMoviePrices getRegularPrices() {
        return regularPrices;
    }

    /**
     * The getThreeDPrices gets the prices for 3D movies.
     *
     * @return The prices for 3D movies.
     */
    public ThreeDMoviesPrices getThreeDPrices() {
        return threeDPrices;
    }

    /**
     * The getFourDXPrices gets the prices for 4DX movies.
     *
     * @return The prices for 4DX movies.
     */
    public FourDXMoviesPrices getFourDXPrices() {
        return fourDXPrices;
    }

    /**
     * The getImaxPrices gets the prices for IMAX movies.
     *
     * @return The prices for IMAX movies.
     */
    public ImaxMoviesPrices getImaxPrices() {
        return imaxPrices;
    }

    /**
     * The setBlockbusterAddedPrice changes the added price for the blockbuster movies.
     *
     * @param blockbusterAddedPrice The new blockbuster added price.
     */
    public void setBlockbusterAddedPrice(double blockbusterAddedPrice) {
        this.blockbusterAddedPrice = blockbusterAddedPrice;
    }

    /**
     * The setRegularPrices changes the prices for the 2D movies.
     *
     * @param regularPrices The new 2D movies prices.
     */
    public void setRegularPrices(RegularMoviePrices regularPrices) {
        this.regularPrices = regularPrices;
    }

    /**
     * The setThreeDPrices changes the prices for the 3D movies.
     *
     * @param threeDPrices The new 3D movies prices.
     */
    public void setThreeDPrices(ThreeDMoviesPrices threeDPrices) {
        this.threeDPrices = threeDPrices;
    }

    /**
     * The setFourDXPrices changes the prices for the 4DX movies.
     *
     * @param fourDXPrices The new 4DX movies prices.
     */
    public void setFourDXPrices(FourDXMoviesPrices fourDXPrices) {
        this.fourDXPrices = fourDXPrices;
    }

    /**
     * The setImaxPrices changes the prices for the IMAX movies.
     *
     * @param imaxPrices The new IMAX movies prices.
     */
    public void setImaxPrices(ImaxMoviesPrices imaxPrices) {
        this.imaxPrices = imaxPrices;
    }
}
