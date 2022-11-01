package models.movies;

import java.io.Serializable;

public class MovieEnums implements Serializable {
    public enum MovieRestriction {
        G("General", "All ages admitted"),
        PG("Parental Guidance Suggested", "Some material may not be suitable for children"),
        PG13("Parental Strongly Cautioned", "Some material maybe inappropriate for children under 13"),
        R("Restricted", "Under 17 requires accompany paren or adult guardian"),
        NC17("No one 17 and under admitted", "No one 17 and under admitted");

        private String restriction;
        private String description;

        MovieRestriction(String restriction, String description) {
            this.restriction = restriction;
            this.description = description;
        }

        public String getRestriction() {
            return restriction;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum MovieType {
        TWO_D("2D", 9.0),
        THREE_D("3D", 12.0),
        FOUR_DX("4DX", 18.0),
        IMAX("IMAX", 16.0);

        private String type;
        private Double ticketPrice;

        MovieType (String type, Double ticketPrice){
            this.type = type;
            this.ticketPrice = ticketPrice;
        }

        public String getType() {
            return type;
        }

        public Double getTicketPrice() {
            return ticketPrice;
        }

        public void setTicketPrice(Double ticketPrice) {
            this.ticketPrice = ticketPrice;
        }
    }

    public enum MovieStatus {
        COMING_SOON,
        PREVIEW,
        NOW_SHOWING;
    }
}
