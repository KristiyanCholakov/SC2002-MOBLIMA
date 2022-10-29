package models;

public class Director extends Person {
    private int numberOfOscars;

    public Director(String fName, String lName) {
        super(fName, lName);
    }

    public int getNumberOfOscars() {
        return numberOfOscars;
    }

    public void setNumberOfOscars(int numberOfOscars) {
        this.numberOfOscars = numberOfOscars;
    }
}
