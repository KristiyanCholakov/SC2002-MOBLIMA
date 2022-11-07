package models;

import java.time.LocalDate;

public class Holiday {
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setAdditionalPrice(double additionalPrice) {
        this.additionalPrice = additionalPrice;
    }
}
