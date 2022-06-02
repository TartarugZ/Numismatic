package com.coursework.Serialization;

import com.coursework.Objects.CollectionBase;

import java.io.*;

public class FileWork {
    private FileInputStream fis;
    private FileOutputStream fos;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private  File file;

    public CollectionBase read(String string) throws IOException, ClassNotFoundException {
        String path = new File("").getAbsolutePath() + "/src/main/resources/UsersCollections/" + string + ".ser";
        file=new File(path);
        CollectionBase collectionBase;
        if(file.length()!=0) {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            collectionBase = (CollectionBase) ois.readObject();
            ois.close();
            fis.close();
            return collectionBase;
        }else return new CollectionBase();

    }

    public void write(CollectionBase collectionBase, String string) throws IOException {
        String path = new File("").getAbsolutePath() + "/src/main/resources/UsersCollections/" + string + ".ser";

            file=new File(path);
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(collectionBase);
            oos.flush();
            oos.close();
            fos.close();


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
