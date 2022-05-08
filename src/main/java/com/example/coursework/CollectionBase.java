package com.example.coursework;

import java.util.ArrayList;

public class CollectionBase {

    private ArrayList<Collection> allCollections = new ArrayList<>();

    public ArrayList<Collection> getAllCollections() {
        return allCollections;
    }
    public void addCollection(Collection collection){
        allCollections.add(collection);
    }
}
