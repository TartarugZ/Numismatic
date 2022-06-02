package com.coursework.Controllers;

import com.coursework.Objects.CollectionBase;
import com.coursework.Functions.PropertyConnection;
import com.coursework.Serialization.FileWork;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.coursework.Controllers.LanguageSelectionScene.translation;

public class AuthorizationScene{

    @FXML private ImageView imageAu;
    @FXML private TextField loginText;
    @FXML private TextField passwordText;
    @FXML private Button enterButton;
    @FXML private Button signUpButton;
    @FXML private Button changeButton;
    @FXML private Label signUpLabel;
    @FXML private Label signInLabel;
    private String fxmlPath= LanguageSelectionScene.fxmlPath;

    private String language;
    private Stage stage;

    public void initialize() throws IOException {
        imageAu.setImage(new Image("file:resources/images/Coins.png"));
        setTranslation();
    }
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
    protected void signIn() throws IOException {
        FileWork fileWork = new FileWork();
        fileWork.fileCreation(loginText.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlPath+"FirstS.fxml"));
        stage.setScene(new Scene(fxmlLoader.load(), 1200, 750));
        FirstScene controller = fxmlLoader.getController();
        controller.setStage(stage);
        CollectionBase collectionBase = new CollectionBase();
        controller.setCollection(collectionBase);
        controller.setAccount(loginText.getText());
    }

    @FXML
    protected void signUp() throws IOException {
        //добавление нового аккаунта
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlPath+"SignUpS.fxml"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
        SignUpScene controller = fxmlLoader.getController();
        controller.setStage(stage);
    }

    @FXML
    private void changeLanguage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlPath+"LanguageS.fxml"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
        LanguageSelectionScene controller = fxmlLoader.getController();
        controller.setStage(stage);
    }

    public void setLanguage() throws IOException {
        PropertyConnection property=new PropertyConnection(translation);
        this.language=property.open().getProperty("language");
        property.close();
    }

}
