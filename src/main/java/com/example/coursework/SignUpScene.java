package com.example.coursework;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpScene{
    @FXML private TextField CreateL;
    @FXML private TextField CreateP;
    @FXML private Label registrationLabel;
    @FXML private Button signUpButton;
    @FXML private Button goBackButton;
    private Stage stage = Launch.getMainStage();

    @FXML
    protected void SignClose() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AuthorizationS.fxml"));

        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resourses/images/icon1.png"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
    }

    @FXML
    protected void Registered() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AuthorizationS.fxml"));

        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resourses/images/icon1.png"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
        //обавить сохранение регистрационных данных
    }


    public void initialize() {
        if(LanguageSelectionScene.language=="ru"){
            CreateL.setPromptText("Создайте логин");
            CreateP.setPromptText("Создайте пароль");
            registrationLabel.setText("Регистрация");
            signUpButton.setText("Создать");
            goBackButton.setText("Назад");
        }

    }
}
