package models;

import java.io.Serializable;

/**
 *  The Person abstract class representing a person with its attributes, methods and construction.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public abstract class Person implements Serializable {

    /**
     * An attribute holding the first name of the person.
     */
    private String fName;

    /**
     * An attribute holding the last name of the person.
     */
    private String lName;

    /**
     * The Person constructor assigning the first and last name.
     *
     * @param fName The first name of the person.
     * @param lName The last name of the person.
     */
    public Person(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }

    /**
     * The getFName method gets the first name of the person.
     *
     * @return The first name of the person.
     */
    public String getFName() {
        return fName;
    }

    /**
     * The getLName method gets the last name of the person.
     *
     * @return The last name of the person.
     */
    public String getLName() {
        return lName;
    }


    /**
     * The getFullName method gets the combined full name consisting of the first and the last names.
     *
     * @return The full name of the person.
     */
    public String getFullName() {
        return this.fName + " " + this.lName;
    }

    /**
     * Custom equals method to check if 2 people are the same based on their first and last names.
     *
     * @param obj The people to be compared.
     * @return true if the people have the same first and last names. false if not.
     */
    @Override
    public boolean equals(Object obj) {
        Person actor = (Person) obj;
        return (actor.getFName().equals(this.getFName()) && actor.getLName().equals(this.getLName()));
    }
}
