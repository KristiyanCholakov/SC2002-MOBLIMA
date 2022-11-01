package pages;

import java.util.Scanner;

public class CineplexEditorPages {
    public static void cinemaEditorPage() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Add cinemas\n" +
                    "       2 - Add locations\n" +
                    "       3 - Add screens\n" +
                    "       4 - Display cinema list\n" +
                    "       5 - Back to Editor Portal");
        }

    }
}
