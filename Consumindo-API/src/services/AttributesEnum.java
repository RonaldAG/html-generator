package services;

public enum AttributesEnum {
    ID(0),
    RANK(1),
    TITLE(2),
    FULL_TITLE(3),
    YEAR(4),
    IMAGE(5),
    CREW(6),
    IMDB_RANKING(7),
    IMDB_COUNT(8);

    private final int position;

    AttributesEnum(int position){
        this.position = position;
    }

    public int getPosition (){
        return this.position;
    }


}
