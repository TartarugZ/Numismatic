package com.example.coursework;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWork {
    private FileInputStream fis;
    private FileOutputStream fos;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private  File file= new File("");

    public CollectionBase read(String string) throws IOException, ClassNotFoundException {
        String path=new File("").getAbsolutePath()+"/src/main/resources/UsersCollections/"+string+".ser";
        CollectionBase collectionBase = new CollectionBase();
        if(file.length()!=0) {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            collectionBase = (CollectionBase) ois.readObject();
            fis.close();
            ois.close();
        }else System.out.println("Файл пуст");

        return collectionBase;
    }

    public void write(CollectionBase collectionBase, String string) throws IOException {
        String path = new File("").getAbsolutePath() + "/src/main/resources/UsersCollections/" + string + ".ser";

            file=new File(path);
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(collectionBase);
            oos.flush();
            fos.close();
            oos.close();

    }

    public void fileCreation(String string) throws IOException {
        String path=new File("").getAbsolutePath()+"/src/main/resources/UsersCollections/"+string+".ser";
        file=new File(path);
        if (file.createNewFile()){
            System.out.println("File is created!");
        }
        else{
            System.out.println("File already exists.");
        }
    }
}
