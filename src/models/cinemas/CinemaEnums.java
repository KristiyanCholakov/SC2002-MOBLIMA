package models.cinemas;

/**
 * The CinemaEnums class holds the enums about the cinema type and seat configuration.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class CinemaEnums {

    /**
     * The CinemaType enum holds the types of cinema.
     */
    public enum CinemaType {

        /**
         * Normal type of cinema.
         */
        NORMAL("Normal"),

        /**
         * Platinum type of cinema.
         */
        PLATINUM("Platinum" );

        /**
         * The attribute to hold the type name of the enum.
         */
        private String type;

        /**
         * The CinemaType constructor accepting the name of the type of the enum.
         *
         * @param type The name of the type
         */
        CinemaType(String type) {
            this.type = type;
        }

        /**
         * The getType method for getting the type name of the enum.
         *
         * @return The type name.
         */
        public String getType() {
            return type;
        }
    }

    /**
     * The SeatConfiguration enum holds the possible seat configurations of cinema.
     */
    public enum SeatConfiguration {

        /**
         * The enum of small cinema with couple and deluxe seats.
         */
        SMALL_CINEMA_C_D("Small Room with Couple and Deluxe seats", 6, 12, true),

        /**
         * The enum of small cinema without couple and deluxe seats.
         */
        SMALL_CINEMA("Small Room", 6, 12, false),

        /**
         * The enum of medium cinema with couple and deluxe seats.
         */
        MEDIUM_CINEMA_C_D("Medium Room with Couple and Deluxe seats", 10, 16, true),

        /**
         * The enum of medium cinema without couple and deluxe seats.
         */
        MEDIUM_CINEMA("Medium Room", 10, 16, false),

        /**
         * The enum of big cinema with couple and deluxe seats.
         */
        BIG_CINEMA_C_D("Big Room with Couple and Deluxe seats", 10, 16, true),

        /**
         * The enum of big cinema without couple and deluxe seats.
         */
        BIG_CINEMA("Big Room", 15, 24, false);

        /**
         * The attribute showing the description of the seat configuration of the cinema.
         */
        private String description;

        /**
         * The attribute shows the number of rows in the cinema.
         */
        private int rows;

        /**
         * The attribute shows the number of columns in the cinema.
         */
        private int columns;

        /**
         * The attribute shows whether the cinema configuration has couple and deluxe seats.
         */
        private boolean withDandC;

        /**
         *
         * @param description The text description of the seat configuration.
         * @param rows The number of rows of the seat configuration.
         * @param columns The number of columns of the seat configuration.
         * @param withDandC Whether the seat configuration includes couple and deluxe seats.
         */
        SeatConfiguration(String description, int rows, int columns, boolean withDandC) {
            this.description = description;
            this.rows = rows;
            this.columns = columns;
            this.withDandC = withDandC;
        }

        /**
         * The getDescription method gets the text description of the seat configuration.
         * @return The text description of the seat configuration.
         */
        public String getDescription() {
            return description;
        }

        /**
         * The getRows method gets the number of rows of the seat configuration.
         *
         * @return The number of rows of the seat configuration.
         */
        public int getRows() {
            return rows;
        }

        /**
         * The getColumns method gets the number of columns of the seat configuration.
         *
         * @return The number of columns of the seat configuration.
         */
        public int getColumns() {
            return columns;
        }

        /**
         * The getColumns method gets if the seat configuration includes deluxe and couple seats.
         *
         * @return true if the seat configuration includes couple and deluxe seats. false if not.
         */
        public boolean isWithDandC() {
            return withDandC;
        }
    }
}
