package com.coursework.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CollectionBase implements Serializable {

    private ArrayList<Collection> allCollections = new ArrayList<>();

    public ArrayList<Collection> getAllCollections() {
        return allCollections;
    }
    public void addCollection(Collection collection){
        allCollections.add(collection);
    }

    public CollectionBase(){
    }

    @Override
    public String toString() {
        return "CollectionBase{" +
                "allCollections=" + allCollections +
                '}';
    }
}
