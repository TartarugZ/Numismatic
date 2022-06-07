package com.coursework.objects;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Coin implements Serializable {


    private String country;
    private String currency;
    private Integer years;
    private String cost;
    private String linkUcoin;
    private LocalDate dataOfCreate;
    private String value;
    private String mint;
    private LinkedHashMap<String,String> infoTable;

public Coin(){ /* пустой конструктор */ }

    public void setInfoTable(Map<String, String> infoTable) {
        this.infoTable = new LinkedHashMap<>(infoTable);
    }

    public Map<String, String> getInfoTable() {
        return infoTable;
    }

    public void setInfoTable() {
        this.infoTable=new LinkedHashMap<>();
    }

    public void addToInfoTable(String firstElem, String secondElem) {

        this.infoTable.put(firstElem,secondElem);
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public  Integer getYears() {
        return years;
    }
    public void setYears( Integer years) {
        this.years = years;
    }
    public String getCost() {
    if(this.cost.equals("none")) return "";else return cost;
    }
    public void setCost(String cost) {
        this.cost = cost;
    }
    public String getLinkUcoin() {
        return linkUcoin;
    }
    public void setLinkUcoin(String linkUcoin) {
        this.linkUcoin = linkUcoin;
    }
    public LocalDate getDataOfCreate() {
        return dataOfCreate;
    }
    public void setDataOfCreate(LocalDate dataOfCreate) {
        this.dataOfCreate = dataOfCreate;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getMint() {
        return mint;
    }
    public void setMint(String mint) {
        this.mint = mint;
    }

    public String getInfo(){
        if(infoTable!=null){
        Iterator<Map.Entry<String, String>> iterator = infoTable.entrySet().iterator();
            StringBuilder sb = new StringBuilder();
            while (iterator.hasNext())
            {
                    Map.Entry<String, String> pair = iterator.next();
                    String first = pair.getKey();
                    String second = pair.getValue();
                    sb.append(first).append("–").append(second).append("\n");
            }
            return sb.toString();
        }else return "";
    }

    @Override
    public String toString() {

        return getCountry()+" "+getValue()+" "+getCurrency()+" "+getYears()+" "+getMint()+" "+getCost();

    }


}
