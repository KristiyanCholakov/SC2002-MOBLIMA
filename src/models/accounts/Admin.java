package models.accounts;

import models.accounts.User;

import java.time.LocalDate;

public class Admin extends User {
    public Admin(String fNmae, String lName, String username, String email, String password) {
        super(fNmae, lName, username, email, password, LocalDate.now());
    }

    @Override
    public String toString() {
        return "Admin:\n" +
                "Username=" + super.getUsername() + "\n" +
                "Email=" + super.getEmail() + "\n" +
                "Password=" + super.getPassword() + "\n" +
                "DateCreated=" + super.getBirthdate().toString() + "\n" +
                "Password=" + super.getPassword() + "\n" +
                "FirstName=" + super.getfName() + "\n" +
                "LastName=" + super.getlName();
    }
}
