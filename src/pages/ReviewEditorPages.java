package pages;

import models.accounts.User;
import models.accounts.Admin;
import models.movies.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



public class ReviewEditorPages {
    //protected ArrayList<Review> reviews;
    private static Movie movie;
    static Scanner scanner = new Scanner(System.in);
    public static void reviewEditorPage() {
        ArrayList<Review> reviews  = movie.getReviews();
        boolean running = true;
        while (running) {
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - View Reviews\n" +
                    "       2 - Delete Reviews\n" +
                    "       3 - Back to Editor Portal" );

            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewReviews(reviews);
                    break;
                case 2:
                    deleteReviews(reviews);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }

    public static void viewReviews(ArrayList<Review> reviews) {
            System.out.println(reviews);
    }
    public static void deleteReviews(ArrayList<Review> reviews){
        String username;
        int i;
        System.out.println("Choose the username of the reviewer you want to remove: ");
        username = scanner.nextLine();

        for(i=0; i< reviews.size();i++){
            if(reviews.get(i).getPublisher().getUsername() == username){
                reviews.remove(i); // assuming every user only leaves 1 review for that movie, else can just remove the break statement
                break;
            }
        }
    }
}
