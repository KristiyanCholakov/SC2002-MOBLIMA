public class Actor extends Person{
    private int number_of_oscars;

    public Actor(String fName, String lName) {
        super(fName, lName);
    }

    public int getNumber_of_oscars() {
        return number_of_oscars;
    }

    public void setNumber_of_oscars(int number_of_oscars) {
        this.number_of_oscars = number_of_oscars;
    }
}
