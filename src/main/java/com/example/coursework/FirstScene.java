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
    @FXML  private TableColumn<String, String> coins;
    @FXML  private TableColumn<Collection, String> collect;
    @FXML  private Button createCollectionButton;
    @FXML  private Button leaveButton;
    @FXML  private Button add;
    @FXML private  Label nickname;

    private CollectionBase collectionBase = new CollectionBase();
    private final ObservableList<String> dataList = FXCollections.observableArrayList();
    private final ObservableList<Collection> collections= FXCollections.observableArrayList();

    {
        try {
            //чисто чтобы первая таблица была не пустой и можно было бы проверить работет ли поиск и я ничего не поломал
            CoinSearcher coinSearcher = new CoinSearcher();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> cc = new ArrayList(CoinSearcher.getCountry());
    private Stage stage;

    public void initialize() {
        setLanguage();
        searchingTable();
        chosenCollection();
    }
 public void setStage(Stage stage){
        this.stage=stage;
 }


    private void setLanguage(){
        if(LanguageSelectionScene.language.equals("ru")){
            tf.setPromptText("Поиск");
            coins.setText("Результаты поиска");
            collect.setText("Ваши коллекции");
            createCollectionButton.setText("Создать коллекцию");
            leaveButton.setText("К авторизации");
            add.setText("Добавить в коллекцию");
        }
    }

    public void setAccount(String string){
        this.nickname.setText(string);
    }

    private void searchingTable(){
        coins.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        dataList.addAll(cc);
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
                    sets();
                    try {
                        stage.setScene(new Scene(fxmlLoader.load(), 1000, 600));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String name = tableview2.getSelectionModel().getSelectedItem().getNameCollection();
                    SecondScene controller = fxmlLoader.getController();
                    controller.setStage(stage);
                    controller.setCollectionBase(collectionBase);
                    controller.setNickname(nickname.getText());
                    for(int i=0;i<collectionBase.getAllCollections().size();i++){
                        if(collectionBase.getAllCollections().get(i).getNameCollection().equals(name)){
                            controller.setCC(collectionBase.getAllCollections().get(i));
                        }
                    }
                    controller.setCollectionNameLabel(name);
                }
            }
        });
    }

    private boolean chosenCoin(){
        return tableview.getSelectionModel().getSelectedIndex() >= 0;
    }

    public void setCollection(CollectionBase base){

        collectionBase=base;
        collect.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNameCollection()));
        collections.addAll(collectionBase.getAllCollections());
        tableview2.setItems(collections);
    }
    public void disableTable2(){
        tableview2.setDisable(true);
    }


    @FXML
    private void exit() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AuthorizationS.fxml"));
        sets();
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
        AuthorizationScene controller = fxmlLoader.getController();
        controller.setStage(stage);
    }

    @FXML
    private void createCollection() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateCollectionS.fxml"));
        Stage stageEdit = new Stage();
        stageEdit.initModality(Modality.APPLICATION_MODAL);
        stageEdit.setTitle("Coin Searcher");
        stageEdit.getIcons().add(new Image("file:resources/images/icon1.png"));
        stageEdit.setScene(new Scene(fxmlLoader.load(), 300, 200));
        CreateCollectionScene controller = fxmlLoader.getController();
        controller.setStage(stageEdit);
        Collection collection=new Collection(" ");
        controller.setCollection(collection);
        stageEdit.showAndWait();
        if(controller.isClosed()){
            tableview2.getItems().add(collection);
            collectionBase.addCollection(collection);
        }
    }

    private void sets(){
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resources/images/icon1.png"));
    }
    @FXML
 private  void  adding() throws IOException {
        if(chosenCoin()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCoinS.fxml"));
            Stage stageEdit = new Stage();
            stageEdit.initModality(Modality.APPLICATION_MODAL);
            stageEdit.setTitle("Coin Searcher");
            stageEdit.getIcons().add(new Image("file:resources/images/icon1.png"));
            stageEdit.setScene(new Scene(fxmlLoader.load(), 280, 250));
            AddCoinScene controller = fxmlLoader.getController();
            controller.setCollectionBase(collectionBase, new Coin(tableview.getSelectionModel().getSelectedItem()), stageEdit );
            stageEdit.showAndWait();
        }
 }
}
