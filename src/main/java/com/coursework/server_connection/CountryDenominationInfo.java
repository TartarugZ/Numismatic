package com.coursework.server_connection;

import java.io.Serializable;
import java.util.*;

/**
 * Класс для получение от сервера значений номинала и валюты конкретной страны
 */
public class CountryDenominationInfo implements Serializable {
    private String country;
    private HashSet<ValAndCurPair> curAndValues;
    private boolean allInfo;

    public Set<ValAndCurPair> getCurAndValues() {
        return curAndValues;
    }

    public void setCurAndValues(Set<ValAndCurPair> curAndValues) {
        this.curAndValues = new HashSet<>(curAndValues);
    }

    public CountryDenominationInfo() {/*empty constructor*/}

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

    /**возврщает список всех номиналов
     * @return List номиналов
     */
    public List<String> getValue(){
        HashSet<String> set = new HashSet<>();
        if(curAndValues!=null) {
            curAndValues.forEach(key -> set.add(key.getFirst()));
        }
        return new ArrayList<>(set);
    }

    /**возврщает список всех валют
     * @return List валют
     */
    public List<String> getCurrency() {
        HashSet<String> set = new HashSet<>();
        if(curAndValues!=null) {
            curAndValues.forEach(key -> set.add(key.getSecond()));
        }
        return new ArrayList<>(set);
    }

    /**Возвращает список всех номиналов подходящих значению валюты
     * @param currency значение валюты
     * @return List номиналов
     */
    public List<String> getSingleValue(String currency){
        ArrayList<String> a=new ArrayList<>();
        if(currency==null||currency.equals("")||currency.equals(" ")){
            a.addAll(getValue());
        }else{
            curAndValues.forEach(key->{
                if(key.getSecond().equals(currency)) a.add(key.getFirst());
            });}

        return a;
    }

    /**Возвращает список всех валют подходящих значению номинала
     * @param value значение номинала
     * @return List валют
     */
    public List<String> getSingleCurrency(String value){
        ArrayList<String> a=new ArrayList<>();
        if(value==null||value.equals("")||value.equals(" ")){
            a.addAll(getCurrency());
        }else {
            curAndValues.forEach(key->{
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
