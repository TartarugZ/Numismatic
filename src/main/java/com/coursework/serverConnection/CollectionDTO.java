package com.coursework.serverConnection;



import java.io.Serializable;

import java.util.ArrayList;
import java.util.Objects;


public class CollectionDTO implements Serializable {

    private ArrayList<CoinDTO> collection;
    public String nameCollection;

    public void setCollection(ArrayList<CoinDTO> collection) {
        this.collection = collection;
    }

    public void setNameCollection(String nameCollection) {
        this.nameCollection = nameCollection;
    }

    public CollectionDTO() {}

    public ArrayList<CoinDTO> getCollection() {return collection;}
    public String getNameCollection() {
        return nameCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionDTO that = (CollectionDTO) o;
        return collection.equals(that.collection) && nameCollection.equals(that.nameCollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collection, nameCollection);
    }
}

