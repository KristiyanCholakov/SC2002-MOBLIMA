package models;

import java.io.Serializable;

public class Prices implements Serializable {
    private double blockbusterAddedPrice;
    private RegularMoviePrices regularPrices;
    private ThreeDMoviesPrices threeDPrices;
    private FourDXMoviesPrices fourDXPrices;
    private ImaxMoviesPrices imaxPrices;

    public static class RegularMoviePrices extends SpecialMoviePrices {
        private double seniorPrice;
        private double loyalTier1Price;
        private double loyalTier2Price;
        private double loyalTier3Price;

        public RegularMoviePrices(double seniorPrice, double studentPrice, double monWedPrice, double thuPrice, double fridayB6Price, double fridayA6Price, double weekendPrice, double loyalTier1Price, double loyalTier2Price, double loyalTier3Price) {
            super(studentPrice, monWedPrice, thuPrice, fridayB6Price, fridayA6Price, weekendPrice);
            this.loyalTier1Price = loyalTier1Price;
            this.loyalTier2Price = loyalTier2Price;
            this.loyalTier3Price = loyalTier3Price;
        }

        public RegularMoviePrices() {
            super(7, 8.5, 9.5, 9.5, 11, 11);
            this.seniorPrice = 4;
            this.loyalTier1Price = 7;
            this.loyalTier2Price = 8.5;
            this.loyalTier3Price = 9.5;
        }

        public double getSeniorPrice() {
            return seniorPrice;
        }

        public double getLoyalTier1Price() {
            return loyalTier1Price;
        }

        public double getLoyalTier2Price() {
            return loyalTier2Price;
        }

        public double getLoyalTier3Price() {
            return loyalTier3Price;
        }

        public void setSeniorPrice(double seniorPrice) {
            this.seniorPrice = seniorPrice;
        }

        public void setLoyalTier1Price(double loyalTier1Price) {
            this.loyalTier1Price = loyalTier1Price;
        }

        public void setLoyalTier2Price(double loyalTier2Price) {
            this.loyalTier2Price = loyalTier2Price;
        }

        public void setLoyalTier3Price(double loyalTier3Price) {
            this.loyalTier3Price = loyalTier3Price;
        }
    }

    public static class ThreeDMoviesPrices extends SpecialMoviePrices {
        public ThreeDMoviesPrices(double studentPrice, double monWedPrice, double thuPrice, double fridayB6Price, double fridayA6Price, double weekendPrice) {
            super(studentPrice, monWedPrice, thuPrice, fridayB6Price, fridayA6Price, weekendPrice);
        }

        public ThreeDMoviesPrices() {
            super(9, 11, 11, 15, 15, 15);
        }
    }

    public static class FourDXMoviesPrices extends SpecialMoviePrices{
        public FourDXMoviesPrices(double studentPrice, double monWedPrice, double thuPrice, double fridayB6Price, double fridayA6Price, double weekendPrice) {
            super(studentPrice, monWedPrice, thuPrice, fridayB6Price, fridayA6Price, weekendPrice);
        }

        public FourDXMoviesPrices() {
            super(12, 14, 14, 14, 18, 18);
        }
    }

    public static class ImaxMoviesPrices extends SpecialMoviePrices{
        public ImaxMoviesPrices(double studentPrice, double monWedPrice, double thuPrice, double fridayB6Price, double fridayA6Price, double weekendPrice) {
            super(studentPrice, monWedPrice, thuPrice, fridayB6Price, fridayA6Price, weekendPrice);
        }

        public ImaxMoviesPrices() {
            super(11, 13, 13, 17, 17, 17);
        }
    }

    public abstract static class SpecialMoviePrices implements Serializable {
        private double studentPrice;
        private double monWedPrice;
        private double thuPrice;
        private double fridayB6Price;
        private double fridayA6Price;
        private double weekendPrice;

        public SpecialMoviePrices(double studentPrice, double monWedPrice, double thuPrice, double fridayB6Price, double fridayA6Price, double weekendPrice) {
            this.studentPrice = studentPrice;
            this.monWedPrice = monWedPrice;
            this.thuPrice = thuPrice;
            this.fridayB6Price = fridayB6Price;
            this.fridayA6Price = fridayA6Price;
            this.weekendPrice = weekendPrice;
        }

        public double getStudentPrice() {
            return studentPrice;
        }

        public double getMonWedPrice() {
            return monWedPrice;
        }

        public double getThuPrice() {
            return thuPrice;
        }

        public double getFridayB6Price() {
            return fridayB6Price;
        }

        public double getFridayA6Price() {
            return fridayA6Price;
        }

        public double getWeekendPrice() {
            return weekendPrice;
        }

        public void setStudentPrice(double studentPrice) {
            this.studentPrice = studentPrice;
        }

        public void setMonWedPrice(double monWedPrice) {
            this.monWedPrice = monWedPrice;
        }

        public void setThuPrice(double thuPrice) {
            this.thuPrice = thuPrice;
        }

        public void setFridayB6Price(double fridayB6Price) {
            this.fridayB6Price = fridayB6Price;
        }

        public void setFridayA6Price(double fridayA6Price) {
            this.fridayA6Price = fridayA6Price;
        }

        public void setWeekendPrice(double weekendPrice) {
            this.weekendPrice = weekendPrice;
        }
    }

    public Prices(double blockbusterAddedPrice, RegularMoviePrices regularPrices, ThreeDMoviesPrices threeDPrices, FourDXMoviesPrices fourDXPrices, ImaxMoviesPrices imaxPrices) {
        this.blockbusterAddedPrice = blockbusterAddedPrice;
        this.regularPrices = regularPrices;
        this.threeDPrices = threeDPrices;
        this.fourDXPrices = fourDXPrices;
        this.imaxPrices = imaxPrices;
    }

    public Prices() {
        this.blockbusterAddedPrice = 1;
        this.regularPrices = new RegularMoviePrices();
        this.threeDPrices = new ThreeDMoviesPrices();
        this.fourDXPrices = new FourDXMoviesPrices();
        this.imaxPrices = new ImaxMoviesPrices();
    }

    public double getBlockbusterAddedPrice() {
        return blockbusterAddedPrice;
    }

    public RegularMoviePrices getRegularPrices() {
        return regularPrices;
    }

    public ThreeDMoviesPrices getThreeDPrices() {
        return threeDPrices;
    }

    public FourDXMoviesPrices getFourDXPrices() {
        return fourDXPrices;
    }

    public ImaxMoviesPrices getImaxPrices() {
        return imaxPrices;
    }

    public void setBlockbusterAddedPrice(double blockbusterAddedPrice) {
        this.blockbusterAddedPrice = blockbusterAddedPrice;
    }

    public void setRegularPrices(RegularMoviePrices regularPrices) {
        this.regularPrices = regularPrices;
    }

    public void setThreeDPrices(ThreeDMoviesPrices threeDPrices) {
        this.threeDPrices = threeDPrices;
    }

    public void setFourDXPrices(FourDXMoviesPrices fourDXPrices) {
        this.fourDXPrices = fourDXPrices;
    }

    public void setImaxPrices(ImaxMoviesPrices imaxPrices) {
        this.imaxPrices = imaxPrices;
    }
}
