package com.coursework.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class CollectionBase implements Serializable {

    private ArrayList<Collection> allCollections = new ArrayList<>();

    public ArrayList<Collection> getAllCollections() {
        return allCollections;
    }
    public void setAllCollections(ArrayList<Collection> a){allCollections.addAll(a);}
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
