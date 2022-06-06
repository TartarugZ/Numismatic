package com.coursework.functions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertyConnection {

    Properties property;
    InputStreamReader fileInputStream;

    public PropertyConnection(String pathToProperty) throws IOException {
        property = new Properties();
        fileInputStream = new InputStreamReader(new FileInputStream(pathToProperty), StandardCharsets.UTF_8);
        property.load(fileInputStream);
    }

    public Properties open() {
        return property;
    }

    public void close() throws IOException {
        fileInputStream.close();
        fileInputStream=null;
    }

}

