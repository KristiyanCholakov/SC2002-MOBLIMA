package pages;

public class PageElements {
    public static void printHeader() {
        System.out.println("\n------------------------------------------------------------");
        System.out.println("                     WELCOME TO MOBLIMA                     ");
        System.out.println("------------------------------------------------------------\n");
    }

    public static void printLine() {
        System.out.println("\n------------------------------------------------------------\n");
    }

    public static void printConsoleMessage(String message) {
        printLine();
        System.out.println("Console Message:");
        System.out.println(message);
        printLine();
    }
}
