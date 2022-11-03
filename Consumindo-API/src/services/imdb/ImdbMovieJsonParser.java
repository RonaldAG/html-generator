package services.imdb;

import java.util.ArrayList;
import java.util.List;

import entities.Movies;
import services.AttributesEnum;
import services.JsonParser;

public class ImdbMovieJsonParser implements JsonParser{
    private String json;

    public ImdbMovieJsonParser(String json){
        this.json = json;
    }

    public List<Movies> parse(){
        String[] jsonMovies = json.split("\\},\\{");
        List<Movies> movies = moviesList(jsonMovies);
        return movies;

    }

    private List<Movies> moviesList(String[] jsonMovies){
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
