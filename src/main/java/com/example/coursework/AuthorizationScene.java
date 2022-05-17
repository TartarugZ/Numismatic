package com.example.coursework;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;

public class AuthorizationScene{

    @FXML private ImageView imageAu;
    @FXML private TextField loginText;
    @FXML private TextField passwordText;
    @FXML private Button enterButton;
    @FXML private Button signUpButton;
    @FXML private Button changeButton;
    @FXML private Label withoutRegistrationLabel;
    @FXML private Label signUpLabel;
    @FXML private Label signInLabel;


    private Stage stage;


    public void initialize() {
        imageAu.setImage(new Image("file:resources/images/Coins.png"));

        if(LanguageSelectionScene.language.equals("ru")){
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
    public void setStage(Stage stage){
        this.stage=stage;
    }
    @FXML
    protected void signIn() throws IOException {
        //добавить способ авторизации
        //если авторизация успешна
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FirstS.fxml"));
        sets();
        stage.setScene(new Scene(fxmlLoader.load(), 1000, 600));
        FirstScene controller = fxmlLoader.getController();
        controller.setStage(stage);
        CollectionBase collectionBase = new CollectionBase();
        controller.setCollection(collectionBase);
        controller.setAccount(loginText.getText());
    }

    @FXML
    protected void signUp() throws IOException {
        //добавление нового аккаунта
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUpS.fxml"));
        sets();
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
        SignUpScene controller = fxmlLoader.getController();
        controller.setStage(stage);
    }

    @FXML
    protected void withoutRegistration() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FirstS.fxml"));
        sets();
        stage.setScene(new Scene(fxmlLoader.load(), 1000, 600));
        FirstScene controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.notAuthorized();
    }

    @FXML
    private void changeLanguage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LanguageS.fxml"));
        sets();
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
        LanguageSelectionScene controller = fxmlLoader.getController();
        controller.setStage(stage);
    }

    private void sets(){
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resources/images/icon1.png"));
    }
}
