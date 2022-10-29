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
                    current_user = null;
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
                case "adminLogin":
                    current_user = MOBLIMA.adminLogin();
                    if (current_user != null) state = "adminPortal";
                    else state = "loginOptions";
                    break;
                case "masterLogin":
                    if (MOBLIMA.masterLogin()) state = "masterPortal";
                    else state = "loginOptions";
                    break;
                case "masterPortal":
                    state = MOBLIMA.masterPortal();
                    break;
                case "createAdmin":
                    MOBLIMA.createAdmin();
                    state = "masterPortal";
                    break;
                case "showAdmins":
                    MOBLIMA.showAdmins();
                    state = "masterPortal";
                    break;
                case "adminPortal":
                    state = MOBLIMA.adminPortal();
                    break;
            }

        }
    }
}