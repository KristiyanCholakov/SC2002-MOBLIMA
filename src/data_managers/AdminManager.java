package data_managers;

import models.accounts.Admin;
import pages.PageElements;

import java.io.*;
import java.util.ArrayList;

/**
 * The AdminManager class takes care of interacting with the file where the admin accounts are stored.
 * It performs writing, updating, reading and searching in the admins' storage.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class AdminManager {

    /**
     * The ADMINS_PATH constant points to the file where admins are stored.
     */
    public static final String ADMINS_PATH = "src/data/admins.txt";

    /**
     * The readAdmins function reads all admin records from the admins' storage.
     *
     * @return An array list of the admin records from the storage. Empty array list if not any.
     */
    public static ArrayList<Admin> readAdmins () {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ADMINS_PATH));
            ArrayList<Admin> admins = (ArrayList<Admin>) ois.readObject();
            ois.close();
            return admins;
        } catch (FileNotFoundException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Admins can't be read!");
        } catch (EOFException e) {
            PageElements.printConsoleMessage("File was empty. This is the first item in it.");
        } catch (IOException | ClassNotFoundException e) {
            PageElements.printConsoleMessage("Error!");
        }
        return new ArrayList<Admin>();
    }

    /**
     * The ifAdminExists function checks if an admin record exists in the admins' storage.
     *
     * @param admin The admin record to be checked for existence in the storage.
     * @return true if the admin exists in the storage. false if not.
     */
    public static boolean ifAdminExists(Admin admin) {
        ArrayList<Admin> allAdmins = readAdmins();
        for (int i = 0; i < allAdmins.size(); i++) {
            Admin currentAdmin = allAdmins.get(i);
            if (currentAdmin.getEmail().equals(admin.getEmail()) || currentAdmin.getUsername().equals(admin.getUsername())) {
                return true;
            }
        }
        return false;
    }

    /**
     * The writeAdmin function adds an admin record to the admins' storage.
     *
     * @param admin The admin record to be written in the storage.
     * @return true if the admin record is added successfully. false if writing failed.
     */
    public static void writeAdmin (Admin admin) {
        File file = new File(ADMINS_PATH);
        ArrayList<Admin> allAdmins = readAdmins();
        allAdmins.add(admin);
        if(file.exists()) file.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ADMINS_PATH));
            out.writeObject(allAdmins);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("Admin successfully registered!");
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Admin is not saved to the database.");
        }
    }

    /**
     * The checkCredentials function checks if there exists an admin account with the given username/email and password.
     *
     * @param username Either the username or the email with which the admin tries to log in.
     * @param password The password of the admin trying to log in.
     * @return The admin record if there exists one with the corresponding email and password. null if it doesn't exist.
     */
    public static Admin checkCredentials(String username, String password) {
        ArrayList<Admin> allAdmins = readAdmins();
        for (int i = 0; i < allAdmins.size(); i++) {
            Admin currentAdmin = allAdmins.get(i);
            if ((currentAdmin.getEmail().equals(username) || currentAdmin.getUsername().equals(username)) && currentAdmin.getPassword().equals(password)) {
                return currentAdmin;
            }
        }
        PageElements.printConsoleMessage("Wrong credentials!");
        return null;
    }
}
