package models.accounts;

import java.time.LocalDate;

/**
 *  The Admin class representing the admin record with its attributes and construction.
 *  
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class Admin extends User {

    /**
     * The Admin constructor creates a new admin. Identified by their first and last name, username, email and password.
     *
     * @param fNmae The first name of the admin.
     * @param lName The last name of the admin.
     * @param username The username of the admin.
     * @param email The email of the admin.
     * @param password The password of the admin.
     */
    public Admin(String fNmae, String lName, String username, String email, String password) {
        super(fNmae, lName, username, email, password, LocalDate.now());
    }

    /**
     * Custom toString method to return a sting of the admin with their specific attributes.
     *
     * @return String of the admin and their attributes.
     */
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
