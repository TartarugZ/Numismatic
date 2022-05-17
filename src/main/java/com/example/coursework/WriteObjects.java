package com.example.coursework;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObjects {
    // write a StringProperty to ObjectOutputStream
    public static void writeStringProp(ObjectOutputStream s, StringProperty strProp) throws IOException {
        s.writeUTF(strProp.getValueSafe());
    }


    // automatic write set of properties to ObjectOutputStream
    public static void writeAllProp(ObjectOutputStream s, Property... properties) throws IOException {
        s.defaultWriteObject();
        for(Property prop:properties) {
            if(prop instanceof IntegerProperty) s.writeInt(((IntegerProperty) prop).intValue());
            else if(prop instanceof StringProperty) s.writeUTF(((StringProperty)prop).getValueSafe());
            else if(prop instanceof ObjectProperty) s.writeObject(((ObjectProperty) prop).get());
            else throw new RuntimeException("Type of object incompatible : " + prop.toString());
        }
    }
}
