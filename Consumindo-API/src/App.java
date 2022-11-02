import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import entities.Movies;
import services.AttributesEnum;
import services.HTMLGenerator;
public class App {
    public static void main(String[] args){
        //Get uri
        String imdb250Movies = "https://imdb-api.com/en/API/Top250Movies/k_f360sfvm";
        var link = URI.create(imdb250Movies);

        // create a client
        var client = HttpClient.newHttpClient();

        // make a request http
        var request = HttpRequest.newBuilder()
            .uri(link)
            .build();

        try{
            //use the client to send the request and print the body
            var response = client.send(request, BodyHandlers.ofString());
        
            //Parse Json
            String jsonMoviesFull = response.body();
            List<Movies> movies = parseJsonMovies(jsonMoviesFull);

            //Write the images in the file 
            FileWriter file = new FileWriter("index.html");
            HTMLGenerator htmlGenerator = new HTMLGenerator(file);
            htmlGenerator.generate(movies);   
        
        } catch (Exception e) {
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
