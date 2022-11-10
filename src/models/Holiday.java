package models;

import java.io.Serializable;
import java.time.LocalDate;

public class Holiday implements Serializable {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private double additionalPrice;

    public Holiday(String name, LocalDate startDate, LocalDate endDate, double additionalPrice) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.additionalPrice = additionalPrice;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getAdditionalPrice() {
        return additionalPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean is(LocalDate date) {
        return (date.isAfter(startDate) && date.isBefore(endDate)) || date.equals(startDate) || date.equals(endDate);
    }

    @Override
    public boolean equals(Object obj) {
        Holiday holiday = (Holiday) obj;
        return this.name.equals(holiday.getName());
    }
}
