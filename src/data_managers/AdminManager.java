package data_managers;

import models.accounts.Admin;
import pages.PageElements;

import java.io.*;
import java.util.ArrayList;

public class AdminManager {

    public static final String ADMINS_PATH = "src/data/admins.txt";

    public static ArrayList<Admin> readAdmins () {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ADMINS_PATH));
            ArrayList<Admin> admins = (ArrayList<Admin>) ois.readObject();
            ois.close();
            return admins;
        } catch (ClassNotFoundException | IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! User is not saved to the database.");
        }
        return new ArrayList<Admin>();
    }

    public static void showAdmins() {
        ArrayList<Admin> allAdmins = readAdmins();
        for (int i = 0; i < allAdmins.size(); i++) {
            Admin currentAdmin = allAdmins.get(i);
            System.out.print("*");
            System.out.println(currentAdmin.toString());
        }
    }

    public static boolean ifAdminExists(Admin admin) {
        ArrayList<Admin> allAdmins = readAdmins();
        for (int i = 0; i < allAdmins.size(); i++) {
            Admin currentAdmin = allAdmins.get(i);
            if (currentAdmin.toString().equals(admin.toString())) {
                return true;
            }
        }
        return false;
    }

    public static boolean writeAdmin (Admin admin) {
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
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Admin is not saved to the database.");
            return false;
        }
    }

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
