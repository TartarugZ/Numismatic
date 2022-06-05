package com.coursework.objects;
import com.coursework.serverConnection.CoinDTO;
import com.coursework.serverConnection.CollectionDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Collection implements Serializable {

    private ArrayList<CoinDTO> collection=new ArrayList<>();

    private String nameCollection ;


    public Collection(String nameCollection) {
        this.nameCollection=nameCollection;
    }

    public void setCollection(ArrayList<CoinDTO> collection) {
        this.collection = collection;
    }

    public ArrayList<CoinDTO> getCollection() {
        return collection;
    }

    public void addToCollection(CoinDTO coin){
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
                "collection=" + collection+
                '}';
    }

    public CollectionDTO toCollectionDTO(){
        CollectionDTO collectionDTO=new CollectionDTO();
        collectionDTO.setNameCollection(this.nameCollection);
        ArrayList<CoinDTO> coins = new ArrayList<>(collection);
        collectionDTO.setCollection(coins);
        return collectionDTO;
    }

    @Override
    public boolean equals(Object o) {
       Collection col=(Collection) o;
       return this.nameCollection.equals(col.nameCollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collection, nameCollection);
    }
}

