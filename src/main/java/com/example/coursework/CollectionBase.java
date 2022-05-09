package com.example.coursework;

import java.util.ArrayList;

public class CollectionBase {
    CollectionBase(){
        Collection collection=new Collection("Ishtar");
        collection.addToCollection(new Coin("Grogu"));
        collection.addToCollection(new Coin("Du"));
        collection.addToCollection(new Coin("BD-1"));
        collection.addToCollection(new Coin("AT-AT"));
        collection.addToCollection(new Coin("Speed"));
        collection.addToCollection(new Coin("Ameno"));
        collection.addToCollection(new Coin("Rot"));
        collection.addToCollection(new Coin("Ebal"));
        collection.addToCollection(new Coin("Dendro"));
        allCollections.add(collection);
    }

    private ArrayList<Collection> allCollections = new ArrayList<>();

    public ArrayList<Collection> getAllCollections() {
        return allCollections;
    }
    public void addCollection(Collection collection){
        allCollections.add(collection);
    }
}
