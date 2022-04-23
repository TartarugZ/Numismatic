package com.example.coursework;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SignUpScene {
    @FXML private TextField CreateL;
    @FXML private TextField CreateP;

    @FXML
    protected void Registered() throws IOException {

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
}
