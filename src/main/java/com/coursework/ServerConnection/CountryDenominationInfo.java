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


    public ArrayList<String> getValue(){
        HashSet<String> set = new HashSet<>();
        curAndValues.forEach((key) -> set.add(key.getFirst()));
        System.out.println(set);
        return new ArrayList<>(set);
    }

    public ArrayList<String> getCurrency() {
        HashSet<String> set = new HashSet<>();
        curAndValues.forEach((key) -> set.add(key.getSecond()));
        System.out.println(set);
        return new ArrayList<>(set);
    }

    public ArrayList<String> getSingleValue(String currency){
        ArrayList<String> a=new ArrayList<>();
        if(currency==null||currency.equals("")||currency.equals(" ")){
            a.addAll(getValue());
        }else{
            curAndValues.forEach((key)->{
                if(key.getSecond().equals(currency)) a.add(key.getFirst());
            });}

        return a;
    }

    public ArrayList<String> getSingleCurrency(String value){
        ArrayList<String> a=new ArrayList<>();
        if(value==null||value.equals("")||value.equals(" ")){
            a.addAll(getCurrency());
        }else {
            curAndValues.forEach((key)->{
                if(key.getFirst().equals(value)) a.add(key.getSecond());
            });
        }

        return a;
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
