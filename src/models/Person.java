package models;

import models.movies.Actor;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private String fName;
    private String lName;

    public Person(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getFullName() {
        return this.fName + " " + this.lName;
    }

    @Override
    public boolean equals(Object obj) {
        obj = (Person) obj;
        return ((Person) obj).getfName().equals(this.fName) && ((Person) obj).getlName().equals(this.lName);
    }
}
