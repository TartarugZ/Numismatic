package com.coursework.Controllers;

import com.coursework.*;
import com.coursework.Serialization.FileWork;
import com.coursework.ServerConnection.SearchInformation;
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

import static com.coursework.Controllers.LanguageSelectionScene.translation;

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
    @FXML private  ComboBox<String> cbCountry;
    @FXML private  ComboBox<String> cbYears;
    @FXML private  ComboBox<String> cbCurrency;
    @FXML private  ComboBox<String> cbValue;
    @FXML private  ComboBox<String> cbMint;
    @FXML private Button search;
    private String fxmlPath = LanguageSelectionScene.fxmlPath;
    private FileWork fileWork = new FileWork();
    private String language;

    private CollectionBase collectionBase = new CollectionBase();
    private CollectionBase localCollectionBase= new CollectionBase();
    private final ObservableList<String> dataList = FXCollections.observableArrayList();
    private final ObservableList<Collection> collections= FXCollections.observableArrayList();
    private ServerWork serverWork=new ServerWork();
    
    private ArrayList<String> cc = new ArrayList();
    private Stage stage;

    public void initialize() throws IOException {
        setTranslation();
        searchingTable();
        chosenCollection();
        setComboBoxes();
    }


    public void setComboBoxes() throws IOException {
        PropertyConnection property=new PropertyConnection(translation);
        String translationPath=new File("").getAbsolutePath()+"/src/main/resources/translation_"+property.open().getProperty("language")+".properties";

        PropertyConnection propertyConnection=new PropertyConnection(translationPath);
        ArrayList<String> years=new ArrayList<>();
        years.add("Austria");
        years.add("Russia");
        ObservableList<String> forYears=FXCollections.observableArrayList(years);
        cbCountry.setItems(serverWork.getCountries(property.open().getProperty("language")));
        cbYears.setItems(forYears);
        ComboBoxListener<String> helper1=new ComboBoxListener<>(cbCountry);
        ComboBoxListener<String> helper2=new ComboBoxListener<>(cbYears);
        ComboBoxListener<String> helper3=new ComboBoxListener<>(cbCurrency);
        ComboBoxListener<String> helper4=new ComboBoxListener<>(cbValue);
        ComboBoxListener<String> helper5=new ComboBoxListener<>(cbMint);
        property.close();
    }
 public void setStage(Stage stage){
        this.stage=stage;
 }

    public void setLanguage() throws IOException {
        PropertyConnection property=new PropertyConnection(translation);
        this.language=property.open().getProperty("language");
        property.close();
    }
    private void setTranslation() throws IOException {
        setLanguage();
        PropertyConnection p=new PropertyConnection(new File("")
                .getAbsolutePath()+"/src/main/resources/translation_"+language+".properties");
            tf.setPromptText(p.open().getProperty("tfF"));
            coins.setText(p.open().getProperty("coinsF"));
            collect.setText(p.open().getProperty("collectF"));
            createCollectionButton.setText(p.open().getProperty("createCollectionButtonF"));
            leaveButton.setText(p.open().getProperty("leaveButtonF"));
            add.setText(p.open().getProperty("addF"));
            addCollectionButton.setText(p.open().getProperty("addCollectionButtonF"));
            addCollectionLabel.setText(p.open().getProperty("addCollectionLabelF"));
            cbCountry.setPromptText(p.open().getProperty("cbCountryF"));
            cbYears.setPromptText(p.open().getProperty("cbYearsF"));
            cbCurrency.setPromptText(p.open().getProperty("cbCurrencyF"));
            cbValue.setPromptText(p.open().getProperty("cbValueF"));
            cbMint.setPromptText(p.open().getProperty("cbMintF"));
            search.setText(p.open().getProperty("searchF"));
            p.close();
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
 @FXML
 public void searching(){
     SearchInformation searchInformation=new SearchInformation();
     searchInformation.setCountry(cbCountry.getSelectionModel().getSelectedItem());
     searchInformation.setYear(cbYears.getSelectionModel().getSelectedItem());
     searchInformation.setCurrency(cbCurrency.getSelectionModel().getSelectedItem());
     searchInformation.setValue(cbValue.getSelectionModel().getSelectedItem());
     searchInformation.setMint(cbMint.getSelectionModel().getSelectedItem());

        
 }


}
