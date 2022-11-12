package data_managers;

import models.accounts.User;
import pages.MainPage;
import pages.PageElements;

import java.io.*;
import java.util.ArrayList;

/**
 * The UserManager class takes care of interacting with the file where the users are stored.
 * It performs writing, updating, reading and searching in the users' storage.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class UserManager {

    /**
     * The USERS_PATH constant points to the file where users are stored.
     */
    private static final String USERS_PATH = "src/data/users.txt";

    /**
     * The readUsers function reads all users records from the users' storage.
     *
     * @return An array list of the users records from the storage. Empty array list if not any.
     */
    public static ArrayList<User> readUsers () {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_PATH));
            ArrayList<User> users = (ArrayList<User>) ois.readObject();
            ois.close();
            return users;
        } catch (FileNotFoundException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Users can't be read!");
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            PageElements.printConsoleMessage("Error!");
        }
        return new ArrayList<User>();
    }

    /**
     * The writeUser function adds a user record to the users' storage.
     *
     * @param user The user record to be written in the storage.
     */
    public static void writeUser (User user) {
        File file = new File(USERS_PATH);
        ArrayList<User> allUsers = readUsers();
        allUsers.add(user);
        if(file.exists()) file.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(USERS_PATH));
            out.writeObject(allUsers);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("User successfully registered!");
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! User is not saved to the database.");
        }
    }

    /**
     * The ifUserExists function checks if the user exists in the users' storage.
     *
     * @param user The user record to be checked if exists in the storage.
     * @return true if the user record exists. false if it does not.
     */
    public static boolean ifUserExists(User user) {
        ArrayList<User> allUsers = readUsers();
        for (int i = 0; i < allUsers.size(); i++) {
            User currentUser = allUsers.get(i);
            if (currentUser.toString().equals(user.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * The getUser function searches for a user with the same username in the storage and provides their record.
     *
     * @param username The username of the user that we search
     * @return The record of the user with the same username. null if a user with this title does not exist.
     */
    public static User getUser(String username) {
        ArrayList<User> allUsers = readUsers();
        for (int i = 0; i < allUsers.size(); i++) {
            User currentUser = allUsers.get(i);
            if (currentUser.getEmail().equals(username) || currentUser.getUsername().equals(username)) {
                return currentUser;
            }
        }
        return null;
    }

    /**
     * The updateUser function searches if the provided user record exists and if so updates their entry.
     *
     * @param user The user record to be updated in the storage.
     * @return true if the user record is updated successfully. false if updating failed.
     */
    public static boolean updateUser(User user) {
        ArrayList<User> allUsers = readUsers();
        User userToUpdated = getUser(user.getUsername());
        if (userToUpdated == null) {
            PageElements.printConsoleMessage("No such user!");
            return false;
        } else {
            allUsers.remove(userToUpdated);
            allUsers.add(user);
            File file = new File(USERS_PATH);
            if(file.exists()) file.delete();
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(USERS_PATH));
            out.writeObject(allUsers);
            out.flush();
            out.close();
            if (MainPage.getCurrentUser() == null) PageElements.printConsoleMessage("User Updated!");
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! User is not updated to the database.");
            return false;
        }
    }

    /**
     * The checkCredentials function checks if there exists a user account with the given username/email and password.
     *
     * @param username Either the username or the email with which the user tries to log in.
     * @param password The password of the user trying to log in.
     * @return The user record if there exists one with the corresponding email and password. null if it doesn't exist.
     */
    public static User checkCredentials(String username, String password) {
        ArrayList<User> allUsers = readUsers();
        for (int i = 0; i < allUsers.size(); i++) {
            User currentUser = allUsers.get(i);
            if ((currentUser.getEmail().equals(username) || currentUser.getUsername().equals(username)) && currentUser.getPassword().equals(password)) {
                return currentUser;
            }
        }
        PageElements.printConsoleMessage("Wrong credentials!");
        return null;
    }
}
