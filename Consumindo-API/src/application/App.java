package application;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import entities.Movies;
import services.HTMLGenerator;
import services.YearCompare;
import services.imdb.ImdbApiClient;
import services.imdb.ImdbMovieJsonParser;
public class App {
    public static void main(String[] args){
        //Get uri
        String apiKey = "k_f360sfvm";
        String json;
        try {
            // create a http conection
            json = new ImdbApiClient(apiKey).getBody();

            //parse json 
            List<Movies> movies = new ImdbMovieJsonParser(json).parse();

            // Sort the content by title
            Collections.sort(movies, Comparator.reverseOrder());

            // Sort the content by year
            // Collections.sort(movies, new YearCompare());

            //Write the content in the file 
            FileWriter file = new FileWriter("index.html");
            new HTMLGenerator(file).generate(movies);   
            file.close();
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
