import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataManager {
    private static final Path users_path = Paths.get("./data/users.txt");
    private static final Path reviews_path = Paths.get("./data/users.txt");
    private static final Path movies_path = Paths.get("./data/users.txt");

    private static final String get_data_entry_regex = "^Username=(?<username>.+), Email=(?<email>.+), Password=(?<password>.+), F_Name=(?<fname>.+), L_Name=(?<lname>.+)$";

    public static boolean addUser (User user) {
        try {
            Files.writeString(users_path, user.toString(), StandardCharsets.UTF_8);
            return true;
        } catch (IOException exception) {
            System.out.println("Error: Invalid Path! User is not saved to the database.");
            return false;
        }
    }

    public static int ifUserExists(User user) {
        try {
            List<String> lines = Files.readAllLines(users_path);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                Pattern r = Pattern.compile(get_data_entry_regex);
                Matcher m = r.matcher(line);

                String line_username = m.group("username");
                String line_email = m.group("email");

                if (line_username.equals(user.getUsername()) || line_email.equals(user.getEmail())) {
                    return 1;
                }
            }
            return 0;
        } catch (IOException exception) {
            System.out.println("Error: Invalid Path! Can't be checked if the user exists.");
            return -1;
        }
    }

    public static int checkCredentials(String username, String password) {
        try {
            List<String> lines = Files.readAllLines(users_path);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);

                Pattern r = Pattern.compile(get_data_entry_regex);
                Matcher m = r.matcher(line);

                String line_username = m.group("username");
                String line_email = m.group("email");
                String line_password = m.group("password");

                if ((line_username.equals(username) || line_email.equals(username)) && line_password.equals(password)) {
                    return 1;
                }
            }
            return 0;
        } catch (IOException exception) {
            System.out.println("Error: Invalid Path! Can't be checked if the user exists.");
            return -1;
        }
    }

}
