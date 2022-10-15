import java.util.Collections;
import java.util.List;

public class Movie {
    private String name;
    private List<String> genre;
    private String duration;
    private String status;
    private String synopsis;
    private Director director;
    private List<Actor> cast;
    private double rating;
    private List<Review> reviews;

    public Movie(String name, List<String> genre, String duration, String status, String synopsis, Director director, List<Actor> cast) {
        this.name = name;
        this.genre = genre;
        this.duration = duration;
        this.status = status;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.rating = 0;
        this.reviews = List.of();
    }


}
