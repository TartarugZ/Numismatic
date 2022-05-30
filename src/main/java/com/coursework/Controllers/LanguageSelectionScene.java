package com.coursework.Controllers;

import com.coursework.PropertyConnection;
import com.coursework.ServerConnection.ServerWork;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class LanguageSelectionScene{


    @FXML private Button lRussian;
    @FXML private Button lEnglish;
    private Stage stage;
    public static String fxmlPath="file:///"+new File("").getAbsolutePath()+"/src/main/resources/com/coursework/";
    public static String translation=new File("").getAbsolutePath()+"/src/main/resources/language.properties";

    public void setStage(Stage stage){this.stage=stage;}

    public void initialize() {
        lRussian.graphicProperty().setValue(new ImageView("file:resources/images/Russia.png"));
        lEnglish.graphicProperty().setValue(new ImageView("file:resources/images/English.png"));
    }

    private void languageChange() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlPath+"AuthorizationS.fxml"));
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resources/images/icon1.png"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 560));
        AuthorizationScene controller = fxmlLoader.getController();
        controller.setStage(stage);

    }

    @FXML
    protected void chooseRussian() throws IOException, InterruptedException {
        setLanguage("ru");
        languageChange();
        /*
        ServerWork serverWork= new ServerWork();
        serverWork.collectionCreate("Ishtar","j");
        */
    }

    @FXML
    protected void chooseEnglish() throws IOException{
        setLanguage("en");
        languageChange();
    }

    public static void setLanguage(String language) throws IOException {
        PropertyConnection propertyConnection=new PropertyConnection(translation);
        FileOutputStream fileOutputStream=new FileOutputStream(translation);
        propertyConnection.open().setProperty("language",language);
        propertyConnection.open().store(fileOutputStream,"");
        propertyConnection.close();
    }


}
