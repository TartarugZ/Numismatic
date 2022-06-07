package com.coursework.objects;



import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Класс коллекции присылаемой с сервера
 */
public class CollectionDTO implements Serializable {

    private ArrayList<Coin> collection;
    private String nameCollection;

    public void setCollection(List<Coin> collection) {
        this.collection = new ArrayList<>(collection);
    }

    public void setNameCollection(String nameCollection) {
        this.nameCollection = nameCollection;
    }

    public CollectionDTO() {/* empty конструктор*/}

    public List<Coin> getCollection() {return collection;}
    public String getNameCollection() {
        return nameCollection;
    }

    /**Создание Collection а основе этой CollectionDTO
     * @param collectionDTO текущая CollectionDTO
     * @return Collection
     */
    public static Collection toCollection(CollectionDTO collectionDTO){
        Collection collection=new Collection(collectionDTO.getNameCollection());
        collection.setCoinArrayList(collectionDTO.getCollection());
        return collection;
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

