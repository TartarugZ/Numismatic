package com.example.coursework;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthorizationScene{

    @FXML private ImageView imageAu;
    @FXML private TextField loginText;
    @FXML private TextField passwordText;
    @FXML private Button enterButton;
    @FXML private Button signUpButton;
    @FXML private Button withoutRegistrationButton;
    @FXML private Button changeButton;
    @FXML private Label withoutRegistrationLabel;
    @FXML private Label signUpLabel;
    @FXML private Label signInLabel;


    private Stage stage=Launch.getMainStage();


    public void initialize() {
        imageAu.setImage(new Image("file:resourses/images/Coins.png"));

        if(LanguageSelectionScene.language=="ru"){
            loginText.setPromptText("Логин");
            passwordText.setPromptText("Пароль");
            enterButton.setText("Войти");
            signUpButton.setText("Регистрация");
            withoutRegistrationLabel.setText("Войти без регистрации");
            signUpLabel.setText("У вас ещё нет аккаунта? Тогда создайте его!");
            signInLabel.setText("Авторизация");
            changeButton.setText("Смена языка");
        }
    }

    @FXML
    protected void signIn() throws IOException {
        //добавить способ авторизации
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FirstS.fxml"));
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resourses/images/icon1.png"));
        stage.setScene(new Scene(fxmlLoader.load(), 1000, 600));
    }

    @FXML
    protected void signUp() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUpS.fxml"));
            stage.setTitle("Coin Searcher");
            stage.getIcons().add(new Image("file:resourses/images/icon1.png"));
            stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
    }

    @FXML
    protected void withoutRegistration() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FirstS.fxml"));
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resourses/images/icon1.png"));
        stage.setScene(new Scene(fxmlLoader.load(), 1000, 600));
    }

    @FXML
    private void changeLanguage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LanguageS.fxml"));
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resourses/images/icon1.png"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
    }
}
