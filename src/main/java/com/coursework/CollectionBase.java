package com.coursework;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CollectionBase implements Serializable {

    private ArrayList<Collection> allCollections = new ArrayList<>();
    private static final long serialVersionUID= -154741419952939728L;

    public ArrayList<Collection> getAllCollections() {
        return allCollections;
    }
    public void addCollection(Collection collection){
        allCollections.add(collection);
    }
    public CollectionBase(){
        Collection collection=new Collection("Ishtar"+ ThreadLocalRandom.current().nextInt(1,1000));
        collection.addToCollection(new Coin("Grogu"));
        collection.addToCollection(new Coin("AT-AT"));
        collection.addToCollection(new Coin("BD-1"));
        allCollections.add(collection);
    }

    @Override
    public String toString() {
        return "CollectionBase{" +
                "allCollections=" + allCollections +
                '}';
    }
}
