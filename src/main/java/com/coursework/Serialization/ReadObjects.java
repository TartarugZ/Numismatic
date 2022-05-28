package com.coursework.Serialization;

import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadObjects {
    public static void readAllProp(ObjectInputStream s, Property... properties) throws IOException, ClassNotFoundException {
        for(Property prop:properties) {
            if(prop instanceof IntegerProperty) ((IntegerProperty)prop).setValue(s.readInt());
            else if(prop instanceof StringProperty) ((StringProperty)prop).setValue(s.readUTF());
            else if(prop instanceof ObjectProperty) ((ObjectProperty)prop).setValue(s.readObject());
            else throw new RuntimeException("Unsupported object type") ;
        }
    }
}
