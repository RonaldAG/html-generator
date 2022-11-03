package services;

import java.util.List;

import entities.Content;

public interface JsonParser {
    List<? extends Content> parse();
}
