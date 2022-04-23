package com.example.coursework;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class WelcomeScene {


    @FXML
    protected void GoToSearch() throws IOException, StringIndexOutOfBoundsException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FirstS.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setOpacity(1);
        stage.setTitle("Searching Coins");
        stage.getIcons().add(new Image("file:resourses/images/icon1.png"));
        stage.setScene(new Scene(root, 1000, 600));
        stage.showAndWait();

    }
    @FXML
    protected void GoToCollection() throws IOException, StringIndexOutOfBoundsException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SecondS.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setOpacity(1);
        stage.setTitle("My Collection");
        stage.getIcons().add(new Image("file:resourses/images/icon1.png"));
        stage.setScene(new Scene(root, 1000, 600));
        stage.showAndWait();

    }
}
