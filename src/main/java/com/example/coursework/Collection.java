package com.example.coursework;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Collection {

    private ArrayList<Coin> collection=new ArrayList<>();

    private StringProperty nameCollection = new SimpleStringProperty();


    public Collection(String nameCollection) {
        this.nameCollection.set(nameCollection);
    }

    public ArrayList<Coin> getCollection() {
        return collection;
    }

    public void addToCollection(Coin coin){
        collection.add(coin);

    }

    public String getNameCollection(){
        return nameCollection.get();
    }
}

