package com.coursework.server_connection;

import com.coursework.functions.PropertyConnection;
import com.coursework.objects.Coin;
import com.coursework.objects.Collection;
import com.coursework.objects.CollectionBase;
import com.coursework.objects.CollectionDTO;
import com.fasterxml.jackson.core.type.TypeReference;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.coursework.controllers.LanguageSelectionScene.TRANSLATION;

/**
 * Класс для контроля запросов на сервер
 */
public class ServerWork {
    Logger log = Logger.getLogger(ServerWork.class.getName());

    /** Запрос на получение списка всех стран
     * @param string язык приложения
     * @return List стран
     */
    public List<String> getCountries(String string) {
        HttpURLConnection con;
        ArrayList<String> we=new ArrayList<>();
        try {
            con = (HttpURLConnection) new URL("http://localhost:8080/search/countries?lang="+string).openConnection();
            String auth = credits();
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
            String authHeaderValue = "Basic " + new String(encodedAuth);
            con.setRequestProperty("Authorization",authHeaderValue);
            con.setRequestMethod("GET");
            con.connect();

            if (HttpURLConnection.HTTP_OK == con.getResponseCode()) {
                String line=buffRead(con.getInputStream());
                ObjectMapper objectMapper = new ObjectMapper();
                we = objectMapper.readValue(line, new TypeReference<>() {
                });
                log.info("Get country request: Success "+con.getResponseCode());
            }else log.info("Get country request: Failure "+con.getResponseCode());
        }catch (IOException e){e.printStackTrace();}
        return we;
    }

    /**Запрос для поиска монет по отправленным на сервер характеристикам
     * @param searchInformation SearchInformation со всеми характеристиками
     * @param language язык приложения
     * @return List найденных монет
     * @throws IOException '
     */
    public List<Coin> userRequest(SearchInformation searchInformation, String language) throws IOException {

        String jsonString = searchInformation.toJSON();
        final Content putResult = Request.Put("http://localhost:8080/search?lang=" + language)
                .bodyString(jsonString, ContentType.APPLICATION_JSON)
               .execute().returnContent();

        String line=buffRead(putResult.asStream());
        log.info("User request was done");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        if (putResult.asString().equals("coins with the specified parameters were not found")) {
            log.info("No coins were found");
            return new ArrayList<>();
        } else {
            log.info("Coins were found");
            return mapper.readValue(line,
                    new TypeReference<ArrayList<Coin>>() {
                   });
        }

    }

    /**Запрос регистрации пользователя
     * @param username имя пользователя
     * @param password пароль пользователя
     * @return String  ответ
     */
    public String userSignUp(String username, String password){

        HttpURLConnection con;
        String result="";
        try {
            con = (HttpURLConnection) new URL("http://localhost:8080/acc/new").openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{"+"\"username\""+":\""+username+"\","+ "\"password\""+":\""+ password+"\"}";
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            con.connect();
            if (HttpURLConnection.HTTP_OK == con.getResponseCode()) {
                result="User successfully created";
                log.info("Registration success: "+con.getResponseCode());
            }else if(con.getResponseCode()==205) {
                result="User with this login already exists";
                log.info("Registration problem: "+con.getResponseCode());
            }else{
                result="Error";
                log.info("Registration failure: "+con.getResponseCode());
            }
        }catch (IOException e){e.printStackTrace();}
        return result;
    }

    /**Запрос на получение номиналов и валют конкретной страны
     * @param country страна
     * @param language язык приложения
     * @return CountryDenominationInfo со страной и HashSet с парами номинал-валюта
     * @throws IOException '
     */
    public CountryDenominationInfo loadValueAndCurrency(String country,String language) throws IOException {

        final Content putResult = Request.Put("http://localhost:8080/search/info?lang=" + language)
                .bodyString("{\"country\": \"" + country + "\"}", ContentType.APPLICATION_JSON)
                .execute().returnContent();

        String line=buffRead(putResult.asStream());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new GuavaModule());
        mapper.registerSubtypes(CountryDenominationInfo.class);
        CountryDenominationInfo countryDenominationInfo=mapper.readValue(line, new TypeReference<>() {
        });
        log.log(Level.INFO,"Result: {0}",countryDenominationInfo);
            return countryDenominationInfo;
    }

    /**Вход в аккаунт
     * @param username имя пользователя
     * @param password пароль пользователя
     * @return String ответ сервера
     */
    public String login(String username, String password){

        HttpURLConnection con;

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
                   propertyConnection.open().setProperty("username",username);
                   propertyConnection.open().setProperty("password",password);
                   propertyConnection.open().setProperty("language",a);
                   propertyConnection.open().store(fileOutputStream,"");
                   propertyConnection.close();
                   log.info("Login successful "+con.getResponseCode());
                    result=String.valueOf(con.getResponseCode());
                }
            }else {
                log.info("Login failure "+con.getResponseCode());
                result=String.valueOf(con.getResponseCode());
            }
        }catch (IOException e){e.printStackTrace();}

        return result;
    }

    /** Отправка коллекции на сервер
     * @param collection Коллекция Collection
     */
    public void sendCollection(CollectionDTO collection,String status){
        HttpURLConnection con;
        try {
            con = (HttpURLConnection) new URL("http://localhost:8080/collection/postcollection?status="+status).openConnection();
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
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            con.connect();
            if (HttpURLConnection.HTTP_OK == con.getResponseCode()) {
                log.info("Collection was sent "+con.getResponseCode());
            }else {
                log.info("Collection wasn't sent "+con.getResponseCode());
            }
        }catch (IOException e){e.printStackTrace();}
    }

    /**Запрос на получение коллекции с сервера
     * @return База коллекций CollectionBase  массивом коллекций внутри
     */
    public CollectionBase getCollections(){
        HttpURLConnection con;
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
                String line = buffRead(con.getInputStream());
                ObjectMapper objectMapper=new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                log.info("Get collections: success "+con.getResponseCode());
                CollectionBase collectionBase=new CollectionBase();
                ArrayList<CollectionDTO> a=new ArrayList<>(objectMapper.readValue(line, new TypeReference<List<CollectionDTO>>() { }));
                ArrayList<Collection> b=new ArrayList<>();
                a.forEach(x->b.add(CollectionDTO.toCollection(x)));
                collectionBase.setAllCollections(b);
                return collectionBase;

            }else {
                log.info("Get collections: none "+con.getResponseCode());
                return new CollectionBase();
            }
        }catch (IOException e){e.printStackTrace();}
        return new CollectionBase();
    }

    /**Запрос обновления цены
     * @param url часть url-ссылки на монету
     * @param language язык приложения
     * @return String с обновленной ценой
     * @throws IOException '
     */
    public String checkCost(String url, String language) throws IOException {
            final Content getResult = Request.Get("http://localhost:8080/search/price?url=" + url + "&lang=" + language)
                    .execute().returnContent();

            log.log(Level.INFO, "Price received: {0} ", getResult.asString());
            return buffRead(getResult.asStream());
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
