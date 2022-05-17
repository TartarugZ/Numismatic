package com.example.coursework;

import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReadObjects {
    // automatic fill a set of properties with values contained in ObjectInputStream
    public static void readAllProp(ObjectInputStream s, Property... properties) throws IOException, ClassNotFoundException {
        for(Property prop:properties) {
            if(prop instanceof IntegerProperty) ((IntegerProperty)prop).setValue(s.readInt());
            else if(prop instanceof LongProperty) ((LongProperty)prop).setValue(s.readLong());
            else if(prop instanceof StringProperty) ((StringProperty)prop).setValue(s.readUTF());
            else if(prop instanceof ObjectProperty) ((ObjectProperty)prop).setValue(s.readObject());
            else throw new RuntimeException("Unsupported object type : " + prop==null?null:prop.toString());
        }
    }
}
