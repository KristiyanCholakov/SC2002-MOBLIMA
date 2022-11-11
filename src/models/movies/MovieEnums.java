package models.movies;

import java.io.Serializable;

/**
 * The MovieEnums class holds the enums about the movie type, restriction and status.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class MovieEnums implements Serializable {

    /**
     * The MovieRestriction enum holds the restriction of movie.
     */
    public enum MovieRestriction {

        /**
         * General restriction of a movie.
         */
        G("General", "Suitable for all ages.", 0),

        /**
         * Parental Guidance restriction of a movie.
         */
        PG("Parental Guidance", "Suitable for all, but parents should guide their young.", 0),

        /**
         * Parental Guidance 13 restriction of a movie.
         */
        PG13("Parental Guidance 13", "Suitable for persons aged 13 and above but parental guidance is advised for children below 13.", 13),

        /**
         * Restricted 21 restriction of a movie.
         */
        R21("Restricted 21", "Restricted to persons aged 21 and above.", 21),

        /**
         * No Children Under 16 restriction of a movie.
         */
        NC16("No Children Under 16", "Suitable for persons aged 16 and above.", 16),

        /**
         * Mature 18 restriction of a movie.
         */
        M18("Mature 18", "Suitable for persons aged 18 and above.", 18);

        /**
         * An attribute to hold the restriction name.
         */
        private String restriction;

        /**
         * An attribute to hold the restriction description.
         */
        private String description;

        /**
         * An attribute to hold the minimum age to watch the movie with this restriction.
         */
        private int minAge;

        /**
         * The MovieRestriction constructor accepting the restriction name and description, and the minimum age to watch.
         *
         * @param restriction The restriction name.
         * @param description The restriction description.
         * @param minAge The minimum age to watch the movie.
         */
        MovieRestriction(String restriction, String description, int minAge) {
            this.restriction = restriction;
            this.description = description;
            this.minAge = minAge;
        }

        /**
         * The getRestriction method gets the restriction name.
         *
         * @return The restriction name.
         */
        public String getRestriction() {
            return restriction;
        }

        /**
         * The getDescription method gets the restriction description.
         *
         * @return The restriction description.
         */
        public String getDescription() {
            return description;
        }

        /**
         * The getMinAge method gets the minimum age for the movie with the restriction.
         *
         * @return The minimum age for the movie with the restriction.
         */
        public int getMinAge() {
            return minAge;
        }
    }

    /**
     * The MovieType enum holds the type of movie.
     */
    public enum MovieType {

        /**
         * 2D movie type.
         */
        TWO_D("2D"),

        /**
         * 3D movie type.
         */
        THREE_D("3D"),

        /**
         * 4DX movie type.
         */
        FOUR_DX("4DX"),

        /**
         * IMAX movie type.
         */
        IMAX("IMAX");

        /**
         * The name of the type.
         */
        private String type;

        /**
         * The MovieType constructor accepting the type name.
         *
         * @param type The type name.
         */
        MovieType (String type){
            this.type = type;
        }

        /**
         * The getType method gets the type name.
         *
         * @return
         */
        public String getType() {
            return type;
        }
    }

    /**
     * The MovieStatus enum holds the status of movie.
     */
    public enum MovieStatus {

        /**
         * Coming Soon movie status.
         */
        COMING_SOON("Movie will be coming to the cinemas soon."),

        /**
         * Preview movie status.
         */
        PREVIEW("Movie is available for preview."),

        /**
         * End Showing movie status.
         */
        END_SHOWING("Movie's showing has ended."),

        /**
         * Now Showing movie status.
         */
        NOW_SHOWING("Movie can be seen at the cinemas.");

        /**
         * The description of the movie status
         */
        private String description;

        /**
         * The MovieType constructor accepting the description of the status.
         *
         * @param description The description of the status.
         */
        MovieStatus(String description) {
            this.description = description;
        }

        /**
         * The getDescription method gets the description of the movie status.
         *
         * @return The description of the movie status.
         */
        public String getDescription() {
            return description;
        }
    }
}
