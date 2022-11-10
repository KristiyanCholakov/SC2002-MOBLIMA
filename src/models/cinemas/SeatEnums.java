package models.cinemas;

import java.io.Serializable;

/**
 * The SeatEnums class holds the enums about the seat type.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class SeatEnums implements Serializable {

    /**
     * The SeatType enum holds the types of seat.
     */
    public enum SeatType {

        /**
         * The deluxe seat type.
         */
        DELUXE("Deluxe", 3),

        /**
         * The normal seat type.
         */
        NORMAL("Normal", 0),

        /**
         * The couple seat type.
         */
        COUPLE("Couple", 2);

        /**
         * An attribute holding the name of the seat type.
         */
        private String type;

        /**
         * An attribute holding the added price because of the seat type.
         */
        private double addPrice;

        /**
         * The SeatType constructor accepting the name of the type of the enum and the added price.
         *
         * @param type The name of the seat type.
         * @param addPrice The added price.
         */
        SeatType(String type, double addPrice) {
            this.type = type;
            this.addPrice = addPrice;
        }

        /**
         * The getType method to get the name of the seat type.
         *
         * @return The name of the seat type.
         */
        public String getType() {
            return type;
        }

        /**
         * The getAddPrice method gets the added price of the seat type.
         *
         * @return The added price of the seat type.
         */
        public double getAddPrice() {
            return addPrice;
        }
    }
}
