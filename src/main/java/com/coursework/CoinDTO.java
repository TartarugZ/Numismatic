package com.coursework;

import java.io.Serializable;
import java.time.LocalDate;

public class CoinDTO implements Serializable {


    private String country;
    private String currency;
    private String category;
    private Integer years;
    private String cost;
    private String linkUcoin;
    private LocalDate dataOfCreate;
    private String value;
    private String mint;

    public CoinDTO(String country, String currency, String category, Integer years, String cost, String linkUcoin, LocalDate dataOfCreate, String value, String mint) {
        this.country = country;
        this.currency = currency;
        this.category = category;
        this.years = years;
        this.cost = cost;
        this.linkUcoin = linkUcoin;
        this.dataOfCreate = LocalDate.now();
        this.value = value;
        this.mint=mint;
    }
    public  CoinDTO(){}
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
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
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

    @Override
    public String toString() {

        return "Монета"+'\''+
                "Страна: "+getCountry()+'\''+
                "Валюта: "+getCurrency()+'\''+
                "Номинал: "+getValue()+'\''+
                "Год: "+ getYears()+'\''+
                "Монетный двор: "+getMint()+'\''+
                "Примерная стоимость: "+getCost()+'\''+
                "ссылка: "+getLinkUcoin()+'\''+
                "Дата создания: "+getDataOfCreate()+'\''+
                "Категория: "+getCategory()+'\''
                ;


    }
}
