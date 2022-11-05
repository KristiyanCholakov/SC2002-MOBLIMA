package models.accounts;

import models.Person;

import java.time.LocalDate;

public class User extends Person {
    private String username;
    private String email;
    private String password;

    private LocalDate birthdate;

    public User(String fName, String lName, String username, String email, String password, LocalDate birthdate) {
        super(fName, lName);
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return user.email.equals(this.email);
    }

    @Override
    public String toString() {
        return "User:\n" +
                "Username=" + this.username + "\n" +
                "Email=" + this.email + "\n" +
                "Password=" + this.password + "\n" +
                "F_Name=" + super.getfName() + "\n" +
                "L_Name=" + super.getlName() + "\n" +
                "Birthday=" + getBirthdate().toString();
    }
}
