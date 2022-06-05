package com.coursework.functions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertyConnection {

    Properties property;
    InputStreamReader fileInputStream;

    public PropertyConnection(String pathToProperty) throws IOException {
        property = new Properties();
        fileInputStream = new InputStreamReader(new FileInputStream(pathToProperty),Charset.forName("UTF-8"));
        property.load(fileInputStream);
        System.out.println("Someone is here");
    }

    public Properties open() throws IOException {
        System.out.println("Opened");
        return property;
    }

    public void close() throws IOException {
        System.out.println("Closed");
        fileInputStream.close();
        fileInputStream=null;
    }

}

