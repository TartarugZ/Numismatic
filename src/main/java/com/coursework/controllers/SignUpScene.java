package com.coursework.controllers;

import com.coursework.functions.PropertyConnection;
import com.coursework.serverConnection.ServerWork;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.coursework.controllers.LanguageSelectionScene.TRANSLATION;

public class SignUpScene{
    @FXML private TextField createL;
    @FXML private TextField createP;
    @FXML private Label registrationLabel;
    @FXML private Button signUpButton;
    @FXML private Button goBackButton;
    private String fxmlPath = LanguageSelectionScene.FXML_PATH;
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
        String result=serverWork.userSignUp(createL.getText(),createP.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText(result);
        alert.showAndWait();

        FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlPath+"AuthorizationS.fxml"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
        AuthorizationScene controller = fxmlLoader.getController();
        controller.setStage(stage);
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
        PropertyConnection property=new PropertyConnection(TRANSLATION);
        this.language=property.open().getProperty("language");
        property.close();
    }

}
