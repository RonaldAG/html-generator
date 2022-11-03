package entities;
public record Movies(String title, String urlImage, String rating, String year) implements Content, Comparable<Movies>{
    @Override
    public int compareTo(Movies other){
        return this.title.compareTo(other.title());
    }
}
