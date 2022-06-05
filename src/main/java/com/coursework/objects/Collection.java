package com.coursework.objects;
import com.coursework.serverConnection.CoinDTO;
import com.coursework.serverConnection.CollectionDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Collection implements Serializable {

    private ArrayList<Coin> collection=new ArrayList<>();

    private String nameCollection ;


    public Collection(String nameCollection) {
        this.nameCollection=nameCollection;
    }

    public void setCollection(ArrayList<Coin> collection) {
        this.collection = collection;
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
                "collection=" + collection+
                '}';
    }

    public CollectionDTO toCollectionDTO(){
        CollectionDTO collectionDTO=new CollectionDTO();
        collectionDTO.setNameCollection(this.nameCollection);
        ArrayList<CoinDTO> coins=new ArrayList<>();
        collection.forEach((coin ->coins.add(coin.toCoinDTO()) ));
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

