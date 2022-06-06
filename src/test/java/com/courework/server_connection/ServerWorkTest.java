package com.courework.server_connection;

import com.coursework.server_connection.ServerWork;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import org.testng.annotations.Test;
import org.junit.jupiter.api.Assertions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

public class ServerWorkTest {
    ServerWork serverWork=new ServerWork();

    @Test
    public void testGetCountriesRequest(){
        ArrayList<String> we=new ArrayList<>(serverWork.getCountries("ru"));
        System.out.println(we);
        Assertions.assertTrue(we.size() >0);
    }

//    @Test
//    public void test

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
}
