package com.coursework.ServerConnection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class SearchInformation implements Serializable {
    private String country;
    private ArrayList<Integer> year;
    private ArrayList<String> value;
    private ArrayList<String> currency;
    private ArrayList<String> mint;


    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public Optional<ArrayList<Integer>> getYear() {
        return  Optional.ofNullable(year);
    }


    public Optional<ArrayList<String>> getValue() {
        return Optional.ofNullable(value);
    }


    public Optional<ArrayList<String>> getCurrency() {
        return Optional.ofNullable(currency);
    }



    public Optional<ArrayList<String>> getMint() {
        return Optional.ofNullable(mint);
    }

    public void setYear(ArrayList<Integer> year) {
        this.year = year;
    }

    public void setValue(ArrayList<String> value) {
        this.value = value;
    }

    public void setCurrency(ArrayList<String> currency) {
        this.currency = currency;
    }

    public void setMint(ArrayList<String> mint) {
        this.mint = mint;
    }

    public SearchInformation(){

    }//Optional<String> mint = Optional.ofNullable(get чтот-то).orElse(""); mint.get()

    @Override
    public String toString() {
        return "SearchInformation{" +
                "\"country\"=\"" + country + '\"' +
                ",\"year\"=" + year+
                ", \"value\"=\"" + value+ '\"' +
                ", \"currency\"=\"" + currency+ '\"' +
                ", \"mint=\"" + mint+ '\"' +
                '}';
    }


    public  String stringArrayToString(ArrayList<String> array){
System.out.println(array);
        String sts="";

        for(String elem:array){
            sts=sts+"\""+elem+"\",";
            System.out.println(elem);

        }
        sts=sts.substring(0,sts.length()-1);

        return  sts;
    }
    public  String integerArrayToString(ArrayList<Integer> array){

        String string="";

        for(Integer elem:array){
            string=string+elem+",";

        }
        string=string.substring(0,string.length()-1);

        return  string;
    }



    public String  toJSON(){
        return "{" +
                "\"country\":\"" + country +"\"" +
                ", \"year\":[" + Optional.ofNullable(integerArrayToString(year)).orElse("") +"]"+
                ", \"value\":[" + Optional.ofNullable(stringArrayToString(value)).orElse("") +"]"+
                ", \"currency\":[" + Optional.ofNullable(stringArrayToString(currency)).orElse("") +"]"+
                ", \"mint\":[" + Optional.ofNullable(stringArrayToString(mint)).orElse("") +"]"+
                '}';

    }

}
