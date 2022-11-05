package data_managers;

import models.movies.Actor;
import models.movies.Movie;
import pages.PageElements;

import java.io.*;
import java.util.ArrayList;

public class ActorManager {

    private static final String ACTOR_PATH = "src/data/actors.txt";

    public static ArrayList<Actor> readActors () {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ACTOR_PATH));
            ArrayList<Actor> actors = (ArrayList<Actor>) ois.readObject();
            ois.close();
            return actors;
        } catch (FileNotFoundException e) {
            PageElements.printConsoleMessage("Error: Invalid Path! Actors can't be read!");
        } catch (EOFException e) {
            PageElements.printConsoleMessage("File was empty. This is the first item in it.");
        } catch (IOException | ClassNotFoundException e) {
            PageElements.printConsoleMessage("Error!");
        }
        return new ArrayList<Actor>();
    }

    public static boolean writeActor (Actor actor) {
        File file = new File(ACTOR_PATH);
        ArrayList<Actor> allActors = readActors();
        allActors.add(actor);
        if(file.exists()) file.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ACTOR_PATH));
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

    public static Actor getActor (String fName, String lName) {
        ArrayList<Actor> allActors = readActors();
        for (int i = 0; i < allActors.size(); i++) {
            Actor currentActor = allActors.get(i);
            if (currentActor.getfName().equals(fName) && currentActor.getlName().equals(lName)) {
                return currentActor;
            }
        }
        return null;
    }

    public static boolean updateActor (Actor actor) {
        ArrayList<Actor> allActors = readActors();
        Actor actorToUpdate = getActor(actor.getfName(), actor.getlName());
        if (actorToUpdate == null) {
            PageElements.printConsoleMessage("No such actor!");
            return false;
        } else {
            allActors.remove(actorToUpdate);
            allActors.add(actor);
            File file = new File(ACTOR_PATH);
            if(file.exists()) file.delete();
        }
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ACTOR_PATH));
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

    public static boolean deleteActor (Actor actor) {
        File file = new File(ACTOR_PATH);
        ArrayList<Actor> allActors = readActors();
        Actor actorToUpdate = getActor(actor.getfName(), actor.getlName());
        if (actorToUpdate == null) {
            PageElements.printConsoleMessage("No such actor!");
            return false;
        } else {
            allActors.remove(actorToUpdate);
        }
        if(file.exists()) file.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ACTOR_PATH));
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
