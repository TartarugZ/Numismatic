package com.example.coursework;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthorizationScene implements Initializable {

    @FXML
    private ImageView ImageAu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image=new Image("file:resourses/images/Coins.png");
        ImageAu.setImage(new Image("file:resourses/images/Coins.png"));
    }
    @FXML
    protected void SignIn() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WelcomeS.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setOpacity(1);
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resourses/images/icon1.png"));
        stage.setScene(new Scene(root, 800, 600));
        stage.showAndWait();
    }

    @FXML
    protected void SignUp() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUpS.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setOpacity(1);
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resourses/images/icon1.png"));
        stage.setScene(new Scene(root, 800, 600));
        stage.showAndWait();
    }
}
