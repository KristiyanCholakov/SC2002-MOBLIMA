package models.movies;

import models.Person;

public class Director extends Person {
    private int numberOfMovies;

    public Director(String fName, String lName, int numberOfMovies) {
        super(fName, lName);
        this.numberOfMovies =numberOfMovies;
    }

    public int getNumberOfMovies() {
        return numberOfMovies;
    }

    public void setNumberOfMovies(int numberOfMovies) {
        this.numberOfMovies = numberOfMovies;
    }

    @Override
    public String toString() {
        return "Director:\n" +
                "F_Name=" + super.getfName() + "\n" +
                "L_Name=" + super.getlName() + "\n" +
                "NumberOfOscars=" + this.numberOfMovies;
    }
}
