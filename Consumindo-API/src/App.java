import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import entities.Movies;
import services.AttributesEnum;
import services.HTMLGenerator;
import services.ImdbApiClient;
public class App {
    public static void main(String[] args){
        //Get uri
        String apiKey = "k_f360sfvm";
        String json;
        try {
            json = new ImdbApiClient(apiKey).getBody();
            List<Movies> movies = parseJsonMovies(json);

            //Write the images in the file 
            FileWriter file = new FileWriter("index.html");
            HTMLGenerator htmlGenerator = new HTMLGenerator(file);
            htmlGenerator.generate(movies);   
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Parse method
    private static List<Movies> parseJsonMovies(String body){
        String[] jsonMovies = body.split("\\},\\{");
        List<Movies> movies = moviesList(jsonMovies);
        return movies;
    }

    private static List<Movies> moviesList(String[] jsonMovies){
        List<Movies> listMovies = new ArrayList<>();
        String[] attributes;
        Movies movie;

        for(int i =0; i < jsonMovies.length; i++){
            attributes = jsonMovies[i].split("\",\"");

            movie = new Movies(attributes[AttributesEnum.TITLE.getPosition()].substring(8), 
                            attributes[AttributesEnum.IMAGE.getPosition()].substring(8), 
                            attributes[AttributesEnum.IMDB_RANKING.getPosition()].substring(13), 
                            attributes[AttributesEnum.YEAR.getPosition()].substring(7));

            listMovies.add(movie);
        }

        return listMovies;
    }
}
