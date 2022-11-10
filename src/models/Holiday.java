package models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *  The Holiday class representing a holiday with its attributes, methods and construction.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class Holiday implements Serializable {

    /**
     * An attribute holding the name of the holiday.
     */
    private String name;

    /**
     * An attribute holding the start date of the holiday.
     */
    private LocalDate startDate;

    /**
     * An attribute holding the end date of the holiday.
     */
    private LocalDate endDate;

    /**
     * An attribute holding the additional price because of the holiday.
     */
    private double additionalPrice;

    /**
     * The Holiday constructor creates a new holiday.
     *
     * @param name The holiday name.
     * @param startDate The holiday start date.
     * @param endDate The holiday end date.
     * @param additionalPrice The additional price because of the holiday.
     */
    public Holiday(String name, LocalDate startDate, LocalDate endDate, double additionalPrice) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.additionalPrice = additionalPrice;
    }

    /**
     * The getName gets the name of the holiday.
     *
     * @return The name of the holiday.
     */
    public String getName() {
        return name;
    }

    /**
     * The getStartDate gets the start date of the holiday.
     *
     * @return The start date of the holiday as a LocalDate record.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * The getStartDate gets the end date of the holiday.
     *
     * @return The end date of the holiday as a LocalDate record.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * The getStartDate gets the added price because of the holiday.
     *
     * @return The added price because of the holiday.
     */
    public double getAdditionalPrice() {
        return additionalPrice;
    }

    /**
     * The setName method changes the name of the holiday.
     *
     * @param name The new name of the holiday.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The is method check if the entered date is during the holiday.
     *
     * @param date The date to be compared
     * @return true if the holiday is on this date. false if not
     */
    public boolean is(LocalDate date) {
        return (date.isAfter(startDate) && date.isBefore(endDate)) || date.equals(startDate) || date.equals(endDate);
    }

    /**
     * Custom equals method to check if holidays are the same based on their name
     *
     * @param obj A holiday to be compared.
     * @return true if the holidays have the same names. false if not.
     */
    @Override
    public boolean equals(Object obj) {
        Holiday holiday = (Holiday) obj;
        return this.name.equals(holiday.getName());
    }
}
