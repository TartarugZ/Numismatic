package com.coursework.server_connection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Класс для отправки и получени от сервера информации поиска монеты
 */
public class SearchInformation implements Serializable {
    private String country;
    private ArrayList<Integer> year;
    private ArrayList<String> value;
    private ArrayList<String> currency;
    private ArrayList<String> mint=new ArrayList<>();


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

    public void setYear(List<Integer> year) {this.year = new ArrayList<>(year);}

    public void setValue(List<String> value) {
        this.value = new ArrayList<>(value);
    }

    public void setCurrency(List<String> currency) {
        this.currency = new ArrayList<>(currency);
    }

    public void setMint(List<String> mint) {
        this.mint = new ArrayList<>(mint);
    }

    public SearchInformation(){/*empty consrtuctor*/}

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


    /**массив строк для будущей строки в json
     * @param array массив строк
     * @return строка для json
     */
    public  String stringArrayToString(List<String> array){

        StringBuilder sb=new StringBuilder();

        for(String elem:array){
            sb.append("\"").append(elem).append("\",");
        }

        return  sb.toString().substring(0,sb.toString().length()-1);
    }

    /**массив Integer для будущей строки в json
     * @param array массив Integer
     * @return строка для json
     */
    public  String integerArrayToString(List<Integer> array){

       StringBuilder sb=new StringBuilder();

        for(Integer elem:array){
            sb.append(elem).append(",");
        }

        return  sb.toString().substring(0,sb.toString().length()-1);
    }


    /**Строка json для отравки на сервер и получения
     * @return строка json
     */
    public String  toJSON(){
        if(Optional.ofNullable(value.get(0)).orElse("null").equals("null")) value.set(0,"");
        if(Optional.ofNullable(currency.get(0)).orElse("null").equals("null")) currency.set(0,"");
        if(Optional.ofNullable(mint.get(0)).orElse("null").equals("null")) mint.set(0,"");

        return "{" +
                "\"country\":\"" + country +"\"" +
                ", \"year\":[" + Optional.ofNullable(integerArrayToString(year)).orElse("") +"]"+
                ", \"value\":[" + Optional.ofNullable(stringArrayToString(value)).orElse("") +"]"+
                ", \"currency\":[" + Optional.ofNullable(stringArrayToString(currency)).orElse("") +"]"+
                ", \"mint\":[" + Optional.ofNullable(stringArrayToString(mint)).orElse("") +"]"+
                '}';

    }

}
