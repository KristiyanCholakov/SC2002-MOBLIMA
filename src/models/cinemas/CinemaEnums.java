package models.cinemas;

import java.util.ArrayList;
import java.util.HashMap;

public class CinemaEnums {
    public enum CinemaType {
        NORMAL("Normal", 10.0),
        PLATINUM("Platinum",15.0 );
        private String type;
        private Double price;

        CinemaType(String type, Double price) {
            this.type = type;
            this.price = price;
        }

        public String getType() {
            return type;
        }

        public Double getPrice() {
            return price;
        }
    }

    public enum SeatConfiguration {
        SMALL_CINEMA("Small Room", 6, 10),
        MEDIUM_CINEMA("Medium Room", 10, 15),
        BIG_CINEMA("Big Room", 15, 25);

        private String description;
        private int rows;
        private int columns;

        SeatConfiguration(String description, int rows, int columns) {
            this.description = description;
            this.rows = rows;
            this.columns = columns;
        }

        public String getDescription() {
            return description;
        }

        public int getRows() {
            return rows;
        }

        public int getColumns() {
            return columns;
        }
    }
}
