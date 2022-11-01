package models.movies;

import models.Person;

public class Actor extends Director {
    private int numberOfOscars;

    public Actor(String fName, String lName, int numberOfMovies, int numberOfOscars) {
        super(fName, lName, numberOfMovies);
        this.numberOfOscars = numberOfOscars;
    }

    public int getNumberOfOscars() {
        return numberOfOscars;
    }
}
