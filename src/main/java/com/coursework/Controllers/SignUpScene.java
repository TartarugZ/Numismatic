package com.coursework.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SignUpScene{
    @FXML private TextField createL;
    @FXML private TextField createP;
    @FXML private Label registrationLabel;
    @FXML private Button signUpButton;
    @FXML private Button goBackButton;
    private String fxmlPath = LanguageSelectionScene.fxmlPath;
    private Stage stage;


    public void setStage(Stage stage){
        this.stage=stage;
    }

    @FXML
    protected void signClose() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlPath+"AuthorizationS.fxml"));
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resources/images/icon1.png"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
        AuthorizationScene controller = fxmlLoader.getController();
        controller.setStage(stage);
    }

    @FXML
    protected void registered() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlPath+"AuthorizationS.fxml"));
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resources/images/icon1.png"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
        AuthorizationScene controller = fxmlLoader.getController();
        controller.setStage(stage);
        //добавить сохранение регистрационных данных
    }


    public void initialize() {
        if(LanguageSelectionScene.language.equals("ru")){
            createL.setPromptText("Создайте логин");
            createP.setPromptText("Создайте пароль");
            registrationLabel.setText("Регистрация");
            signUpButton.setText("Создать");
            goBackButton.setText("Назад");
        }

    }

}
