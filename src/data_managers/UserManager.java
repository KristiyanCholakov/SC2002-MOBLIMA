package data_managers;

import models.accounts.User;
import pages.PageElements;

import java.io.*;
import java.util.ArrayList;

public class UserManager {

    private static final String USERS_PATH = "src/data/users.txt";


    public static ArrayList<User> readUsers () {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_PATH));
            ArrayList<User> users = (ArrayList<User>) ois.readObject();
            ois.close();
            return users;
        } catch (ClassNotFoundException | IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! User is not saved to the database.");
        }
        return new ArrayList<User>();
    }

    public static boolean writeUser (User user) {
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
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! User is not saved to the database.");
            return false;
        }
    }

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
