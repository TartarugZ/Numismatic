package com.coursework.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Collection implements Serializable {

    private ArrayList<Coin> coinArrayList =new ArrayList<>();

    private String nameCollection ;


    public Collection(String nameCollection) {
        this.nameCollection=nameCollection;
    }

    public void setCoinArrayList(List<Coin> coinArrayList) {
        this.coinArrayList = new ArrayList<>(coinArrayList);
    }

    public List<Coin> getCoinArrayList() {
        return coinArrayList;
    }

    public void addToCollection(Coin coin){
        coinArrayList.add(coin);
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
                "coinArrayList=" + coinArrayList +
                ", nameCollection='" + nameCollection + '\'' +
                '}';
    }

    public CollectionDTO toCollectionDTO(){
        CollectionDTO collectionDTO=new CollectionDTO();
        collectionDTO.setNameCollection(this.nameCollection);
        ArrayList<Coin> coins = new ArrayList<>(coinArrayList);
        collectionDTO.setCollection(coins);
        return collectionDTO;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

       Collection col=(Collection) obj;
       return this.nameCollection.equals(col.nameCollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coinArrayList, nameCollection);
    }
}

