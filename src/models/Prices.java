package models;

public class Prices {
    private

    public class RegularMoviePrices {
        private double seniorPrice;
        private double studentPrice;
        private double monWedPrice;
        private double thuPrice;
        private double fridayB6Price;
        private double fridayA6Price;
        private double weekendPrice;
        private double loyalTier1Price;
        private double loyalTier2Price;
        private double loyalTier3Price;

        public RegularMoviePrices(double seniorPrice, double studentPrice, double monWedPrice, double thuPrice, double fridayB6Price, double fridayA6Price, double weekendPrice, double loyalTier1Price, double loyalTier2Price, double loyalTier3Price) {
            this.seniorPrice = seniorPrice;
            this.studentPrice = studentPrice;
            this.monWedPrice = monWedPrice;
            this.thuPrice = thuPrice;
            this.fridayB6Price = fridayB6Price;
            this.fridayA6Price = fridayA6Price;
            this.weekendPrice = weekendPrice;
            this.loyalTier1Price = loyalTier1Price;
            this.loyalTier2Price = loyalTier2Price;
            this.loyalTier3Price = loyalTier3Price;
        }
    }

    public class ThreeDMoviesPrices {
        private double studentPrice;
        private double monWedPrice;
        private double thuPrice;
        private double fridayB6Price;
        private double fridayA6Price;
        private double weekendPrice;

        public ThreeDMoviesPrices(double studentPrice, double monWedPrice, double thuPrice, double fridayB6Price, double fridayA6Price, double weekendPrice) {
            this.studentPrice = studentPrice;
            this.monWedPrice = monWedPrice;
            this.thuPrice = thuPrice;
            this.fridayB6Price = fridayB6Price;
            this.fridayA6Price = fridayA6Price;
            this.weekendPrice = weekendPrice;
        }
    }

    public class FourDXMoviesPrices {
        private double studentPrice;
        private double monWedPrice;
        private double thuPrice;
        private double fridayB6Price;
        private double fridayA6Price;
        private double weekendPrice;

        public FourDXMoviesPrices(double studentPrice, double monWedPrice, double thuPrice, double fridayB6Price, double fridayA6Price, double weekendPrice) {
            this.studentPrice = studentPrice;
            this.monWedPrice = monWedPrice;
            this.thuPrice = thuPrice;
            this.fridayB6Price = fridayB6Price;
            this.fridayA6Price = fridayA6Price;
            this.weekendPrice = weekendPrice;
        }
    }

    public class ImaxMoviesPrices {
        private double studentPrice;
        private double monWedPrice;
        private double thuPrice;
        private double fridayB6Price;
        private double fridayA6Price;
        private double weekendPrice;

        public ImaxMoviesPrices(double studentPrice, double monWedPrice, double thuPrice, double fridayB6Price, double fridayA6Price, double weekendPrice) {
            this.studentPrice = studentPrice;
            this.monWedPrice = monWedPrice;
            this.thuPrice = thuPrice;
            this.fridayB6Price = fridayB6Price;
            this.fridayA6Price = fridayA6Price;
            this.weekendPrice = weekendPrice;
        }
    }
}
