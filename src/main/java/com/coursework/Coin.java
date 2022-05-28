package com.coursework;

import com.coursework.Serialization.ReadObjects;
import com.coursework.Serialization.WriteObjects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class Coin implements Serializable {

    private transient StringProperty country;
    private transient StringProperty years;
    private transient StringProperty cost;
    private transient StringProperty currency;
    private transient StringProperty value;
    private transient StringProperty category;
    private transient StringProperty mint;
    private transient StringProperty date;
    private transient StringProperty linkUcoin;
    transient  ArrayList<String> a=new ArrayList<>();


/*
    public Coin(CoinDTO coinDTO) {
        this.country = new SimpleStringProperty(coinDTO.getCountry());
        this.years = new SimpleStringProperty(coinDTO.getYears().toString());
        this.cost = new SimpleStringProperty(coinDTO.getCost());
        this.currency=new SimpleStringProperty(coinDTO.getCurrency());
        this.value=new SimpleStringProperty(coinDTO.getValue());
        this.category=new SimpleStringProperty(coinDTO.getCategory());
        this.mint=new SimpleStringProperty(coinDTO.getMint());
        this.date=new SimpleStringProperty(coinDTO.getDataOfCreate().toString());
        this.linkUcoin=new SimpleStringProperty(coinDTO.getLinkUcoin());
    }

 */
public Coin(String string){
    this.country = new SimpleStringProperty(string);
    this.years = new SimpleStringProperty("");
    this.cost = new SimpleStringProperty("");
    this.currency=new SimpleStringProperty("");
    this.value=new SimpleStringProperty("");
    this.category=new SimpleStringProperty("");
    this.mint=new SimpleStringProperty("");
    this.date=new SimpleStringProperty("");
    this.linkUcoin=new SimpleStringProperty("");
}
    public Coin(){init();}

    public String getCountry() {
        return country.get();
    }
    public StringProperty getCountryProperty(){return  country;}
    public void setCountry(String string){this.country.set(string);}

    public String getYears(){return years.get();}
    public StringProperty getYearsProperty(){return  years;}
    public void setYears(String string){this.years.set(string);}

    public String getCost(){return cost.get();}
    public StringProperty getPriceProperty(){return cost;}
    public void setCost(String string){this.cost.set(string);}

    public String getCurrency(){return this.currency.get();}
    public StringProperty getCurrencyProperty(){return  currency;}
    public void setCurrency(String string){this.currency.set(string);}

    public String getValue(){return this.value.get();}
    public StringProperty getValueProperty(){return  value;}
    public void setValue(String string){this.value.set(string);}

    public String getCategory(){return this.category.get();}
    public StringProperty getCategoryProperty(){return  category;}
    public void setCategory(String string){this.category.set(string);}

    public String getMint(){return this.mint.get();}
    public StringProperty getMintProperty(){return  mint;}
    public void setMint(String string){this.mint.set(string);}

    public String getDate(){return this.date.get();}
    public StringProperty getDateProperty(){return  date;}
    public void setDate(String string){this.date.set(string);}

    public String getLinkUcoin(){return this.linkUcoin.get();}
    public StringProperty getLinkUcoinProperty(){return  linkUcoin;}
    public void setLinkUcoin(String string){this.linkUcoin.set(string);}

    @Override
    public String toString() {
        return "Coin{" +
                "country=" + country +
                ", years=" + years +
                ", cost=" + cost +
                ", currency=" + currency +
                ", value=" + value +
                ", category=" + category +
                ", mint=" + mint +
                ", date=" + date +
                ", linkUcoin=" + linkUcoin +
                '}';
    }


    private void init(){
        this.country = new SimpleStringProperty();
        this.years = new SimpleStringProperty();
        this.cost = new SimpleStringProperty();
        this.currency=new SimpleStringProperty();
        this.value=new SimpleStringProperty();
        this.category=new SimpleStringProperty();
        this.mint=new SimpleStringProperty();
        this.date=new SimpleStringProperty();
        this.linkUcoin=new SimpleStringProperty();
    }
    @Serial
    private void writeObject(ObjectOutputStream s) throws IOException {
        WriteObjects.writeAllProp(s,country,years, cost,currency,value,category,mint,date,linkUcoin);
    }
    @Serial
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        init();
        ReadObjects.readAllProp(s, country,years, cost,currency,value,category,mint,date,linkUcoin);
    }
}
