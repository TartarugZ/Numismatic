package com.coursework.controllers;

import com.coursework.objects.CollectionBase;
import com.coursework.functions.PropertyConnection;
import com.coursework.functions.FileWork;
import com.coursework.server_connection.ServerWork;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.coursework.controllers.LanguageSelectionScene.FXML_PATH;
import static com.coursework.controllers.LanguageSelectionScene.TRANSLATION;

/**
 * Класс отвечающий за сцену аутентификации
 */
public class AuthScene {

    @FXML private ImageView imageAu;
    @FXML private TextField loginText;
    @FXML private PasswordField passwordText;
    @FXML private Button enterButton;
    @FXML private Button signUpButton;
    @FXML private Button changeButton;
    @FXML private Label signUpLabel;
    @FXML private Label signInLabel;


    private String language;
    private Stage stage;


    public void initialize() throws IOException {
        imageAu.setImage(new Image("file:resources/images/Coins.png"));
        setTranslation();
    }

    /** Присваивает окно для отображения
     * @param stage окно для вывода
     */
    public void setStage(Stage stage){
        this.stage=stage;
    }

    private void setTranslation() throws IOException{
        setLanguage();
        PropertyConnection p=new PropertyConnection(new File("")
                .getAbsolutePath()+"/src/main/resources/translation_"+language+".properties");
        loginText.setPromptText(p.open().getProperty("loginTextAuth"));
        passwordText.setPromptText(p.open().getProperty("passwordTextAuth"));
        enterButton.setText(p.open().getProperty("enterButtonAuth"));
        signUpButton.setText(p.open().getProperty("signUpButtonAuth"));
        signUpLabel.setText(p.open().getProperty("signUpLabelAuth"));
        signInLabel.setText(p.open().getProperty("signInLabelAuth"));
        changeButton.setText(p.open().getProperty("changeButtonAuth"));
        p.close();
    }
    @FXML
    private void signIn() throws IOException {
        ServerWork serverWork=new ServerWork();
        String b=serverWork.login(loginText.getText(),passwordText.getText());
       if(b.equals("200")) {
           FileWork fileWork = new FileWork();
           fileWork.fileCreation(loginText.getText());
           FXMLLoader fxmlLoader = new FXMLLoader(new URL(FXML_PATH + "FirstS.fxml"));
           stage.setScene(new Scene(fxmlLoader.load(), 1200, 750));
           FirstScene controller = fxmlLoader.getController();
           controller.setStage(stage);
           CollectionBase collectionBase = serverWork.getCollections();
           controller.setCollection(collectionBase);
           controller.setAccount(loginText.getText());
       }else{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("");
           alert.setHeaderText(null);
           alert.setContentText("Wrong login or password");
           alert.showAndWait();
       }
    }

    @FXML
    private void signUp() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(FXML_PATH+"SignUpS.fxml"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
        SignUpScene controller = fxmlLoader.getController();
        controller.setStage(stage);
    }

    @FXML
    private void changeLanguage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(FXML_PATH+"LanguageS.fxml"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
        LanguageSelectionScene controller = fxmlLoader.getController();
        controller.setStage(stage);
    }

    /**Определение языка из session.properties
     * @throws IOException ошибка при чтении property
     */
    public void setLanguage() throws IOException {
        PropertyConnection property=new PropertyConnection(TRANSLATION);
        this.language=property.open().getProperty("language");
        property.close();
    }

}
