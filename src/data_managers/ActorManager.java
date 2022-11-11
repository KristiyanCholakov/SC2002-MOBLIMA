package data_managers;

import models.movies.Actor;
import models.movies.Movie;
import pages.PageElements;

import java.io.*;
import java.util.ArrayList;

/**
 * The ActorManager class takes care of interacting with the file where the actors are stored.
 * It performs writing, updating, reading and searching in the actors' storage.
 *
 * @author Kristiyan Cholakov (KrisCholakov02)
 * @version 10/11/22
 */
public class ActorManager {

    /**
     * The ACTORS_PATH constant points to the file where actors are stored.
     */
    private static final String ACTORS_PATH = "src/data/actors.txt";

    /**
     * The readActors function reads all actors records from the actors' storage.
     *
     * @return An array list of the actor records from the storage. Empty array list if not any.
     */
    public static ArrayList<Actor> readActors () {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ACTORS_PATH));
            ArrayList<Actor> actors = (ArrayList<Actor>) ois.readObject();
            ois.close();
            return actors;
        } catch (FileNotFoundException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Actors can't be read!");
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            PageElements.printConsoleMessage("Error!");
        }
        return new ArrayList<Actor>();
    }

    /**
     * The writeActor function adds an actor record to the actors' storage.
     *
     * @param actor The actor record to be written in the storage.
     * @return true if the actor record is added successfully. false if writing failed.
     */
    public static boolean writeActor (Actor actor) {
        File file = new File(ACTORS_PATH);
        ArrayList<Actor> allActors = readActors();
        allActors.add(actor);
        if(file.exists()) file.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ACTORS_PATH));
            out.writeObject(allActors);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("Actor Added!");
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Actor is not saved to the database.");
            return false;
        }
    }

    /**
     * The getActor function searches for an actor with the same first and last name in the storage and providing their
     * record.
     *
     * @param fName The first name of an actor that we search
     * @param lName The last name of an actor that we search
     * @return The record of the actor with the same first and last name. null if an actor with these names does not exist.
     */
    public static Actor getActor (String fName, String lName) {
        ArrayList<Actor> allActors = readActors();
        for (int i = 0; i < allActors.size(); i++) {
            Actor currentActor = allActors.get(i);
            if (currentActor.getFName().equals(fName) && currentActor.getLName().equals(lName)) {
                return currentActor;
            }
        }
        return null;
    }

    /**
     * The updateActor function searches if the provided actor record exists and if so updates their entry.
     *
     * @param actor The actor record to be updated in the storage.
     * @return true if the actor record is updated successfully. false if updating failed.
     */
    public static boolean updateActor (Actor actor) {
        ArrayList<Actor> allActors = readActors();
        Actor actorToUpdate = getActor(actor.getFName(), actor.getLName());
        if (actorToUpdate == null) {
            PageElements.printConsoleMessage("No such actor!");
            return false;
        } else {
            allActors.remove(actorToUpdate);
            allActors.add(actor);
            File file = new File(ACTORS_PATH);
            if(file.exists()) file.delete();
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ACTORS_PATH));
            out.writeObject(allActors);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("Actor Updated!");
            ArrayList<Movie> movies = MovieManager.readMovies();
            for (int i = 0; i < movies.size(); i++) {
                Movie movie = movies.get(i);
                for (int j = 0; j < movie.getCast().size(); j++) {
                    Actor a = movie.getCast().get(j);;
                    if (a.equals(actor)) {
                        movie.getCast().remove(a);
                        movie.getCast().add(actor);
                        MovieManager.updateMovie(movie);
                        break;
                    }
                }
            }
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Director is not updated to the database.");
            return false;
        }
    }

    /**
     * The deleteActor function searches if the provided actor record exists and if so deletes their entry.
     *
     * @param actor The actor record to be deleted in the storage.
     * @return true if the actor record is deleted successfully. false if deletion failed.
     */
    public static boolean deleteActor (Actor actor) {
        File file = new File(ACTORS_PATH);
        ArrayList<Actor> allActors = readActors();
        Actor actorToUpdate = getActor(actor.getFName(), actor.getLName());
        if (actorToUpdate == null) {
            PageElements.printConsoleMessage("No such actor!");
            return false;
        } else {
            allActors.remove(actorToUpdate);
        }
        if(file.exists()) file.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ACTORS_PATH));
            out.writeObject(allActors);
            out.flush();
            out.close();
            PageElements.printConsoleMessage("Actor Deleted!");
            return true;
        } catch (IOException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Director is not deleted from the database.");
            return false;
        }
    }
}
