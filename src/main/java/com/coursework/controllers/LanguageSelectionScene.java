package com.coursework.controllers;

import com.coursework.functions.PropertyConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Класс, отвечающий за сцену выбора языка
 */
public class LanguageSelectionScene{

    @FXML private Button lRussian;
    @FXML private Button lEnglish;
    private Stage stage;

    /**
     * путь к fxml файлам
     */
    public static final String FXML_PATH ="file:///"+new File("").getAbsolutePath()+"/src/main/resources/com/coursework/";
    /**
     * путь к файлу перевода
     */
    public static final String TRANSLATION =new File("").getAbsolutePath()+"/src/main/resources/session.properties";

    /** Присваивает окно для отображения
     * @param stage окно для вывода
     */
    public void setStage(Stage stage){this.stage=stage;}

    public void initialize() {
        lRussian.graphicProperty().setValue(new ImageView("file:resources/images/Russia.png"));
        lEnglish.graphicProperty().setValue(new ImageView("file:resources/images/English.png"));
    }

    private void next() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(FXML_PATH +"AuthorizationS.fxml"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 560));
        AuthScene controller = fxmlLoader.getController();
        controller.setStage(stage);
    }

    @FXML
    private void chooseRussian() throws IOException {
        setLanguage("ru");
        next();
    }

    @FXML
    private void chooseEnglish() throws IOException{
        setLanguage("en");
        next();
    }

    /** Определение языка из session.properties
     * @param language язык для записи в properties
     * @throws IOException ошибка при чтении properties
     */
    public static void setLanguage(String language) throws IOException {
        PropertyConnection propertyConnection = new PropertyConnection(TRANSLATION);
        try(FileOutputStream fileOutputStream=new FileOutputStream(TRANSLATION)) {
            propertyConnection.open().setProperty("language", language);
            propertyConnection.open().store(fileOutputStream, "");
            propertyConnection.close();
        }
    }

}
