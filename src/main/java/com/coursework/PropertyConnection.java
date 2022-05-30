package com.coursework;

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
    }

    public Properties open() throws IOException {
        return property;
    }

    public void close() throws IOException {
        fileInputStream.close();
        fileInputStream=null;
    }

}

