package com.coursework.Controllers;

import com.coursework.*;
import com.coursework.Serialization.FileWork;
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

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import com.coursework.ServerConnection.ServerWork;

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
    @FXML private Button addCollectionButton;
    @FXML private  Label addCollectionLabel;
    @FXML private  ComboBox cbCountry;
    @FXML private  ComboBox cbYears;
    @FXML private  ComboBox cbCost;
    @FXML private  ComboBox cbCurrency;
    @FXML private  ComboBox cbValue;
    @FXML private  ComboBox cbCategory;
    @FXML private  ComboBox cbMint;
    private String fxmlPath = LanguageSelectionScene.fxmlPath;
    private FileWork fileWork = new FileWork();

    private CollectionBase collectionBase = new CollectionBase();
    private CollectionBase localCollectionBase= new CollectionBase();
    private final ObservableList<String> dataList = FXCollections.observableArrayList();
    private final ObservableList<Collection> collections= FXCollections.observableArrayList();
    private ServerWork serverWork=new ServerWork();

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

    public void initialize() throws IOException, ClassNotFoundException {
        setLanguage();
        searchingTable();
        chosenCollection();
        setComboBoxes();
    }

    public void setComboBoxes(){

        ObservableList<String> forCountries=FXCollections.observableArrayList(serverWork.getCountries());
        ArrayList<String> years=new ArrayList<>();
        years.add("Austria");
        years.add("Russia");
        ObservableList<String> forYears=FXCollections.observableArrayList(years);
        ArrayList<String> cost=new ArrayList<>();
        cost.add("Austria");
        cost.add("Russia");
        ObservableList<String> forCost=FXCollections.observableArrayList(cost);
        cbCountry.setItems(forCountries);
        cbCost.setItems(forCost);
        cbYears.setItems(forYears);
        ComboBoxListener helper1=new ComboBoxListener<>(cbCountry);
        ComboBoxListener<String> helper2=new ComboBoxListener<>(cbYears);
        ComboBoxListener<String> helper3=new ComboBoxListener<>(cbCost);

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
            nickname.setText("Без входа в аккаунт");
            addCollectionButton.setText("Добавить");
            addCollectionLabel.setText("Добавить локальную коллекцию");
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
                    FXMLLoader fxmlLoader = null;
                    try {
                        fxmlLoader = new FXMLLoader(new URL(fxmlPath+"SecondS.fxml"));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    sets();
                    try {
                        stage.setScene(new Scene(fxmlLoader.load(), 1000, 600));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String name = tableview2.getSelectionModel().getSelectedItem().getNameCollection();
                    SecondScene controller = fxmlLoader.getController();
                    controller.setStage(stage);
                    controller.setCollectionBase(collectionBase, localCollectionBase);
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


    @FXML
    private void exit() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlPath+"AuthorizationS.fxml"));
        sets();
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
        AuthorizationScene controller = fxmlLoader.getController();
        controller.setStage(stage);
    }

    @FXML
    private void createCollection() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlPath+"CreateCollectionS.fxml"));
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

    @FXML
    private void addLocalCollection() throws IOException, ClassNotFoundException {
        localCollectionBase=fileWork.read(nickname.getText());
        System.out.println(fileWork.read(nickname.getText()));
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlPath+"AddCollectionS.fxml"));
        Stage stageEdit = new Stage();
        stageEdit.initModality(Modality.APPLICATION_MODAL);
        stageEdit.setTitle("Coin Searcher");
        stageEdit.getIcons().add(new Image("file:resources/images/icon1.png"));
        stageEdit.setScene(new Scene(fxmlLoader.load(), 270, 260));
        AddCollectionScene controller = fxmlLoader.getController();
        controller.setStage(stageEdit);
        Collection collection=new Collection(" ");
        controller.setCollection(collection);
        controller.setCollectionBase(localCollectionBase, collectionBase);
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
            FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlPath+"AddCoinS.fxml"));
            Stage stageEdit = new Stage();
            stageEdit.initModality(Modality.APPLICATION_MODAL);
            stageEdit.setTitle("Coin Searcher");
            stageEdit.getIcons().add(new Image("file:resources/images/icon1.png"));
            stageEdit.setScene(new Scene(fxmlLoader.load(), 280, 250));
            AddCoinScene controller = fxmlLoader.getController();
            controller.setCollectionBase(collectionBase, new Coin(), stageEdit );
            stageEdit.showAndWait();

        }
 }

}
