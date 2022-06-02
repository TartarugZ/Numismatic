package com.coursework.Controllers;

import com.coursework.Functions.PropertyConnection;
import com.coursework.ServerConnection.ServerWork;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.coursework.Controllers.LanguageSelectionScene.translation;

public class SignUpScene{
    @FXML private TextField createL;
    @FXML private TextField createP;
    @FXML private Label registrationLabel;
    @FXML private Button signUpButton;
    @FXML private Button goBackButton;
    private String fxmlPath = LanguageSelectionScene.fxmlPath;
    private Stage stage;
    private String language;

    public void setStage(Stage stage){
        this.stage=stage;
    }

    @FXML
    protected void signClose() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlPath+"AuthorizationS.fxml"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
        AuthorizationScene controller = fxmlLoader.getController();
        controller.setStage(stage);
    }

    @FXML
    protected void registered() throws IOException {
        ServerWork serverWork = new ServerWork();
        serverWork.userSignUp(createL.getText(),createP.getText());
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlPath+"AuthorizationS.fxml"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
        AuthorizationScene controller = fxmlLoader.getController();
        controller.setStage(stage);
        //добавить сохранение регистрационных данных
    }

    public void initialize() throws IOException {
        setTranslation();
    }

    private void setTranslation() throws IOException {
        setLanguage();
        PropertyConnection p=new PropertyConnection(new File("")
                .getAbsolutePath()+"/src/main/resources/translation_"+language+".properties");
        createL.setPromptText(p.open().getProperty("createLSU"));
        createP.setPromptText(p.open().getProperty("createPSU"));
        registrationLabel.setText(p.open().getProperty("registrationLabelSU"));
        signUpButton.setText(p.open().getProperty("signUpButtonSU"));
        goBackButton.setText(p.open().getProperty("goBackButtonSU"));
        p.close();
    }

    private void setLanguage() throws IOException {
        PropertyConnection property=new PropertyConnection(translation);
        this.language=property.open().getProperty("language");
        property.close();
    }

}
