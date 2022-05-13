package com.example.coursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class Launch extends Application {

    public static String linkOnMainPageUcoin = "https://"+LanguageSelectionScene.language+".ucoin.net";//ссылка на главную страницу сайта uCoin с возможность изменения языка
    private Stage mainStage = new Stage();

    @Override
    public void start(Stage stage) throws IOException {
        stage=mainStage;
        FXMLLoader fxmlLoader = new FXMLLoader(Launch.class.getResource("LanguageS.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resources/images/icon1.png"));//wewe
        LanguageSelectionScene controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.showAndWait();
    }




    public static void main(String[] args){
        launch();
    }

    public static String replaceAmpersand(String text){
        return text.replace("&amp;","&");
    }

    public static String replaceCatalogToTable(String text){
        return text.replaceAll("catalog","table");
    }
}