package pages;

/**
 *  The PageElements class holds design of common elements of the program.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class PageElements {

    /**
     * The printHeader method prints the header of the program.
     */
    public static void printHeader() {
        System.out.println("\n------------------------------------------------------------");
        System.out.println("                     WELCOME TO MOBLIMA                     ");
        System.out.println("------------------------------------------------------------\n");
    }

    /**
     * The printLine method prints a line.
     */
    public static void printLine() {
        System.out.println("\n------------------------------------------------------------\n");
    }

    /**
     * The printConsoleMessage method prints a specific design console message.
     * @param message The message to be printed.
     */
    public static void printConsoleMessage(String message) {
        printLine();
        System.out.println("Console Message:");
        System.out.println(message);
        printLine();
    }
}
