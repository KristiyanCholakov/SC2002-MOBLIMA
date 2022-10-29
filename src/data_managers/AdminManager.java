package data_managers;

import constants.Paths;
import constants.Regexes;
import models.Admin;
import models.User;
import pages.PageElements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminManager {

    public static void showAdmins() {
        try {
            List<String> lines = Files.readAllLines(Paths.admins_path);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.equals("")) continue;

                System.out.print("*");
                System.out.println(line);
            }
        } catch (IOException exception) {
            PageElements.printConsoleMessage("Error: Invalid Path! Can't be checked if the user exists.");
        }
    }

    public static int ifAdminExists(Admin admin) {
        try {
            List<String> lines = Files.readAllLines(Paths.admins_path);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.equals("")) continue;

                Pattern r = Pattern.compile(Regexes.user_entry_regex);
                Matcher m = r.matcher(line);
                if (m.find()) {
                    String line_username = m.group("username");
                    String line_email = m.group("email");
                    if (line_username.equals(admin.getUsername()) || line_email.equals(admin.getEmail())) {
                        return 1;
                    }
                } else {
                    PageElements.printConsoleMessage("Wrong entry format or Regex!");
                    return -1;
                }
            }
            return 0;
        } catch (IOException exception) {
            PageElements.printConsoleMessage("Error: Invalid Path! Can't be checked if the user exists.");
            return -1;
        }
    }

    public static boolean addAdmin (User user) {
        try {
            Files.writeString(Paths.admins_path, user.toString(), StandardOpenOption.APPEND);
            PageElements.printConsoleMessage("Admin successfully registered!");
            return true;
        } catch (IOException exception) {
            PageElements.printConsoleMessage("Error: Invalid Path! models.User is not saved to the database.");
            return false;
        }
    }

    public static Admin checkAdminCredentials(String username, String password) {
        try {
            List<String> lines = Files.readAllLines(Paths.admins_path);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.equals("")) continue;

                Pattern r = Pattern.compile(Regexes.user_entry_regex);
                Matcher m = r.matcher(line);

                if (m.find()) {
                    String line_username = m.group("username");
                    String line_email = m.group("email");
                    String line_password = m.group("password");

                    if ((line_username.equals(username) || line_email.equals(username)) && line_password.equals(password)) {
                        String f_name = m.group("fname");
                        String l_name = m.group("lname");
                        Admin loggedUser = new Admin(f_name, l_name, line_username, line_email, line_password);
                        return loggedUser;
                    }
                } else {
                    PageElements.printConsoleMessage("Wrong entry format or Regex!");
                    return null;
                }
            }
            PageElements.printConsoleMessage("Wrong credentials!");
            return null;
        } catch (IOException exception) {
            PageElements.printConsoleMessage("Error: Invalid Path! Can't be checked if the user exists.");
            return null;
        }
    }
}
