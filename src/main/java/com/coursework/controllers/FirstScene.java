package com.coursework.controllers;

import com.coursework.functions.ComboBoxListener;
import com.coursework.functions.PropertyConnection;
import com.coursework.objects.Collection;
import com.coursework.objects.CollectionBase;
import com.coursework.serialization.FileWork;
import com.coursework.serverConnection.CoinDTO;
import com.coursework.serverConnection.CountryDenominationInfo;
import com.coursework.serverConnection.SearchInformation;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import com.coursework.serverConnection.ServerWork;
import javafx.util.Duration;

import static com.coursework.controllers.LanguageSelectionScene.FXML_PATH;
import static com.coursework.controllers.LanguageSelectionScene.TRANSLATION;

public class FirstScene{
    @FXML  private TableView<CoinDTO> tableview;
    @FXML  private TableView<Collection> tableview2;
    @FXML  private TableColumn<CoinDTO, String> coins;
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

    private FileWork fileWork = new FileWork();
    private String language;

    private CollectionBase collectionBase = new CollectionBase();
    private CollectionBase localCollectionBase= new CollectionBase();
    private ObservableList<Collection> collections= FXCollections.observableArrayList();
    private ServerWork serverWork=new ServerWork();
    private CountryDenominationInfo cdi=new CountryDenominationInfo();

    private Stage stage;

    public void initialize() throws IOException, ClassNotFoundException {
        setTranslation();
        chosenCollection();
        setComboBoxes();
        linkCoin();
        extraInfo();
    }

    public void extraInfo(){
        tableview.setRowFactory(tv -> new TableRow<>() {
            Tooltip tooltip = new Tooltip();
            @Override
            public void updateItem(CoinDTO coinDTO, boolean empty) {
                super.updateItem(coinDTO, empty);
                if (coinDTO == null) {
                    setTooltip(null);
                } else {
                    setTooltip(tooltip);
                    tooltip.setPrefWidth(200);
                    tooltip.setWrapText(true);
                    tooltip.setText(coinDTO.getInfo());
                    tooltip.setShowDuration(Duration.minutes(1));
                }
            }
        });
    }


