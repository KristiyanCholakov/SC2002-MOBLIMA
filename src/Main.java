import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String state = "loginOptions";
        User current_user = null;
        while (true) {
            MOBLIMA.printHeader();

            switch (state) {
                case "loginOptions":
                    state = MOBLIMA.selectLoginOption();
                    break;
                case "register":
                    current_user = MOBLIMA.register();
                    if (current_user != null) state = "login";
                    else state = "loginOptions";
                    break;
                case "login":
                    current_user = MOBLIMA.login();
                    if (current_user != null) state = "";
                    else state = "loginOptions";
                    break;
            }

        }
    }
}