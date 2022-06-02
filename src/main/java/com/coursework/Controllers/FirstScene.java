package com.coursework.Controllers;

import com.coursework.Functions.ComboBoxListener;
import com.coursework.Functions.PropertyConnection;
import com.coursework.Objects.Coin;
import com.coursework.Objects.Collection;
import com.coursework.Objects.CollectionBase;
import com.coursework.Serialization.FileWork;
import com.coursework.ServerConnection.CoinDTO;
import com.coursework.ServerConnection.CountryDenominationInfo;
import com.coursework.ServerConnection.SearchInformation;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import com.coursework.ServerConnection.ServerWork;
import javafx.util.Duration;

import static com.coursework.Controllers.LanguageSelectionScene.translation;

public class FirstScene{
    @FXML  private TextField tf;
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
    private String fxmlPath = LanguageSelectionScene.fxmlPath;
    private FileWork fileWork = new FileWork();
    private String language;

    private CollectionBase collectionBase = new CollectionBase();
    private CollectionBase localCollectionBase= new CollectionBase();
    private ObservableList<CoinDTO> dataList = FXCollections.observableArrayList();
    private ObservableList<Collection> collections= FXCollections.observableArrayList();
    private ServerWork serverWork=new ServerWork();
    private CountryDenominationInfo cdi;

    private Stage stage;

    public void initialize() throws IOException {
        setTranslation();
        chosenCollection();
        setComboBoxes();
        linkCoin();
        extraInfo();
    }

    public void extraInfo(){
        tableview.setRowFactory(tv -> new TableRow<CoinDTO>() {
            private Tooltip tooltip = new Tooltip();
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
        PropertyConnection property=new PropertyConnection(translation);

        cbCountry.setItems(serverWork.getCountries(property.open().getProperty("language")));



        cbCountry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    cdi =serverWork.loadValueAndCurrency(cbCountry.getSelectionModel().getSelectedItem(),language);
                    cbValue.setItems(FXCollections.observableArrayList(cdi.getValue()));
                    cbCurrency.setItems(FXCollections.observableArrayList(cdi.getCurrency()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        cbValue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                cbCurrency.setItems(FXCollections.observableArrayList(cdi.getSingleCurrency(cbValue.getSelectionModel().getSelectedItem())));
                ComboBoxListener<String> helper1=new ComboBoxListener<>(cbCurrency);
                ComboBoxListener<String> helper2=new ComboBoxListener<>(cbValue);
            }
        });

        cbCurrency.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                cbValue.setItems(FXCollections.observableArrayList(cdi.getSingleValue(cbCurrency.getSelectionModel().getSelectedItem())));
                ComboBoxListener<String> helper1=new ComboBoxListener<>(cbCurrency);
                ComboBoxListener<String> helper2=new ComboBoxListener<>(cbValue);
            }
        });

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
        tableview2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2){
                    FXMLLoader fxmlLoader = null;
                    try {
                        fxmlLoader = new FXMLLoader(new URL(fxmlPath+"SecondS.fxml"));
                        stage.setScene(new Scene(fxmlLoader.load(), 1200, 750));
                        localCollectionBase=fileWork.read(nickname.getText());
                    } catch (IOException | ClassNotFoundException e) {
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

    private void linkCoin(){
        tableview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2){
                    Desktop desktop=Desktop.getDesktop();
                    try {
                        desktop.browse(new URL("https://"+language+".ucoin.net" +tableview.getSelectionModel().getSelectedItem().getLinkUcoin()).toURI());
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
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
        CreateCollectionStage controller = fxmlLoader.getController();
        controller.setStage(stageEdit);
        Collection collection=new Collection(" ");
        controller.setCollection(collection);
        stageEdit.showAndWait();
        if(controller.isClosed()){
            collectionBase.addCollection(collection);
            tableview2.getItems().clear();
            collections.addAll(collectionBase.getAllCollections());
            tableview2.setItems(collections);
        }
    }

    @FXML
    private void addLocalCollection() throws IOException, ClassNotFoundException {
        localCollectionBase=fileWork.read(nickname.getText());
        System.out.println(localCollectionBase.toString());
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlPath+"AddCollectionS.fxml"));
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
 private  void  adding() throws IOException {
        if(chosenCoin()) {
            FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlPath+"AddCoinS.fxml"));
            Stage stageEdit = new Stage();
            stageEdit.initModality(Modality.APPLICATION_MODAL);
            stageEdit.setTitle("Coin Searcher");
            stageEdit.getIcons().add(new Image("file:resources/images/icon1.png"));
            stageEdit.setScene(new Scene(fxmlLoader.load(), 280, 250));
            AddCoinStage controller = fxmlLoader.getController();
            controller.setCollectionBase(collectionBase,tableview.getSelectionModel().getSelectedItem().toCoin(), stageEdit );
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
     System.out.println(searchInformation);
     coins.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().toString()));
     dataList=serverWork.userRequest(searchInformation,language);
     tableview.setItems(dataList);

 }

}
