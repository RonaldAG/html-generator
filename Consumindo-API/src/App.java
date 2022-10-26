import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
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
        String[] jsonMovies = jsonMoviesFull.split("\\},\\{");
        System.out.println(jsonMovies[2]);
    }
}
