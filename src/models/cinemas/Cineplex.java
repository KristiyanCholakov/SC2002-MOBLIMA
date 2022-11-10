package models.cinemas;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *  The Cineplex class representing the cineplex record with its attributes, methods and construction.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class Cineplex implements Serializable {

    /**
     * An attribute holding the name of the cineplex.
     */
    private String name;

    /**
     * An attribute holding the address of the cineplex.
     */
    private String address;

    /**
     * An attribute holding the schedules of the cineplex.
     */
    private HashMap<LocalDate, ArrayList<ShowTime>> schedules;

    /**
     * An attribute holding the cinemas of the cineplex.
     */
    private ArrayList<Cinema> cinemas;


    /**
     * The Cineplex constructor to create a new cineplex entry.
     *
     * @param name The name of the cineplex.
     * @param address The address of the cineplex.
     * @param cinemas The cinemas of the cineplex.
     */
    public Cineplex(String name, String address, ArrayList<Cinema> cinemas) {
        this.name = name;
        this.address = address;
        this.schedules = new HashMap<LocalDate, ArrayList<ShowTime>>();
        this.cinemas = cinemas;
    }

    /**
     * The getName method to get the name of the cineplex.
     *
     * @return The name of the cineplex.
     */
    public String getName() {
        return name;
    }

    /**
     * The getAddress method to get the address of the cineplex.
     *
     * @return The address of the cineplex.
     */
    public String getAddress() {
        return address;
    }

    /**
     * The getSchedules method gets the schedules of the cineplex.
     *
     * @return Array lists of the showtimes for the specific date.
     */
    public HashMap<LocalDate, ArrayList<ShowTime>> getSchedules() {
        return schedules;
    }

    /**
     * The getCinemas method gets the cinemas of the cineplex.
     *
     * @return An array list of the cinemas of the cineplex.
     */
    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    /**
     * The cinemasToString method gets the text representation of the cinemas of the cineplex.
     *
     * @return String of the cinemas' data of the cineplex.
     */
    public String cinemasToString() {
        String cinemasStr = "";
        for (int i = 0; i < this.cinemas.size(); i++) {
            cinemasStr += this.cinemas.get(i).toString();
            if (i != this.cinemas.size()) cinemasStr+="\n";
        }
        return cinemasStr;
    }

    /**
     * The setName method changes the name of the cineplex.
     *
     * @param name The new name of the cineplex.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The setAddress changes the address of the cineplex.
     *
     * @param address The new address of the cineplex.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * The setSchedules changes the schedules of the cineplex.
     *
     * @param schedules The new schedules of the cineplex.
     */
    public void setSchedules(HashMap<LocalDate, ArrayList<ShowTime>> schedules) {
        this.schedules = schedules;
    }

    /**
     * The setCinemas changes the cinemas of the cineplex.
     *
     * @param cinemas The new cinemas of the cineplex.
     */
    public void setCinemas(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    /**
     * The printCineplex method prints simplified information of the cineplex.
     */
    public void printCineplex() {
        System.out.println(this.name + " (" + this.address + ")");
    }

    /**
     * The cancelBooking method to update the showtime when a booking is canceled.
     *
     * @param date The date of the booking to be canceled.
     * @param showTime The showtime of the booking to be canceled.
     * @param seat The seat of the booking to be canceled.
     * @return true if the booking is successfully canceled. false if not.
     */
    public boolean cancelBooking(LocalDate date, ShowTime showTime, Seat seat) {
        for (int i = 0; i < this.schedules.get(date).size(); i++) {
            if (this.schedules.get(date).get(i).getMovie().equals(showTime.getMovie()) &&
                    this.schedules.get(date).get(i).getStartTime().equals(showTime.getStartTime())) {
                return this.schedules.get(date).get(i).getSeatOccupancy().get(seat.getRow()).get(seat.getColumn()-1).cancelSeat();
            }
        }
        return false;
    }

    /**
     * Custom equals method to check if 2 cineplexes are the same.
     *
     * @param obj Another cineplex to be compared.
     * @return true if the cineplexes meet the custom condition. false if not.
     */
    @Override
    public boolean equals(Object obj) {
        Cineplex cineplex = (Cineplex) obj;
        return (cineplex.getName().equals(this.name));
    }

    /**
     * Custom toString method to get a text representation of the cineplex record.
     *
     * @return String of the representation of the main information of the cineplex.
     */
    @Override
    public String toString() {
        return "Cineplex:\n" +
                "Name=" + this.name + "\n" +
                "Address=" + this.address + "\n" +
                "Cinemas=\n" + this.cinemasToString();
    }
}
