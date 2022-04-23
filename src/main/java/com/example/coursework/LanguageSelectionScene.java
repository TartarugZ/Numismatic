package com.example.coursework;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LanguageSelectionScene implements Initializable {
    public String language="";
    @FXML private Button LRussian;
    @FXML private Button LEnglish;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LRussian.graphicProperty().setValue(new ImageView("file:resourses/images/Russia.png"));
        LEnglish.graphicProperty().setValue(new ImageView("file:resourses/images/English.png"));
    }

    private void languageChange() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AuthorizationS.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setOpacity(1);
        stage.setTitle("Authorization");
        stage.getIcons().add(new Image("file:resourses/images/icon1.png"));
        stage.setScene(new Scene(root, 800, 600));
        stage.showAndWait();

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
