package com.coursework.serverConnection;

import com.coursework.functions.PropertyConnection;
import com.coursework.objects.Collection;
import com.coursework.objects.CollectionBase;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static com.coursework.controllers.LanguageSelectionScene.TRANSLATION;


public class ServerWork {

    public ObservableList<String> getCountries(String string) {
        HttpURLConnection con = null;
        ArrayList<String> we=new ArrayList<>();
        try {
            con = (HttpURLConnection) new URL("http://localhost:8080/search/countries?lang="+string).openConnection();
            String auth = credits();
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
            String authHeaderValue = "Basic " + new String(encodedAuth);
            con.setRequestProperty("Authorization",authHeaderValue);
            con.setRequestMethod("GET");
            con.connect();

            StringBuilder sb = new StringBuilder();

            if (HttpURLConnection.HTTP_OK == con.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                line = sb.toString();
                ObjectMapper objectMapper = new ObjectMapper();
                we = objectMapper.readValue(line, new TypeReference<ArrayList<String>>() {
                });

            }else System.out.println(con.getResponseCode());
        }catch (IOException e){e.printStackTrace();}
        return FXCollections.observableArrayList(we);
    }

    public ArrayList<CoinDTO> userRequest(SearchInformation searchInformation, String language) throws IOException {

        String jsonString = searchInformation.toJSON();
        final Content putResult = Request.Put("http://localhost:8080/search?lang=" + language)
                .bodyString(jsonString, ContentType.APPLICATION_JSON)
                .execute().returnContent();

        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(putResult.asStream()));
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        line = sb.toString();


        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        if (putResult.asString().equals("coins with the specified parameters were not found")) {
            return new ArrayList<>();
        } else {
            ArrayList<CoinDTO> listFromJackson = mapper.readValue(line,
                    new TypeReference<ArrayList<CoinDTO>>() {
                    });

            return listFromJackson;
        }
    }

    public String userSignUp(String username, String password) throws IOException {

        HttpURLConnection con = null;
        StringBuilder sb = new StringBuilder();
        String result="";
        try {
            con = (HttpURLConnection) new URL("http://localhost:8080/acc/new").openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{"+"\"username\""+":\""+username+"\","+ "\"password\""+":\""+ password+"\"}";
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            con.connect();
            if (HttpURLConnection.HTTP_OK == con.getResponseCode()) {
                result=buffRead(con.getInputStream());
                System.out.println(con.getResponseCode()+con.getResponseMessage());

            }else {
                result=buffRead(con.getInputStream());
                System.out.println(con.getResponseCode()+con.getResponseMessage());
            }
        }catch (IOException e){e.printStackTrace();}
        System.out.println(sb.toString());
        return result;
    }

    public CountryDenominationInfo loadValueAndCurrency(String country,String language) throws IOException {

        final Content putResult = Request.Put("http://localhost:8080/search/info?lang=" + language)
                .bodyString("{\"country\": \"" + country + "\"}", ContentType.APPLICATION_JSON)
                .execute().returnContent();
        System.out.println(putResult);

        String line=buffRead(putResult.asStream());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new GuavaModule());
        mapper.registerSubtypes(CountryDenominationInfo.class);
        CountryDenominationInfo countryDenominationInfo=mapper.readValue(line, new TypeReference<CountryDenominationInfo>() {});
        System.out.println(countryDenominationInfo);
            return countryDenominationInfo;
    }

    public String login(String username, String password){

        HttpURLConnection con = null;

        String result="";
        try {
            con = (HttpURLConnection) new URL("http://localhost:8080/acc/login").openConnection();

            String auth = username+":"+password;
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
            String authHeaderValue = "Basic " + new String(encodedAuth);
            con.setRequestProperty("Authorization",authHeaderValue);
            con.setRequestMethod("GET");
            con.connect();
            if (HttpURLConnection.HTTP_OK == con.getResponseCode()) {

                PropertyConnection propertyConnection=new PropertyConnection(TRANSLATION);
                try( FileOutputStream fileOutputStream=new FileOutputStream(TRANSLATION)){
                   String a=propertyConnection.open().getProperty("language");
                   System.out.println(a);
                   propertyConnection.open().setProperty("username",username);
                   propertyConnection.open().setProperty("password",password);
                   propertyConnection.open().setProperty("language",a);
                   propertyConnection.open().store(fileOutputStream,"");
                   propertyConnection.close();
                   result=buffRead(con.getInputStream());
                   System.out.println("OH, YES");
                   System.out.println(con.getResponseCode()+con.getResponseMessage());
                }

            }else {
                System.out.println(con.getResponseCode()+con.getResponseMessage());
            }
        }catch (IOException e){e.printStackTrace();}

        return result;
    }

    public void sendCollection(CollectionDTO collection){
        HttpURLConnection con = null;
        String result="";
        try {
            con = (HttpURLConnection) new URL("http://localhost:8080/collection/new").openConnection();
            con.setRequestMethod("POST");
            String auth = credits();
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
            String authHeaderValue = "Basic " + new String(encodedAuth);
            con.setRequestProperty("Authorization",authHeaderValue);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            ObjectMapper mapper=new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            String jsonInputString = mapper.writeValueAsString(collection);
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            con.connect();
            if (HttpURLConnection.HTTP_OK == con.getResponseCode()) {
                result=buffRead(con.getInputStream());
                System.out.println("GOOD"+con.getResponseCode()+con.getResponseMessage());
                System.out.println(result);

            }else {
                result=buffRead(con.getInputStream());
                System.out.println(con.getResponseCode()+con.getResponseMessage());
                System.out.println(result);
            }
        }catch (IOException e){e.printStackTrace();}
    }

    public CollectionBase getCollections(){
        HttpURLConnection con = null;
        String result="";
        try {
            con = (HttpURLConnection) new URL("http://localhost:8080/collection/get").openConnection();
            con.setRequestMethod("GET");
            String auth = credits();
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
            String authHeaderValue = "Basic " + new String(encodedAuth);
            con.setRequestProperty("Authorization",authHeaderValue);
            con.setRequestProperty("Accept", "application/json");
            con.connect();
            if (HttpURLConnection.HTTP_OK == con.getResponseCode()) {
                StringBuilder sb=new StringBuilder();
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                line = sb.toString();
                ObjectMapper objectMapper=new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                System.out.println(line);
                System.out.println("SUCCESS");
                CollectionBase collectionBase=new CollectionBase();
                ArrayList<CollectionDTO> a=objectMapper.readValue(line, new TypeReference<ArrayList<CollectionDTO>>() { });
                ArrayList<Collection> b=new ArrayList<>();
                a.forEach(x->b.add(x.toCollection(x)));
                collectionBase.setAllCollections(b);
                return collectionBase;

            }else {
                System.out.println("FAILURE");
                return new CollectionBase();
            }
        }catch (IOException e){e.printStackTrace();}
        return new CollectionBase();
    }

    private String buffRead(InputStream inputStream) throws IOException {
        StringBuilder sb=new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        line = sb.toString();
        return line;
    }

    private String credits() throws IOException {
        PropertyConnection property=new PropertyConnection(TRANSLATION);
        String a=property.open().getProperty("username")+":"+property.open().getProperty("password");
        property.close();
        return a;
    }


}
