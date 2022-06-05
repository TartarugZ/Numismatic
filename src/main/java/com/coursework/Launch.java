package com.coursework;

import com.coursework.controllers.LanguageSelectionScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class Launch extends Application {

    private final Stage mainStage = new Stage();

    @Override
    public void start(Stage stage) throws IOException {
        stage=mainStage;
        FXMLLoader fxmlLoader = new FXMLLoader(Launch.class.getResource("LanguageS.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 560);
        stage.setScene(scene);
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resources/images/icon1.png"));
        LanguageSelectionScene controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.showAndWait();
    }

    public static void main(String[] args){
        launch();
    }

}