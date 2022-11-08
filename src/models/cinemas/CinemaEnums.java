package models.cinemas;

import java.util.ArrayList;
import java.util.HashMap;

public class CinemaEnums {
    public enum CinemaType {
        NORMAL("Normal"),
        PLATINUM("Platinum" );
        private String type;
        CinemaType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum SeatConfiguration {
        SMALL_CINEMA_C_D("Small Room with Couple and Deluxe seats", 6, 12, true),
        SMALL_CINEMA("Small Room", 6, 12, false),
        MEDIUM_CINEMA_C_D("Medium Room with Couple and Deluxe seats", 10, 16, true),
        MEDIUM_CINEMA("Medium Room", 10, 16, false),
        BIG_CINEMA_C_D("Big Room with Couple and Deluxe seats", 10, 16, true),
        BIG_CINEMA("Big Room", 15, 24, false);

        private String description;
        private int rows;
        private int columns;
        private boolean withPandC;

        SeatConfiguration(String description, int rows, int columns, boolean withPandC) {
            this.description = description;
            this.rows = rows;
            this.columns = columns;
            this.withPandC = withPandC;
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

        public boolean isWithPandC() {
            return withPandC;
        }
    }
}
