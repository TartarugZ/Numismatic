package com.coursework.ServerConnection;

import com.fasterxml.jackson.core.type.TypeReference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.http.HttpRequest;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.apache.http.impl.client.CloseableHttpClient;
import org.json.JSONObject;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerWork {

    public void collectionCreate(String collection, String name) throws IOException {
    /*
    String query="http://localhost:8080/collection/get?username="+name+"?collectioname="+collection;
    Map<String,Object> params = new LinkedHashMap<>();
    params.put("collectionname", collection);
    params.put("username", name);
    StringBuilder postData = new StringBuilder();
    for (Map.Entry<String, Object> param : params.entrySet()) {
        if (postData.length() != 0) postData.append('&');
        postData.append('=');
        postData.append(param.getValue());
    }
    byte[] postDataBytes = postData.toString().getBytes();
    HttpURLConnection connection=null;

    try {
        connection = (HttpURLConnection) new URL(query).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        connection.setDoOutput(true);
        connection.getOutputStream().write(postDataBytes);
        connection.connect();

        StringBuilder stringBuilder= new StringBuilder();
if(HttpURLConnection.HTTP_OK== connection.getResponseCode()){
    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    String line;
    while((line = in.readLine())!=null){
        stringBuilder.append(line).append("/n");
    }
    System.out.println(stringBuilder.toString());
}else System.out.println("Connection failed");

    }catch (IOException e){
        e.printStackTrace();
    }finally {
        if (connection != null) {
            connection.disconnect();
        }
    }

    CloseableHttpClient client = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost("http://localhost:8080/collection/new");

    JSONObject messages = new JSONObject();
    messages.put("collectionname",collection);
    messages.put("user_id",1);

    JSONObject test = new JSONObject();
    test.put("CollectionEntity",messages);
    test.put("username",name);


    JSONObject jsonObject=new JSONObject(params);
    StringEntity entity = new StringEntity(test.toString());
    httpPost.setEntity(entity);
    httpPost.setHeader("Accept", "application/json");
    httpPost.setHeader("Content-type", "application/json");

    CloseableHttpResponse response = client.execute(httpPost);
    System.out.println("LOX"+response.toString());
    client.close();

    HttpURLConnection con=null;
    try{
        con= (HttpURLConnection) new URL("http://localhost:8080/search/info?lang=ru").openConnection();
        con.setRequestMethod("GET");
        JSONObject js=new JSONObject();
        js.put("country","Россия");
        //con.setRequestProperty();
        con.connect();



        StringBuilder sb= new StringBuilder();
        if(HttpURLConnection.HTTP_OK== con.getResponseCode()){
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line=" ";
            while((line = in.readLine())!=null){
                sb.append(line);
            }
            line= sb.toString();
            System.out.println(sb);
            ObjectMapper objectMapper=new ObjectMapper();
           ArrayList<String> we =objectMapper.readValue(line, new TypeReference<ArrayList<String>>(){});

            we.forEach((key)-> {

                        System.out.println(key + "  :  ");
                    });





                System.out.println(sb);
                System.out.println(we.get(3));

            }

    }catch (Throwable cause){
        cause.printStackTrace();
    }finally {
        if (con != null) {
            con.disconnect();
        }
    }


    final Content getResult = Request.Get("http://localhost:8080/search/countries?lang=ru")
            .execute().returnContent();
    System.out.println(getResult);


     */
/*
    final Content postResult = Request.Put("http://localhost:8080/search/info?lang=ru")
            .bodyString("{\"country\": \"Россия\"}", ContentType.APPLICATION_JSON)
            .execute().returnContent();
    System.out.println(postResult.asString());

    final Content postResult = Request.Post("http://localhost:8080/acc/new?lang=ru")
            .bodyString("{\"username\": \"Aya\",\"password\": \"Seal\"}", ContentType.APPLICATION_JSON)
            .execute().returnContent();
    System.out.println(postResult.asString());



        final Content postResult = Request.Get("http://localhost:8080/acc/get=?Kokomi")
                .execute().returnContent();
        System.out.println(postResult.asString());

        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) new URL("http://localhost:8080/acc/get=?Kokomi").openConnection();
            con.setRequestMethod("GET");
            JSONObject js = new JSONObject();
            con.connect();


            StringBuilder sb = new StringBuilder();
            if (HttpURLConnection.HTTP_OK == con.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = " ";
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                line = sb.toString();
                System.out.println(sb);
                ObjectMapper objectMapper = new ObjectMapper();
                ArrayList<String> we = objectMapper.readValue(line, new TypeReference<ArrayList<String>>() {
                });

                we.forEach((key) -> {

                    System.out.println(key + "  :  ");
                });
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }



        final Content postResult = Request.Put("http://localhost:8080/search/info?lang=ru")
            .bodyString("{\"country\": \"Россия\"}", ContentType.APPLICATION_JSON)
            .execute().returnContent();
    System.out.println(postResult.asString());

        final Content getResult = Request.Get("http://localhost:8080/search/countries?lang=ru")
                .execute().returnContent();
        System.out.println(getResult);

*/
        HttpURLConnection con=null;
        try{
            con= (HttpURLConnection) new URL("http://localhost:8080/search/info?lang=ru").openConnection();
            con.setRequestMethod("GET");
            UserWork userWork=new UserWork();
            con.setRequestProperty("Authorization",userWork.getPasswordAuthentication().toString());

            con.connect();



            StringBuilder sb= new StringBuilder();
            if(HttpURLConnection.HTTP_OK== con.getResponseCode()){
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line=" ";
                while((line = in.readLine())!=null){
                    sb.append(line);
                }
                line= sb.toString();
                System.out.println(sb);
                ObjectMapper objectMapper=new ObjectMapper();
                ArrayList<String> we =objectMapper.readValue(line, new TypeReference<ArrayList<String>>(){});

                we.forEach((key)-> {

                    System.out.println(key + "  :  ");
                });





                System.out.println(sb);
                System.out.println(we.get(3));

            }else System.out.println(con.getResponseCode());

        }catch (Throwable cause){
            cause.printStackTrace();
        }finally {
            if (con != null) {
                con.disconnect();
            }
        }

    }

    public ObservableList<String> getCountries(String string) {
        HttpURLConnection con = null;
        ArrayList<String> we=new ArrayList<>();
        try {
            con = (HttpURLConnection) new URL("http://localhost:8080/search/countries?lang="+string).openConnection();
            UserWork userWork=new UserWork();
            con.setRequestProperty("Authorization",userWork.getPasswordAuthentication().toString());
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
}
