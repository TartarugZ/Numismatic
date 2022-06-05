package com.coursework.controllers;
import com.coursework.objects.Coin;
import com.coursework.objects.Collection;
import com.coursework.objects.CollectionBase;
import com.coursework.functions.PropertyConnection;
import com.coursework.serialization.FileWork;
import com.coursework.serverConnection.ServerWork;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.Desktop;
import java.util.Iterator;

import static com.coursework.controllers.LanguageSelectionScene.FXML_PATH;
import static com.coursework.controllers.LanguageSelectionScene.TRANSLATION;

public class SecondScene  {

    @FXML private TableView<Coin> coinTableView;
    @FXML private TableColumn<Coin, String> countryColumn;
    @FXML private TableColumn<Coin, String> yearColumn;
    @FXML private TableColumn<Coin, String> valueColumn;
    @FXML private TableColumn<Coin, String> currencyColumn;
    @FXML private TableColumn<Coin, String> costColumn;
    @FXML private TableColumn<Coin, String> mintColumn;
    @FXML private Label ldate;
    @FXML private Hyperlink llink;
    @FXML private Label collectionNameLabel;
    @FXML private Label detailsLabel;
    @FXML private Label dateOf;
    @FXML private Label link;
    @FXML private Button goSearchButton;
    @FXML private Button editButton;
    @FXML private Button deleteButton;
    @FXML private Button saveButton1;
    @FXML private Button saveButton;
    @FXML private TextArea info;
    private FileWork fileWork=new FileWork();
    private String language;

    private Stage stage;
    private ArrayList<Coin> cc = new ArrayList<>();
    private ObservableList<Coin> cc2= FXCollections.observableArrayList(cc);
    private CollectionBase collectionBase;
    private CollectionBase localCollectionBase;
    private Collection collectionMain;
    private String nickname;
    private Desktop desktop=Desktop.getDesktop();

    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void setCollectionBase(CollectionBase collectionBase, CollectionBase localCollectionBase) {
        this.localCollectionBase=localCollectionBase;
        this.collectionBase = collectionBase;

    }
    public void setNickname(String string){
        this.nickname=string;
    }

    public void setCC(Collection collection){
        this.cc =collection.getCollection();
        this.collectionMain =collection;
        refreshTable();
    }

    public void setCollectionNameLabel(String string){
        this.collectionNameLabel.setText(string);
    }


    @FXML
    public void initialize() throws IOException {
        setTranslation();
        visible(false);
        coinDetails(null);
        coinTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> coinDetails(newValue));

