package pages;

import constants.Regexes;
import data_managers.ActorManager;
import data_managers.DirectorManager;
import data_managers.MovieManager;
import models.movies.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieEditorPages {
    public static void movieEditorPage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();
        boolean running = true;
        while (running) {
            System.out.println("Select the action you want:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Add\n" +
                    "       2 - Edit\n" +
                    "       3 - Delete\n" +
                    "       4 - Back to Editor Portal");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addMoviePage();
                    break;
                case 2:
                    editMoviePage();
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

    public static void addMoviePage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();

        System.out.print("Title: ");
        String title = scanner.nextLine();
        if (MovieManager.getMovie(title) != null) {
            PageElements.printConsoleMessage("Movie already exits!");
            return;
        }
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Duration (hh:mm:ss): ");
        String durationStr = scanner.nextLine();
        if (!durationStr.matches(Regexes.duration_regex)) {
            PageElements.printConsoleMessage("Error: The duration is not in wanted format.");
            return;
        }
        LocalTime duration = LocalTime.parse(durationStr);
        System.out.print("Status (CS, P, NS): ");

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
            default:
                PageElements.printConsoleMessage("Error: The status is not in wanted format.");
                return;

        }
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
        double rating = 0.0;
        ArrayList<Review> reviews = new ArrayList<Review>();
        Movie movie = new Movie(title, genre, duration, status, type, restriction, synopsis, director, actors, rating, reviews);
        MovieManager.writeMovie(movie);
    }

    public static void editMoviePage() {
        Scanner scanner = new Scanner(System.in);
        PageElements.printHeader();

        System.out.print("Title: ");
        String title = scanner.nextLine();
        Movie movie = MovieManager.getMovie(title);
        if (movie == null) {
            PageElements.printConsoleMessage("Movie does not exits!");
            return;
        }
        boolean running = true;
        while (running) {
            System.out.println("Select what you want to edit:\n" +
                    "(Type the number of the choice)\n" +
                    "       1 - Genre \n" +
                    "       2 - Duration\n" +
                    "       3 - Status\n" +
                    "       4 - Type\n" +
                    "       5 - Director\n" +
                    "       6 - Actors\n" +
                    "       7 - Back to Movie Editor");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Genre: ");
                    String genre = scanner.nextLine();
                    movie.setGenre(genre);
                    MovieManager.updateMovie(movie);
                    break;
                case 2:
                    System.out.print("Duration (hh:mm:ss): ");
                    String durationStr = scanner.nextLine();
                    if (!durationStr.matches(Regexes.duration_regex)) {
                        PageElements.printConsoleMessage("Error: The duration is not in wanted format.");
                        return;
                    }
                    LocalTime duration = LocalTime.parse(durationStr);
                    movie.setDuration(duration);
                    break;
                case 3:
                    System.out.print("Status (CS, P, NS): ");
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
                        default:
                            PageElements.printConsoleMessage("Error: The status is not in wanted format.");
                            return;

                    }
                    movie.setStatus(status);
                    break;
                case 4:
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
                case 5:
                    Director director = movie.getDirector();
                    director.setNumberOfMovies(director.getNumberOfMovies()-1);
                    if (director.getNumberOfMovies() == 0) DirectorManager.deleteDirector(director);
                    else DirectorManager.updateDirector(director);
                    System.out.print("Director: ");
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
                    break;
                case 6:
                    ArrayList<Actor> actors = movie.getCast();
                    for (int i = 0; i < actors.size(); i++) {
                        Actor actor = actors.get(i);
                        actor.setNumberOfMovies(actor.getNumberOfMovies()-1);
                        if (actor.getNumberOfMovies() == 0) ActorManager.deleteActor(actor);
                        else ActorManager.updateActor(actor);
                    }
                    System.out.print("Number of Main Actors: ");
                    int n = scanner.nextInt();
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
                case 7:
                    running = false;
                    break;
                default:
                    PageElements.printConsoleMessage("Invalid Choice!");
            }
            MovieManager.updateMovie(movie);
        }
    }

    public static void deleteMoviePage() {

    }
}
