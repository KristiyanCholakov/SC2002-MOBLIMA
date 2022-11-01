package models.movies;

import java.io.Serializable;

public class MovieEnums implements Serializable {
    public enum MovieRestriction {
        G("General", "Suitable for all ages."),
        PG("Parental Guidance", "SSuitable for all, but parents should guide their young."),
        PG13("Parental Guidance 13", "Suitable for persons aged 13 and above but parental guidance is advised for children below 13."),
        R21("Restricted 21", "Restricted to persons aged 21 and above."),
        NC16("No Children Under 16", "Suitable for persons aged 16 and above."),
        M18("Mature 18", "Suitable for persons aged 18 and above.");

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
