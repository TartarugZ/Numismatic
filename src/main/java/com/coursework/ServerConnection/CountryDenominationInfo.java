package com.coursework.ServerConnection;

import java.io.Serializable;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

public class CountryDenominationInfo implements Serializable {
    private String country;
    private HashSet<ValAndCurPair> curAndValues;
    private boolean allInfo;



    public HashSet<ValAndCurPair> getCurAndValues() {
        return curAndValues;
    }

    public void setCurAndValues(HashSet<ValAndCurPair> curAndValues) {
        this.curAndValues = curAndValues;
    }

    public CountryDenominationInfo() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public boolean isAllInfo() {
        return allInfo;
    }

    public void setAllInfo(boolean allInfo) {
        this.allInfo = allInfo;
    }


    public ObservableList<String> getValue(){
        ArrayList<String> values=new ArrayList<>();
        curAndValues.forEach((key)->values.add(key.getFirst()));
        return FXCollections.observableArrayList(values);
    }

    public ObservableList<String> getCurrency(){
        ArrayList<String> currencies=new ArrayList<>();
        curAndValues.forEach((key)->currencies.add(key.getSecond()));
        return FXCollections.observableArrayList(currencies);
    }

    @Override
    public String toString() {
        return "CountryDenominationInfo{" +
                "country='" + country + '\'' +
                ", curAndValues=" + curAndValues +
                ", allInfo=" + allInfo +
                '}';
    }
}
