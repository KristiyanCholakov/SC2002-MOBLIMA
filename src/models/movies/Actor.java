package models.movies;

import data_managers.ActorManager;
import models.Person;

import java.util.Comparator;

public class Actor extends Director {
    private int numberOfOscars;

    public Actor(String fName, String lName, int numberOfMovies, int numberOfOscars) {
        super(fName, lName, numberOfMovies);
        this.numberOfOscars = numberOfOscars;
    }

    public int getNumberOfOscars() {
        return numberOfOscars;
    }


    public static class ByNumberOfOscars implements Comparator<Actor> {

        @Override
        public int compare(Actor o1, Actor o2) {
            return o2.getNumberOfOscars() - o1.getNumberOfOscars();
        }
    }

    @Override
    public boolean equals(Object obj) {
        Actor actor = (Actor) obj;
        return (actor.getfName().equals(this.getfName()) && actor.getlName().equals(this.getlName()));
    }

    @Override
    public String toString() {
        return "Actor:\n" +
                "F_Name=" + super.getfName() + "\n" +
                "L_Name=" + super.getlName() + "\n" +
                "NumberOfOscars=" + getNumberOfOscars() + "\n"+
                "NumberOfMovies=" + super.getNumberOfMovies();
    }
}
