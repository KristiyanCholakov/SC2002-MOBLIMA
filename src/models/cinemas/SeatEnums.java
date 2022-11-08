package models.cinemas;

import java.io.Serializable;

public class SeatEnums implements Serializable {
    public enum SeatType {
        DELUXE("Deluxe", 3),
        NORMAL("Normal", 0),
        COUPLE("Couple", 2);

        private String type;
        private double addPrice;

        SeatType(String type, double addPrice) {
            this.type = type;
            this.addPrice = addPrice;
        }

        public String getType() {
            return type;
        }

        public double getAddPrice() {
            return addPrice;
        }
    }
}