        editButton.setDisable(true);
        deleteButton.setDisable(true);
    }

    private  void refreshTable(){
        countryColumn.setCellValueFactory(data -> data.getValue().getCountryProperty());
        yearColumn.setCellValueFactory(data -> data.getValue().getYearsProperty());
        valueColumn.setCellValueFactory(data -> data.getValue().getValueProperty());
        currencyColumn.setCellValueFactory(data -> data.getValue().getCurrencyProperty());
        costColumn.setCellValueFactory(data -> data.getValue().getPriceProperty());
        mintColumn.setCellValueFactory(data -> data.getValue().getMintProperty());
        cc2= FXCollections.observableArrayList(cc);
        coinTableView.setItems(cc2);
    }

    private void setTranslation() throws IOException {
        setLanguage();
        PropertyConnection p=new PropertyConnection(new File("")
                .getAbsolutePath()+"/src/main/resources/translation_"+language+".properties");
        collectionNameLabel.setText(p.open().getProperty("collectionNameLabelS"));
        detailsLabel.setText(p.open().getProperty("detailsLabelS"));
        costColumn.setText(p.open().getProperty("priceS"));
        currencyColumn.setText(p.open().getProperty("currencyS"));
        valueColumn.setText(p.open().getProperty("valueS"));
        mintColumn.setText(p.open().getProperty("mintS"));
        dateOf.setText(p.open().getProperty("dateOfS"));
        link.setText(p.open().getProperty("linkS"));
        goSearchButton.setText(p.open().getProperty("goSearchButtonS"));
        editButton.setText(p.open().getProperty("editButtonS"));
        deleteButton.setText(p.open().getProperty("deleteButtonS"));
        countryColumn.setText(p.open().getProperty("countryColumnS"));
        yearColumn.setText(p.open().getProperty("yearColumnS"));
        saveButton.setText(p.open().getProperty("saveButtonS"));
        saveButton1.setText(p.open().getProperty("saveButton1S"));
        p.close();
    }

    @FXML
    protected void deleteItem(){

       if(coinTableView.getSelectionModel().getSelectedIndex()>=0) {
           Iterator<Coin> iterator =cc.iterator();
           System.out.println(cc.size());
           while (iterator.hasNext())
           {
               Coin coin=iterator.next();
               System.out.println(coin);
               if(coinExists(coin)){
                   iterator.remove();
               }
           }
           System.out.println(cc.size());
           coinTableView.getItems().remove(coinTableView.getSelectionModel().getSelectedItem());
       }
    }

  private boolean coinExists(Coin coin){
      return coin == coinTableView.getSelectionModel().getSelectedItem();
  }


    @FXML
    protected void editItem(){
        Coin selectedCoin = coinTableView.getSelectionModel().getSelectedItem();
        if (selectedCoin != null) {
            boolean okClicked = showEditStage(selectedCoin);
            if (okClicked) {
                for (Coin coin : cc) {
                    if (coinExists(coin)) {
                        coin.setCountry(selectedCoin.getCountry());
                        coin.setYears(selectedCoin.getYears());
                        coin.setCurrency(selectedCoin.getCurrency());
                        coin.setCost(selectedCoin.getCost());
                        coin.setValue(selectedCoin.getValue());
                        coin.setMint(selectedCoin.getMint());
                        coin.setDate(selectedCoin.getDate());
                        coin.setLinkUcoin(selectedCoin.getLinkUcoin());
                        coin.setInfo(selectedCoin.getInfo());
                    }
                }
                coinDetails(selectedCoin);
            }
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    private boolean showEditStage(Coin coin) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new URL(FXML_PATH+"EditS.fxml"));
            Stage stageEdit = new Stage();
            stageEdit.initModality(Modality.APPLICATION_MODAL);
            stageEdit.setTitle("Coin Searcher");
            stageEdit.getIcons().add(new Image("file:resources/images/icon1.png"));
            stageEdit.setScene(new Scene(fxmlLoader.load(), 970, 520));

            EditStage controller = fxmlLoader.getController();
            controller.setStage(stageEdit);
            controller.setCoin(coin);
            stageEdit.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void visible(boolean a){
        ldate.setVisible(a);
        llink.setVisible(a);
        dateOf.setVisible(a);
        link.setVisible(a);
        info.setVisible(a);
    }

    private void coinDetails(Coin coin) {
        if (coin != null) {
            visible(true);
            ldate.setText(coin.getDate());
            llink.setText("https://"+language+".ucoin.net" +coin.getLinkUcoin());
            editButton.setDisable(false);
            deleteButton.setDisable(false);
            info.setText(coin.getInfo());
            info.setEditable(false);
            llink.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        desktop.browse(new URL(llink.getText()).toURI());
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }

    @FXML
    protected void goSearch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(FXML_PATH+"FirstS.fxml"));
        stage.setScene(new Scene(fxmlLoader.load(), 1200, 750));
        FirstScene controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setAccount(nickname);
        controller.setCollection(collectionBase);
    }

    @FXML
    private void save() throws IOException{

        Iterator<Collection> iterator = localCollectionBase.getAllCollections().iterator();
        if(!localCollectionBase.getAllCollections().isEmpty()){
            Collection collectionz;
            while (iterator.hasNext())
            {
                collectionz=iterator.next();
                if(collectionz.equals(collectionMain)){
                    System.out.println("OH YES");
                   iterator.remove();
                }
            }
        }
        localCollectionBase.addCollection(collectionMain);
        fileWork.write(localCollectionBase,nickname);
    }

    @FXML
    private void save1(){
        ServerWork serverWork=new ServerWork();
        serverWork.sendCollection(collectionMain.toCollectionDTO());
    }
    public void setLanguage() throws IOException {
        PropertyConnection property=new PropertyConnection(TRANSLATION);
        this.language=property.open().getProperty("language");
        property.close();
    }


}

