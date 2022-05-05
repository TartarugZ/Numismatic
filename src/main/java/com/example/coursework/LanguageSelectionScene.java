package com.example.coursework;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;


//доделать нормальный перевод
public class LanguageSelectionScene{

    public static String language="en";
    @FXML private Button lRussian;
    @FXML private Button lEnglish;
    @FXML private Label chooseLabel;
    private Stage stage=Launch.getMainStage();


    public void initialize() {

        lRussian.graphicProperty().setValue(new ImageView("file:resourses/images/Russia.png"));
        lEnglish.graphicProperty().setValue(new ImageView("file:resourses/images/English.png"));
    }

    private void languageChange() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AuthorizationS.fxml"));
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resourses/images/icon1.png"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
    }

    @FXML
    protected void ChooseRussian()throws IOException{
        language="ru";
        languageChange();
    }

    @FXML
    protected void ChooseEnglish()throws IOException{
        language="en";
        languageChange();
    }

}
