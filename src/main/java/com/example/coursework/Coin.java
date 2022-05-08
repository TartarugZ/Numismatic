package com.example.coursework;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;



public class Coin {

    private StringProperty country;
    private StringProperty years;
    private StringProperty price;
    private StringProperty currency;
    ArrayList<String> a=new ArrayList<>();
   /*
    private  String category;
    private Optional<Float> cost;
    private String linkUcoin;
    private LocalDate dataOfCreate;*/

    public Coin(String country) {
        int y =ThreadLocalRandom.current().nextInt(0,4);
        a.add("10 von");
        a.add("100 rubles");
        a.add("5 pounds" );
        a.add("300 bucks");
        a.add("10000 yen");

        this.country = new SimpleStringProperty(country);
        this.years = new SimpleStringProperty(String.valueOf(ThreadLocalRandom.current().nextInt(1312,2022)));
        this.price= new SimpleStringProperty(String.valueOf(ThreadLocalRandom.current().nextInt(10,100)));
        this.currency=new SimpleStringProperty(a.get(y));
        /*
       this.value = value;
        this.category = category;
        this.cost = cost;
        this.linkUcoin = linkUcoin;
        this.dataOfCreate =dataOfCreate.now() ;*/

    }

    public String getCountry() {
        return country.get();
    }
    public StringProperty getCountryProperty(){return  country;}
    public void setCountry(String string){this.country.set(string);}

    public String getYears(){return String.valueOf(this.years.get());}
    public StringProperty getYearsProperty(){return  years;}
    public void setYears(String string){this.years.set(string);}

    public String getPrice(){return String.valueOf(this.price.get());}
    public void setPrice(String string){this.price.set(string);}

    public String getCurrency(){return this.currency.get();}
    public void setCurrency(String string){this.currency.set(string);}


/*
public Optional<Float> getCost() {
        return cost;
    }

    public LocalDate getDataOfCreate() {
        return dataOfCreate;
    }
    public String getCurrency() {
        return currency;
    }

    public String getValue() {
        return value;
    }

    public String getCategory() {
        return category;
    }

    public Optional<Float> getCostActual() {
        return cost;
    }


    public String getLinkUcoin() {
        return linkUcoin;
    }
*/

}
