package com.coursework.functions;

import com.coursework.objects.CollectionBase;
import java.io.*;
import java.util.logging.Logger;

/**
 * Класс для сериализации, десериализации и создания файла пользователя
 */
public class FileWork {
    private  File file;
    Logger log = Logger.getLogger(FileWork.class.getName());
    private String toFiles="/src/main/resources/UsersCollections/";

    /**чтение файла
     * @param string имя пользователя
     * @return возвращает CollectionBase, то есть объект с массивом коллекций
     * @throws IOException '
     * @throws ClassNotFoundException '
     */
    public CollectionBase read(String string) throws IOException, ClassNotFoundException {
        String path = new File("").getAbsolutePath() + toFiles + string + ".ser";
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

    /**запись файла
     * @param collectionBase база коллекций
     * @param string имя пользователя
     * @throws IOException '
     */
    public void write(CollectionBase collectionBase, String string) throws IOException {
        String path = new File("").getAbsolutePath() + toFiles + string + ".ser";
        file=new File(path);
        try(FileOutputStream fos = new FileOutputStream(path)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(collectionBase);
            oos.flush();
            oos.close();
            log.info("Local save: success");
        }
    }

    /**создание файла
     * @param string имя пользователя
     * @throws IOException '
     */
    public void fileCreation(String string) throws IOException {
        String path=new File("").getAbsolutePath()+toFiles+string+".ser";
        file=new File(path);
        if (file.createNewFile()){
            log.info("File is created!");
        }
        else{
           log.info("File already exists.");
        }
    }
}
