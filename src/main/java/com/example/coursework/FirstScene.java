package com.example.coursework;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class FirstScene{
    @FXML  private TextField tf;
    @FXML  private TableView<String> tableview;
    @FXML  private TableView<Collection> tableview2;
    @FXML  private TableColumn<String, String> Coins;
    @FXML  private TableColumn<Collection, String> Collect;
    @FXML  private Button createCollectionButton;
    @FXML  private Button leaveButton;

    private static CollectionBase base = new CollectionBase();
    private final ObservableList<String> dataList = FXCollections.observableArrayList();
    private final ObservableList<Collection> collections= FXCollections.observableArrayList();
    private ArrayList<String> cc = new ArrayList( CoinSearcher.getCountry());
    private Stage stage= Launch.getMainStage();
    int y=0;

    public void initialize() {
        setLanguage();
        searchingTable();
        setCollections();
        chosenCollection();
        recaf();
    }

    private void recaf(){
        Coins.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        Collect.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNameCollection()));
        dataList.addAll(cc);
        collections.addAll(base.getAllCollections());
        tableview2.setItems(collections);
    }

    private void setLanguage(){
        if(LanguageSelectionScene.language=="ru"){
            tf.setPromptText("Поиск");
            Coins.setText("Результаты поиска");
            Collect.setText("Ваши коллекции");
            createCollectionButton.setText("Создать коллекцию");
            leaveButton.setText("К авторизации");
        }
    }

    private void searchingTable(){

        FilteredList<String> filteredCoins = new FilteredList<>(dataList, b -> true);

        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredCoins.setPredicate(coin -> {

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

        SortedList<String> sortedCoins = new SortedList<>(filteredCoins);
        sortedCoins.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedCoins);

    }

    private void chosenCollection(){
        tableview2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2){
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SecondS.fxml"));
                    stage.setTitle("Coin Searcher");
                    stage.getIcons().add(new Image("file:resourses/images/icon1.png"));
                    try {
                        stage.setScene(new Scene(fxmlLoader.load(), 1000, 600));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String name = tableview2.getSelectionModel().getSelectedItem().getNameCollection();
                    SecondScene controller = fxmlLoader.getController();
                    controller.setStage(stage);
                    for(int i=0;i<base.getAllCollections().size();i++){
                        if(base.getAllCollections().get(i).getNameCollection()==name){
                            controller.setCC(base.getAllCollections().get(i));
                        }
                    }
                    controller.setCollectionNameLabel(name);
                }
            }
        });
    }

    private Collection getSavedCollection(String string){//метод для нахождения уже существующей коллекции

        return new Collection(string) ;
    }

    private void setCollections(){
        FilteredList<Collection> filteredCollections = new FilteredList<>(collections, b -> true);

        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredCollections.setPredicate(coin -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                if(newValue.length()>coin.getCollection().size()) return false;
                String lowerCaseFilter = newValue.toLowerCase();

                if (coin.getNameCollection().toLowerCase().startsWith(lowerCaseFilter) ) {
                    return true; // Filter matches first name.
                }else
                    return false; // Does not match.
            });
        });
        SortedList<Collection> sortedCollection = new SortedList<>(filteredCollections);
        sortedCollection.comparatorProperty().bind(tableview2.comparatorProperty());
        tableview2.setItems(sortedCollection);
    }

    @FXML
    private void exit() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AuthorizationS.fxml"));
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resourses/images/icon1.png"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
    }

    @FXML
    private void createCollection() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateCollectionS.fxml"));
        Stage stageEdit = new Stage();
        stageEdit.initModality(Modality.APPLICATION_MODAL);
        stageEdit.setTitle("Coin Searcher");
        stageEdit.getIcons().add(new Image("file:resourses/images/icon1.png"));
        stageEdit.setScene(new Scene(fxmlLoader.load(), 300, 200));
        CreateCollectionScene controller = fxmlLoader.getController();
        controller.setStage(stageEdit);
        stageEdit.showAndWait();
    }

    public static CollectionBase getBase(){
        return base;
    }

    public void setTableview2(TableView<Collection> tableview2) {
        this.tableview2.refresh();
    }
}
