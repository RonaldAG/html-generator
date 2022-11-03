package services;

import java.util.Comparator;

import entities.Content;

public class YearCompare implements Comparator<Content>{

    @Override
    public int compare(Content o1, Content o2) {
        return o1.year().compareTo(o2.year());
    }
    
}
