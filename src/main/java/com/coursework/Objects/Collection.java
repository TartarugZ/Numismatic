package com.coursework.Objects;
import java.io.Serializable;
import java.util.ArrayList;

public class Collection implements Serializable {

    private ArrayList<Coin> collection=new ArrayList<>();

    private String nameCollection ;


    public Collection(String nameCollection) {
        this.nameCollection=nameCollection;
    }

    public ArrayList<Coin> getCollection() {
        return collection;
    }

    public void addToCollection(Coin coin){
        collection.add(coin);

    }

    public String getNameCollection(){
        return nameCollection;
    }

    public void setNameCollection(String nameCollection) {
        this.nameCollection=nameCollection;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "collection=" + collection +
                ", nameCollection='" + nameCollection + '\'' +
                '}';
    }
}

