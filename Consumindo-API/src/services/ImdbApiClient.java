package services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class ImdbApiClient {
    
    private static String linkApi =  "https://imdb-api.com/en/API/Top250Movies/";
    private static URI UriApi;
    private static HttpClient client;
    private static HttpRequest request;
    private String key;


    public ImdbApiClient(String key){
        this.key = key;
    }

    public String getBody() throws IOException, InterruptedException{
        // create a URI
        String fullLink = linkApi + key;
        UriApi = URI.create(fullLink);

        // create a client
        client = HttpClient.newHttpClient();

        // make a request http
        request = HttpRequest.newBuilder()
        .uri(UriApi)
        .build();

        // get response and return the body
        var response = client.send(request, BodyHandlers.ofString());
        return response.body();
    }

}
