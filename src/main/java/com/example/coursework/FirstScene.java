package com.example.coursework;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class FirstScene{
    @FXML  private TextField tf;
    @FXML  private TableView<String> tableview;
    @FXML  private TableView<String> tableview2;
    @FXML  private TableColumn<String, String> Coins;
    @FXML  private TableColumn<String, String> Collect;
    @FXML  private Button goCollectionButton;
    @FXML  private Button createCollectionButton;

    private final ObservableList<String> dataList = FXCollections.observableArrayList();
    private final ObservableList<String> Collections= FXCollections.observableArrayList();
    private ArrayList<String> CC= new ArrayList( CoinSearcher.getCountry());
    private Stage stage= Launch.getMainStage();


    @FXML
    protected void goCollection() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SecondS.fxml"));
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resourses/images/icon1.png"));
        stage.setScene(new Scene(fxmlLoader.load(), 1000, 600));
    }


    public void initialize() {

    if(LanguageSelectionScene.language=="ru"){
        tf.setPromptText("Поиск");
        Coins.setText("Результаты поиска");
        Collect.setText("Ваши коллекции");
        goCollectionButton.setText("К коллекции");
        createCollectionButton.setText("Создать коллекцию");
    }

        //Collections.sort(CC);
       Coins.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
       dataList.addAll(CC);

        FilteredList<String> filteredDataCountry = new FilteredList<>(dataList, b -> true);

        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataCountry.setPredicate(coin -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                if(newValue.length()>coin.length()) return false;
                String lowerCaseFilter = newValue.toLowerCase();



                if (coin.toLowerCase().substring(0,lowerCaseFilter.length()).equals(lowerCaseFilter) ) {
                    return true; // Filter matches first name.
                }else
                    return false; // Does not match.
            });
        });


        SortedList<String> sortedDataCountry = new SortedList<>(filteredDataCountry);

        sortedDataCountry.comparatorProperty().bind(tableview.comparatorProperty());

        tableview.setItems(sortedDataCountry);


    }


}
