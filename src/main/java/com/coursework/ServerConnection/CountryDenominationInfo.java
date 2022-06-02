package com.coursework.ServerConnection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;

public class CountryDenominationInfo implements Serializable {
    private String country;
    private MultiMap<String,ArrayList<String>> curAndValues;

    public MultiMap<String, ArrayList<String>> getCurAndValues() {
        return curAndValues;
    }

    public void setCurAndValues(MultiMap<String, ArrayList<String>> curAndValues) {
        this.curAndValues = curAndValues;
    }


    private boolean allInfo;

    public CountryDenominationInfo() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }





    public void setCurAndValues() {

        this.curAndValues = new MultiValueMap<>();
    }
    public void addToCurAndValues(String f, String s){

        curAndValues.put(f,s);

    }

    public boolean isAllInfo() {
        return allInfo;
    }

    public void setAllInfo(boolean allInfo) {
        this.allInfo = allInfo;
    }

    public ObservableList<String> getValue(){
        ArrayList<String> values = new ArrayList<>();
        curAndValues.forEach((key,value)->{
                values.add(key);
                });
        return FXCollections.observableArrayList(values);
    }

    public ObservableList<String> getCurrency(){
        ArrayList<String> currency = new ArrayList<>();
        curAndValues.forEach((key,value)->{
            ArrayList<String> h=(ArrayList<String>)value;
            currency.addAll(h);
        });
        return FXCollections.observableArrayList(currency);
    }

}
