package com.coursework.ServerConnection;

import com.coursework.Objects.Coin;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class CoinDTO implements Serializable {


    private String country;
    private String currency;
    private Integer years;
    private String cost;
    private String linkUcoin;
    private LocalDate dataOfCreate;
    private String value;
    private String mint;

    private LinkedHashMap<String,String> infoTable;

public CoinDTO(){

}
    public CoinDTO(String country, String currency, Integer years, String cost, String linkUcoin, LocalDate dataOfCreate, String value, String mint) {
        this.country = country;
        this.currency = currency;
        this.years = years;
        this.cost = cost;
        this.linkUcoin = linkUcoin;
        this.dataOfCreate = LocalDate.now();
        this.value = value;
        this.mint=mint;
    }

    public void setInfoTable(LinkedHashMap<String, String> infoTable) {
        this.infoTable = infoTable;
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
        return cost;
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
        Iterator<Map.Entry<String, String>> iterator = infoTable.entrySet().iterator();
        if(infoTable!=null){
            StringBuilder sb = new StringBuilder();
            while (iterator.hasNext())
            {
                    Map.Entry<String, String> pair = iterator.next();
                    String key = pair.getKey();
                    String value = pair.getValue();
                    sb.append(key + "–" + value + "\n");
            }
            return sb.toString();
        }else return "";

    }

    @Override
    public String toString() {

        return getCountry()+" "+getValue()+" "+getCurrency()+" "+getYears()+" "+getMint()+" "+getCost();

    }

    public Coin toCoin(){
    Coin coin=new Coin();
    coin.setCountry(getCountry());
    coin.setYears(getYears().toString());
    coin.setCost(getCost());
    coin.setCurrency(getCurrency());
    coin.setValue(getValue());
    coin.setMint(getMint());
    coin.setDate(getDataOfCreate().toString());
    coin.setLinkUcoin(getLinkUcoin());
    coin.setInfo(getInfo());
    return coin;
    }


}
