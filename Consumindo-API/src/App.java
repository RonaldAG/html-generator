import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import services.AttributesEnum;
public class App {
    public static void main(String[] args) throws Exception {
        //Get uri
        String imdb250Movies = "https://imdb-api.com/en/API/Top250Movies/k_f360sfvm";
        var link = URI.create(imdb250Movies);

        // create a client
        var client = HttpClient.newHttpClient();

        // make a request http
        var request = HttpRequest.newBuilder()
            .uri(link)
            .build();

        //use the client to send the request and print the body
        var response = client.send(request, BodyHandlers.ofString());

        //Parse Json
        String jsonMoviesFull = response.body().replace("\"", " ");
        String[] jsonMovies = jsonMoviesFull.split("\\},\\{"); // array which every position it's a movie

        List<Movies> movies = moviesList(jsonMovies);

        movies.forEach( movie -> System.out.println(movie.title().toString()));
    }

    //Parse method
    public static List<Movies> moviesList(String[] jsonMovies){
        List<Movies> listMovies = new ArrayList<>();
        String[] attributes;
        Movies movie;

        for(int i =0; i < jsonMovies.length; i++){
            attributes = jsonMovies[i].split(",");
            movie = new Movies(attributes[AttributesEnum.TITLE.getPosition()], 
                            attributes[AttributesEnum.IMAGE.getPosition()], 
                            attributes[AttributesEnum.IMDB_RANKING.getPosition()], 
                            attributes[AttributesEnum.YEAR.getPosition()]);

            listMovies.add(movie);
        }

        return listMovies;
    }
}
