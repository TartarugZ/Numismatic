package com.example.coursework;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;

public class LanguageSelectionScene{


    @FXML private Button lRussian;
    @FXML private Button lEnglish;
    private Stage stage;
    public static String language="en";

    public void setStage(Stage stage){this.stage=stage;}

    public void initialize() {
        lRussian.graphicProperty().setValue(new ImageView("file:resources/images/Russia.png"));
        lEnglish.graphicProperty().setValue(new ImageView("file:resources/images/English.png"));
    }

    private void languageChange() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AuthorizationS.fxml"));
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resources/images/icon1.png"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
        AuthorizationScene controller = fxmlLoader.getController();
        controller.setStage(stage);

    }

    @FXML
    protected void chooseRussian()throws IOException{
        language="ru";
        languageChange();
    }

    @FXML
    protected void chooseEnglish()throws IOException{
        language="en";
        languageChange();
    }

}
