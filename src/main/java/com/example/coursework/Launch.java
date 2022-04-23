package com.example.coursework;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class Launch extends Application {


    public static String linkOnMainPageUcoin = "https://en.ucoin.net";//ссылка на главную страницу сайта uCoin с возможность изменения языка

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Launch.class.getResource("LanguageS.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);

        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resourses/images/icon1.png"));//wewe
        stage.show();
    }


    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {






        newCoinSearch();
        launch();

        //Coin coin =createCoin( "country","currency","value","category",null,"linkUcoin");


    }

    public static Coin createCoin(String country, String currency, String value, String category, Optional <Float> costActual, String linkUcoin){


        Coin coin = new Coin(country);

        return coin;

    };

    public void addCoinToCollection(Collection collection,Coin coin){

        collection.addToCollection(coin);

    };

    public static void newCoinSearch() throws IOException {

        CoinSearcher coinSearcher = new CoinSearcher();



        //Scanner scanCountry = new Scanner(System.in);
        //System.out.println("Enter country");
        //String country = scanCountry.nextLine();*/
        //coinSearcher.smartCoutrySelection(country);
        //coinSearcher.getCounrtyLink("Germany");
        coinSearcher.testgetCounrtyLink();

    }
    public static String replaceAmpersand(String text){

        return text.replace("&amp;","&");

    }



    public static String replaceCatalogToTable(String text){

        return text.replaceAll("catalog","table");

    }
}
