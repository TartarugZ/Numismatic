package com.coursework.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс базы коллекций
 */
public class CollectionBase implements Serializable {

    private ArrayList<Collection> allCollections = new ArrayList<>();

    public List<Collection> getAllCollections() {
        return allCollections;
    }
    public void setAllCollections(List<Collection> a){allCollections.addAll(a);}
    public void addCollection(Collection collection){
        allCollections.add(collection);
    }

    public CollectionBase(){/*пустой коструктор*/}

    @Override
    public String toString() {
        return "CollectionBase{" +
                "allCollections=" + allCollections +
                '}';
    }
}
