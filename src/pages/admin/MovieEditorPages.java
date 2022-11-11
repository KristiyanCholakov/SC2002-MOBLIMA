package pages.admin;

import constants.Regexes;
import data_managers.ActorManager;
import data_managers.DirectorManager;
import data_managers.MovieManager;
import models.movies.*;
import pages.PageElements;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  The MovieEditorPages class holds the functionality connected with the movie editing pages for the admin.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class MovieEditorPages {

    /**
     * The movieEditorPage method gives the opportunity to choose what the admin wants to do.
     * If the choice is invalid asks for another. If it is valid redirects to the corresponding page.
     */
    public static void movieEditorPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        boolean running = true;
        while (running) {
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Add Movie\n" +
                    "       2 - Edit Movie\n" +
                    "       3 - Delete Movie\n" +
                    "       4 - Back to Editor Portal");
            System.out.print("Choice: ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                choice = -1;
                PageElements.printConsoleMessage("You have to enter a number!");
            }
            scanner.nextLine();
            switch (choice) {
                case 1:
                    try {
                        addMoviePage();
                    }  catch (InputMismatchException e ) {
                        PageElements.printConsoleMessage("You have to enter a valid input!");
                    }
                    break;
                case 2:
                    try {
                        editMoviePage();
                    }  catch (InputMismatchException e ) {
                        PageElements.printConsoleMessage("You have to enter a valid input!");
                    }
                    break;
                case 3:
                    deleteMoviePage();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
        }
    }

    /**
     * The addMoviePage is used for adding new movie records to the storage.
     * If all the required fields are filled correctly the record will be created.
     * Otherwise, the admin is redirected to the previous page.
     */
    public static void addMoviePage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();

        System.out.print("Title: ");
        String title = scanner.nextLine();
        if (MovieManager.getMovie(title) != null) {
            PageElements.printConsoleMessage("Movie already exists!");
            return;
        }
        System.out.print("Genre (if many, separate with ', '): ");
        String genre = scanner.nextLine();
        System.out.print("Duration (hh:mm): ");
        String durationStr = scanner.nextLine() + ":00";
        if (!durationStr.matches(Regexes.DURATION_REGEX)) {
            PageElements.printConsoleMessage("Error: The duration is not in wanted format.");
            return;
        }
        LocalTime duration = LocalTime.parse(durationStr);
        System.out.print("Status (CS, P, NS, ED): ");
        String statusStr = scanner.nextLine();
        MovieEnums.MovieStatus status;
        switch (statusStr) {
            case "CS":
                status = MovieEnums.MovieStatus.COMING_SOON;
                break;
            case "P":
                status = MovieEnums.MovieStatus.PREVIEW;
                break;
            case "NS":
                status = MovieEnums.MovieStatus.NOW_SHOWING;
                break;
            case "ED":
                status = MovieEnums.MovieStatus.END_SHOWING;
                break;
            default:
                PageElements.printConsoleMessage("Error: The status is not in wanted format.");
                return;

        }
        System.out.print("Is the movie a blockbuster (true, false): ");
        boolean isBlockbuster = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("Type (2D, 3D, 4DX, IMAX): ");
        String typeStr = scanner.nextLine();
        MovieEnums.MovieType type;
        switch (typeStr) {
            case "2D":
                type = MovieEnums.MovieType.TWO_D;
                break;
            case "3D":
                type = MovieEnums.MovieType.THREE_D;
                break;
            case "4DX":
                type = MovieEnums.MovieType.FOUR_DX;
                break;
            case "IMAX":
                type = MovieEnums.MovieType.IMAX;
                break;
            default:
                PageElements.printConsoleMessage("Error: The type is not in wanted format.");
                return;
        }
        System.out.print("Restriction (G, PG, PG13, NC16, M18, R21): ");
        String restrictionStr = scanner.nextLine();
        MovieEnums.MovieRestriction restriction;
        switch (restrictionStr) {
            case "G":
                restriction = MovieEnums.MovieRestriction.G;
                break;
            case "PG":
                restriction = MovieEnums.MovieRestriction.PG;
                break;
            case "PG13":
                restriction = MovieEnums.MovieRestriction.PG13;
                break;
            case "NC16":
                restriction = MovieEnums.MovieRestriction.NC16;
                break;
            case "M18":
                restriction = MovieEnums.MovieRestriction.M18;
                break;
            case "R21":
                restriction = MovieEnums.MovieRestriction.R21;
                break;
            default:
                PageElements.printConsoleMessage("Error: The restriction is not in wanted format.");
                return;
        }
        System.out.print("Synopsis: ");
        String synopsis = scanner.nextLine();
        System.out.println("Director: ");
        System.out.print("***First Name: ");
        String directorFName = scanner.nextLine();
        System.out.print("***Last Name: ");
        String directorLName = scanner.nextLine();
        Director director = DirectorManager.getDirector(directorFName, directorLName);
        if (director == null) {
            director = new Director(directorFName, directorLName, 1);
            DirectorManager.writeDirector(director);
        } else {
            director.setNumberOfMovies(director.getNumberOfMovies() + 1);
            DirectorManager.updateDirector(director);
        }
        System.out.print("Number of Main Actors: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Actor> actors = new ArrayList<Actor>();
        for (int i = 0; i < n; i++) {
            System.out.println("Actor: ");
            System.out.print("***First Name: ");
            String actorFName = scanner.nextLine();
            System.out.print("***Last Name: ");
            String actorLName = scanner.nextLine();
            Actor actor = ActorManager.getActor(actorFName, actorLName);
            if (actor == null) {
                System.out.print("***Number of Oscars: ");
                int numberOfOscars = scanner.nextInt();
                scanner.nextLine();
                actor = new Actor(actorFName, actorLName, 1, numberOfOscars);
                ActorManager.writeActor(actor);
            } else {
                actor.setNumberOfMovies(actor.getNumberOfMovies() + 1);
                ActorManager.updateActor(actor);
            }
            actors.add(actor);
        }
        ArrayList<String> genreList = new ArrayList<>();
        Collections.addAll(genreList, genre.split(", "));
        Movie movie = new Movie(title, genreList, duration, status,isBlockbuster, type, restriction, synopsis, director, actors);
        MovieManager.writeMovie(movie);
    }

    /**
     * The editMoviePage method is used for editing already existing record in the storage based on the movie title.
     * If the record doesn't exist, the admin is redirected to the previous page.
     * If thw record exists and later all the required input fields are filled according to the requirement, the movie
     * record is updated with the new data.
     */
    public static void editMoviePage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();

        System.out.print("Title: ");
        String title = scanner.nextLine();
        Movie movie = MovieManager.getMovie(title);
        if (movie == null) {
            PageElements.printConsoleMessage("Movie does not exists!");
            return;
        }
        boolean running = true;
        while (running) {
            PageElements.printHeader();
            System.out.println("Select what you want to edit:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Genre \n" +
                    "       2 - Duration\n" +
                    "       3 - Status\n" +
                    "       4 - Blockbuster\n" +
                    "       5 - Type\n" +
                    "       6 - Director\n" +
                    "       7 - Actors\n" +
                    "       8 - Synopsis\n" +
                    "       9 - Restriction\n" +
                    "       10 - Back to Movie Editor");
            System.out.print("Choice: ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                choice = -1;
                PageElements.printConsoleMessage("You have to enter a number!");
            }
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Genre (if many, separate with ', '): ");
                    String genre = scanner.nextLine();
                    ArrayList<String> genreList = new ArrayList<>();
                    Collections.addAll(genreList, genre.split(", "));
                    movie.setGenre(genreList);
                    MovieManager.updateMovie(movie);
                    break;
                case 2:
                    System.out.print("Duration (hh:mm): ");
                    String durationStr = scanner.nextLine() + ":00";
                    if (!durationStr.matches(Regexes.DURATION_REGEX)) {
                        PageElements.printConsoleMessage("Error: The duration is not in wanted format.");
                        return;
                    }
                    LocalTime duration = LocalTime.parse(durationStr);
                    movie.setDuration(duration);
                    break;
                case 3:
                    System.out.print("Status (CS, P, NS, ED): ");
                    String statusStr = scanner.nextLine();
                    MovieEnums.MovieStatus status;
                    switch (statusStr) {
                        case "CS":
                            status = MovieEnums.MovieStatus.COMING_SOON;
                            break;
                        case "P":
                            status = MovieEnums.MovieStatus.PREVIEW;
                            break;
                        case "NS":
                            status = MovieEnums.MovieStatus.NOW_SHOWING;
                            break;
                        case "ED":
                            status = MovieEnums.MovieStatus.END_SHOWING;
                            break;
                        default:
                            PageElements.printConsoleMessage("Error: The status is not in wanted format.");
                            return;

                    }
                    movie.setStatus(status);
                    break;
                case 4:
                    System.out.print("Is the movie a blockbuster (true, false): ");
                    boolean isBlockbuster = scanner.nextBoolean();
                    movie.setBlockbuster(isBlockbuster);
                    break;
                case 5:
                    System.out.print("Type (2D, 3D, 4DX, IMAX): ");
                    String typeStr = scanner.nextLine();
                    MovieEnums.MovieType type;
                    switch (typeStr) {
                        case "2D":
                            type = MovieEnums.MovieType.TWO_D;
                            break;
                        case "3D":
                            type = MovieEnums.MovieType.THREE_D;
                            break;
                        case "4DX":
                            type = MovieEnums.MovieType.FOUR_DX;
                            break;
                        case "IMAX":
                            type = MovieEnums.MovieType.IMAX;
                            break;
                        default:
                            PageElements.printConsoleMessage("Error: The type is not in wanted format.");
                            return;
                    }
                    movie.setType(type);
                    break;
                case 6:
                    Director director = movie.getDirector();
                    director.setNumberOfMovies(director.getNumberOfMovies()-1);
                    if (director.getNumberOfMovies() == 0) DirectorManager.deleteDirector(director);
                    else DirectorManager.updateDirector(director);
                    System.out.println("Director: ");
                    System.out.print("***First Name: ");
                    String directorFName = scanner.nextLine();
                    System.out.print("***Last Name: ");
                    String directorLName = scanner.nextLine();
                    Director newDirector = DirectorManager.getDirector(directorFName, directorLName);
                    if (newDirector == null) {
                        newDirector = new Director(directorFName, directorLName, 1);
                        DirectorManager.writeDirector(newDirector);
                    } else {
                        newDirector.setNumberOfMovies(newDirector.getNumberOfMovies() + 1);
                        DirectorManager.updateDirector(newDirector);
                    }
                    movie.setDirector(newDirector);
                    break;
                case 7:
                    ArrayList<Actor> actors = movie.getCast();
                    for (int i = 0; i < actors.size(); i++) {
                        Actor actor = actors.get(i);
                        actor.setNumberOfMovies(actor.getNumberOfMovies()-1);
                        if (actor.getNumberOfMovies() == 0) ActorManager.deleteActor(actor);
                        else ActorManager.updateActor(actor);
                    }
                    System.out.print("Number of Main Actors: ");
                    int n = scanner.nextInt();
                    scanner.nextLine();
                    ArrayList<Actor> newActors = new ArrayList<Actor>();
                    for (int i = 0; i < n; i++) {
                        System.out.print("Actor: ");
                        System.out.print("***First Name: ");
                        String actorFName = scanner.nextLine();
                        System.out.print("***Last Name: ");
                        String actorLName = scanner.nextLine();
                        Actor actor = ActorManager.getActor(actorFName, actorLName);
                        if (actor == null) {
                            System.out.print("***Number of Oscars: ");
                            int numberOfOscars = scanner.nextInt();
                            scanner.nextLine();
                            actor = new Actor(actorFName, actorLName, 1, numberOfOscars);
                            ActorManager.writeActor(actor);
                        } else {
                            actor.setNumberOfMovies(actor.getNumberOfMovies() + 1);
                            ActorManager.updateActor(actor);
                        }
                        newActors.add(actor);
                    }
                    movie.setCast(newActors);
                    break;
                case 8:
                    System.out.println("Enter the new synopsis:");
                    String synopsis = scanner.nextLine();
                    movie.setSynopsis(synopsis);
                    break;
                case 9:
                    System.out.print("Restriction (G, PG, PG13, NC16, M18, R21): ");
                    String restrictionStr = scanner.nextLine();
                    MovieEnums.MovieRestriction restriction;
                    switch (restrictionStr) {
                        case "G":
                            restriction = MovieEnums.MovieRestriction.G;
                            break;
                        case "PG":
                            restriction = MovieEnums.MovieRestriction.PG;
                            break;
                        case "PG13":
                            restriction = MovieEnums.MovieRestriction.PG13;
                            break;
                        case "NC16":
                            restriction = MovieEnums.MovieRestriction.NC16;
                            break;
                        case "M18":
                            restriction = MovieEnums.MovieRestriction.M18;
                            break;
                        case "R21":
                            restriction = MovieEnums.MovieRestriction.R21;
                            break;
                        default:
                            PageElements.printConsoleMessage("Error: The restriction is not in wanted format.");
                            return;
                    }
                    movie.setRestriction(restriction);
                    break;
                case 10:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
            MovieManager.updateMovie(movie);
        }
    }

    /**
     * The deleteMoviePage method is used for deleting an already existing movie record based on its movie title.
     * If the movie doesn't exist, redirects to the previous page.
     * If the movie exists, the movie record will be removed from the storage.
     */
    public static void deleteMoviePage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();

        System.out.println("Enter the title of the movie to be deleted!");
        String title = scanner.nextLine();
        Movie movie = MovieManager.getMovie(title);
        if (movie == null) {
            PageElements.printConsoleMessage("The movie doesn't exist!");
            return;
        }
        DirectorManager.deleteDirector(movie.getDirector());
        for (int i = 0; i < movie.getCast().size(); i++) {
            ActorManager.deleteActor(movie.getCast().get(i));
        }
        MovieManager.deleteMovie(movie);
    }
}
