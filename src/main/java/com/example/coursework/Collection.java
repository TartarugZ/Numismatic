package com.example.coursework;

import java.util.ArrayList;

public class Collection {

    private ArrayList<Coin> collection=new ArrayList<>();

    public final String nameCollection;


    public Collection(String nameCollection) {
        this.nameCollection = nameCollection;

    }

    public ArrayList<Coin> getCollection() {
        return collection;
    }

    public void addToCollection(Coin coin){
        collection.add(coin);

    }

}

