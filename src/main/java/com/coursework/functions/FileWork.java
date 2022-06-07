package com.coursework.functions;

import com.coursework.objects.CollectionBase;

import java.io.*;
import java.util.logging.Logger;

public class FileWork {
    private  File file;
    Logger log = Logger.getLogger(FileWork.class.getName());

    public CollectionBase read(String string) throws IOException, ClassNotFoundException {
        String path = new File("").getAbsolutePath() + "/src/main/resources/UsersCollections/" + string + ".ser";
        file=new File(path);
        CollectionBase collectionBase;
        if(file.length()!=0) {
            try(FileInputStream fis = new FileInputStream(path)){
                ObjectInputStream ois = new ObjectInputStream(fis);
                collectionBase = (CollectionBase) ois.readObject();
                ois.close();
            }
            return collectionBase;
        }else return new CollectionBase();

    }

    public void write(CollectionBase collectionBase, String string) throws IOException {
        String path = new File("").getAbsolutePath() + "/src/main/resources/UsersCollections/" + string + ".ser";
        file=new File(path);
        try(FileOutputStream fos = new FileOutputStream(path)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(collectionBase);
            oos.flush();
            oos.close();
        }
    }

    public void fileCreation(String string) throws IOException {
        String path=new File("").getAbsolutePath()+"/src/main/resources/UsersCollections/"+string+".ser";
        file=new File(path);
        if (file.createNewFile()){
            log.info("File is created!");
        }
        else{
           log.info("File already exists.");
        }
    }
}
