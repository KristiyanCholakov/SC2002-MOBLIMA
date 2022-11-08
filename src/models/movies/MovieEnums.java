package models.movies;

import java.io.Serializable;

public class MovieEnums implements Serializable {
    public enum MovieRestriction {
        G("General", "Suitable for all ages.", 0),
        PG("Parental Guidance", "Suitable for all, but parents should guide their young.", 0),
        PG13("Parental Guidance 13", "Suitable for persons aged 13 and above but parental guidance is advised for children below 13.", 13),
        R21("Restricted 21", "Restricted to persons aged 21 and above.", 21),
        NC16("No Children Under 16", "Suitable for persons aged 16 and above.", 16),
        M18("Mature 18", "Suitable for persons aged 18 and above.", 18);

        private String restriction;
        private String description;
        private int minAge;

        MovieRestriction(String restriction, String description, int minAge) {
            this.restriction = restriction;
            this.description = description;
            this.minAge = minAge;
        }

        public String getRestriction() {
            return restriction;
        }

        public String getDescription() {
            return description;
        }

        public int getMinAge() {
            return minAge;
        }
    }

    public enum MovieType {
        TWO_D("2D"),
        THREE_D("3D"),
        FOUR_DX("4DX"),
        IMAX("IMAX");

        private String type;

        MovieType (String type){
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public enum MovieStatus {
        COMING_SOON("Movie will be coming to the cinemas soon."),
        PREVIEW("Movie is available for preview."),
        NOW_SHOWING("Movie can be seen at the cinemas.");

        private String description;

        MovieStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
