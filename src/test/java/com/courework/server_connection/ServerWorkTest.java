package com.courework.server_connection;

import com.coursework.objects.Coin;
import com.coursework.server_connection.SearchInformation;
import com.coursework.server_connection.ServerWork;
import org.testng.annotations.Test;
import org.junit.jupiter.api.Assertions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ServerWorkTest {
    ServerWork serverWork=new ServerWork();
    @Test
    public void testGetCountriesRequest(){
        ArrayList<String> we=new ArrayList<>(serverWork.getCountries("ru"));
        System.out.println(we);
        Assertions.assertTrue(we.size() >0);
    }

    @Test
    public void testLogin() throws IOException {
        Assertions.assertEquals("200", serverWork.login("Kokomi", "Real"));
    }

    @Test
    public void testSearching() throws IOException {
        SearchInformation searchInformation=new SearchInformation();
        ArrayList<Integer> year=new ArrayList<>();
        ArrayList<String> currency=new ArrayList<>();
        ArrayList<String> value=new ArrayList<>();
        ArrayList<String> mint=new ArrayList<>();
        year.add(2016);
        value.add("1");
        currency.add("euro");
        mint.add("");
        searchInformation.setCountry("Germany");
        searchInformation.setYear(year);
        searchInformation.setCurrency(currency);
        searchInformation.setValue(value);
        searchInformation.setMint(mint);
        List<Coin> we=serverWork.userRequest(searchInformation,"en");
        System.out.println(we);
        Assertions.assertTrue(we.size()>0);
    }
}
