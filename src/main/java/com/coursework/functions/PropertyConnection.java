package com.coursework.functions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * класс для более быстрой работы с Property
 */
public class PropertyConnection {

    Properties property;
    InputStreamReader fileInputStream;

    /**конструктор PropertyConnection
     * @param pathToProperty путь к properties
     * @throws IOException '
     */
    public PropertyConnection(String pathToProperty) throws IOException {
        property = new Properties();
        fileInputStream = new InputStreamReader(new FileInputStream(pathToProperty), StandardCharsets.UTF_8);
        property.load(fileInputStream);
    }

    /**открыть properties
     * @return возвращает Property
     */
    public Properties open() {
        return property;
    }

    /**закрыть properties
     * @throws IOException '
     */
    public void close() throws IOException {
        fileInputStream.close();
        fileInputStream=null;
    }

}