    public void setComboBoxes() throws IOException {
        PropertyConnection property=new PropertyConnection(TRANSLATION);

        cbCountry.setItems(serverWork.getCountries(property.open().getProperty("language")));
        property.close();

        cbCountry.setOnAction(actionEvent -> {
            try {
                if (countryExists()) {
                    cdi = serverWork.loadValueAndCurrency(cbCountry.getSelectionModel().getSelectedItem(), language);
                    cbValue.setItems(FXCollections.observableArrayList(cdi.getValue()));
                    cbCurrency.setItems(FXCollections.observableArrayList(cdi.getCurrency()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        cbValue.setOnAction(actionEvent ->
                cbCurrency.setItems(FXCollections.observableArrayList(cdi.getSingleCurrency(cbValue.getSelectionModel().getSelectedItem()))));

        cbCurrency.setOnAction(actionEvent ->
                cbValue.setItems(FXCollections.observableArrayList(cdi.getSingleValue(cbCurrency.getSelectionModel().getSelectedItem()))));

        ComboBoxListener<String> helper=new ComboBoxListener<>(cbCountry);
        cbYears.setEditable(true);
        cbValue.setEditable(true);
        cbCurrency.setEditable(true);
        cbMint.setEditable(true);

    }
 public void setStage(Stage stage){
        this.stage=stage;
 }

    public void sets() throws IOException, ClassNotFoundException {
        PropertyConnection property=new PropertyConnection(TRANSLATION);
        this.language=property.open().getProperty("language");
        String a=property.open().getProperty("username");
        localCollectionBase=fileWork.read(a);
        property.close();
    }
    private void setTranslation() throws IOException, ClassNotFoundException {
        sets();
        PropertyConnection p=new PropertyConnection(new File("")
                .getAbsolutePath()+"/src/main/resources/translation_"+language+".properties");
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


    private void chosenCollection(){
        tableview2.setOnMouseClicked(event -> {
            if(event.getClickCount()==2){
                FXMLLoader fxmlLoader = null;
                try {
                    fxmlLoader = new FXMLLoader(new URL(FXML_PATH+"SecondS.fxml"));
                    stage.setScene(new Scene(fxmlLoader.load(), 1200, 750));

                } catch (IOException e ) {
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
        });
    }

    private void linkCoin(){
        tableview.setOnMouseClicked(event -> {
            if(event.getClickCount()==2){
                Desktop desktop=Desktop.getDesktop();
                try {
                    desktop.browse(new URL("https://"+language+".ucoin.net" +tableview.getSelectionModel().getSelectedItem().getLinkUcoin()).toURI());
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
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
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(FXML_PATH+"AuthorizationS.fxml"));
        stage.setScene(new Scene(fxmlLoader.load(), 800, 600));
        AuthorizationScene controller = fxmlLoader.getController();
        controller.setStage(stage);
    }

    @FXML
    private void createCollection() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(FXML_PATH+"CreateCollectionS.fxml"));
        Stage stageEdit = new Stage();
        stageEdit.initModality(Modality.APPLICATION_MODAL);
        stageEdit.setTitle("Coin Searcher");
        stageEdit.getIcons().add(new Image("file:resources/images/icon1.png"));
        stageEdit.setScene(new Scene(fxmlLoader.load(), 300, 200));
        CreateCollectionStage controller = fxmlLoader.getController();
        controller.setStage(stageEdit);
        Collection collection=new Collection(" ");
        controller.setCollection(collection);
        stageEdit.showAndWait();
        if(controller.isClosed()){
            int y=0;
            for(int i=0;i<collectionBase.getAllCollections().size();i++){
                if(collection.getNameCollection().equals(collectionBase.getAllCollections().get(i).getNameCollection())) y++;
            }
            for(int i=0;i<localCollectionBase.getAllCollections().size();i++){
                if(collection.getNameCollection().equals(localCollectionBase.getAllCollections().get(i).getNameCollection())) y++;
            }

            if(y>0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Coolection with this name already exists");
                alert.showAndWait();
            }else {
                collectionBase.addCollection(collection);
                tableview2.getItems().clear();
                collections.addAll(collectionBase.getAllCollections());
                tableview2.setItems(collections);
            }

        }
    }

    @FXML
    private void addLocalCollection() throws IOException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(FXML_PATH+"AddCollectionS.fxml"));
        Stage stageEdit = new Stage();
        stageEdit.initModality(Modality.APPLICATION_MODAL);
        stageEdit.setTitle("Coin Searcher");
        stageEdit.getIcons().add(new Image("file:resources/images/icon1.png"));
        stageEdit.setScene(new Scene(fxmlLoader.load(), 270, 260));
        AddCollectionStage controller = fxmlLoader.getController();
        controller.setStage(stageEdit);
        controller.setCollectionBase(localCollectionBase, collectionBase);
        stageEdit.showAndWait();
        if(controller.isClosed()){
            tableview2.getItems().clear();
            collections.addAll(collectionBase.getAllCollections());
            tableview2.setItems(collections);
        }
    }

    @FXML
 private  void addingCoin() throws IOException {
        if(chosenCoin()) {
            FXMLLoader fxmlLoader = new FXMLLoader(new URL(FXML_PATH+"AddCoinS.fxml"));
            Stage stageEdit = new Stage();
            stageEdit.initModality(Modality.APPLICATION_MODAL);
            stageEdit.setTitle("Coin Searcher");
            stageEdit.getIcons().add(new Image("file:resources/images/icon1.png"));
            stageEdit.setScene(new Scene(fxmlLoader.load(), 280, 250));
            AddCoinStage controller = fxmlLoader.getController();
            controller.setCollectionBase(collectionBase,tableview.getSelectionModel().getSelectedItem(), stageEdit );
            stageEdit.showAndWait();
        }
 }

 @FXML
 public void searching() throws IOException {
     ArrayList<Integer> year=new ArrayList<>();
     ArrayList<String> currency=new ArrayList<>();
     ArrayList<String> value=new ArrayList<>();
     ArrayList<String> mint=new ArrayList<>();
     if(cbYears.getSelectionModel().getSelectedItem()!=null) {
         year.add(Integer.parseInt(cbYears.getSelectionModel().getSelectedItem()));
     }else year.add(null);
     currency.add(cbCurrency.getSelectionModel().getSelectedItem());
     value.add(cbValue.getSelectionModel().getSelectedItem());
     mint.add(cbMint.getSelectionModel().getSelectedItem());

     SearchInformation searchInformation=new SearchInformation();
     searchInformation.setCountry(cbCountry.getSelectionModel().getSelectedItem());
     searchInformation.setYear(year);
     searchInformation.setCurrency(currency);
     searchInformation.setValue(value);
     searchInformation.setMint(mint);
     coins.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().toString()));
     ArrayList<CoinDTO> we=serverWork.userRequest(searchInformation,language);
     ObservableList<CoinDTO> dataList = FXCollections.observableArrayList(we);
     if(we.isEmpty()){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         PropertyConnection propertyConnection=new PropertyConnection(new File("")
                 .getAbsolutePath()+"/src/main/resources/translation_"+language+".properties");
         alert.setTitle(propertyConnection.open().getProperty("information"));
         alert.setHeaderText(null);
         alert.setContentText(propertyConnection.open().getProperty("coinNotFound"));
         alert.showAndWait();
         propertyConnection.close();
     }
     tableview.setItems(dataList);

 }

 private boolean countryExists(){
        return cbCountry.getItems().contains(cbCountry.getSelectionModel().getSelectedItem());
 }

}
