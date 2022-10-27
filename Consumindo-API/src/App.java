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
        String jsonMoviesFull = response.body();
        jsonMoviesFull = jsonMoviesFull.replace("\"", " ");
        String[] jsonMovies = jsonMoviesFull.split("\\},\\{");
        
        List<String> titlesList = parseTitles(jsonMovies);
        List<String> yearsList = parseYear(jsonMovies);

        titlesList.forEach(System.out::println);
        yearsList.forEach(System.out::println);
    }

    //PARSE METHODS -------------------------------------------
    public static List<String> parseTitles(String[] json){
        List<String> titlesList = new ArrayList<>();
        String[] atributos = new String[8]; //total of attributes
        for(int i = 0; i< json.length; i++){
            atributos = json[i].split(",");
            titlesList.add(atributos[AttributesEnum.TITLE.getPosition()]);
        }
        return titlesList;
    }
    
    public static List<String> parseYear(String[] json){
        List<String> titlesList = new ArrayList<>();
        String[] atributos = new String[8]; //total of attributes
        for(int i = 0; i< json.length; i++){
            atributos = json[i].split(",");
            titlesList.add(atributos[AttributesEnum.YEAR.getPosition()]);
        }
        return titlesList;
    }
}
