package com.coursework.Controllers;
import com.coursework.Coin;
import com.coursework.Collection;
import com.coursework.CollectionBase;
import com.coursework.PropertyConnection;
import com.coursework.Serialization.FileWork;
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

import static com.coursework.Controllers.LanguageSelectionScene.translation;

public class SecondScene  {

    @FXML private TableView<Coin> coinTableView;
    @FXML private TableColumn<Coin, String> countryColumn;
    @FXML private TableColumn<Coin, String> yearColumn;
    @FXML private Label lcountry;
    @FXML private Label lyear;
    @FXML private Label lcost;
    @FXML private Label lcurrency;
    @FXML private Label lvalue;
    @FXML private Label lcategory;
    @FXML private Label lmint;
    @FXML private Label ldate;
    @FXML private Hyperlink llink;
    @FXML private Label collectionNameLabel;
    @FXML private Label detailsLabel;
    @FXML private Label country;
    @FXML private Label year;
    @FXML private Label price;
    @FXML private Label currency;
    @FXML private Label value;
    @FXML private Label category;
    @FXML private Label mint;
    @FXML private Label dateOf;
    @FXML private Label link;
    @FXML private Button goSearchButton;
    @FXML private Button editButton;
    @FXML private Button deleteButton;
    @FXML private Button saveButton1;
    @FXML private Button saveButton;
    private String fxmlPath = LanguageSelectionScene.fxmlPath;
    private FileWork fileWork=new FileWork();
    private String language;

    private Stage stage;
    private ArrayList<Coin> cc = new ArrayList<>();
    private ObservableList<Coin> cc2= FXCollections.observableArrayList(cc);
    private CollectionBase collectionBase;
    private CollectionBase localCollectionBase;
    private Collection collection;
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
        this.collection=collection;
        refreshTable();
    }

    public void setCollectionNameLabel(String string){
        this.collectionNameLabel.setText(string);
    }


    @FXML
    public void initialize() throws IOException {
        setTranslation();

        coinDetails(null);
        coinTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> coinDetails(newValue));

        editButton.setDisable(true);
        deleteButton.setDisable(true);
    }

    private  void refreshTable(){
        countryColumn.setCellValueFactory(data -> data.getValue().getCountryProperty());
        yearColumn.setCellValueFactory(data -> data.getValue().getYearsProperty());
        cc2= FXCollections.observableArrayList(cc);
        coinTableView.setItems(cc2);
    }

    private void setTranslation() throws IOException {
        setLanguage();
        PropertyConnection p=new PropertyConnection(new File("")
                .getAbsolutePath()+"/src/main/resources/translation_"+language+".properties");
        collectionNameLabel.setText(p.open().getProperty("collectionNameLabelS"));
        detailsLabel.setText(p.open().getProperty("detailsLabelS"));
        country.setText(p.open().getProperty("countryS"));
        year.setText(p.open().getProperty("yearS"));
        price.setText(p.open().getProperty("priceS"));
        currency.setText(p.open().getProperty("currencyS"));
        value.setText(p.open().getProperty("valueS"));
        category.setText(p.open().getProperty("categoryS"));
        mint.setText(p.open().getProperty("mintS"));
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
            for(int i=0;i<cc.size();i++){
                if(coinExists(cc.get(i))) {
                    System.out.println(cc.size());
                    cc.remove(i);
                    System.out.println(cc.size());
                }
            }
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
                for(int i=0;i<cc.size();i++){
                    if(coinExists(cc.get(i))) {
                        cc.get(i).setCountry(selectedCoin.getCountry());
                        cc.get(i).setYears(selectedCoin.getYears());
                        cc.get(i).setCurrency(selectedCoin.getCurrency());
                        cc.get(i).setCost(selectedCoin.getCost());
                        cc.get(i).setValue(selectedCoin.getValue());
                        cc.get(i).setCategory(selectedCoin.getCategory());
                        cc.get(i).setMint(selectedCoin.getMint());
                        cc.get(i).setDate(selectedCoin.getDate());
                        cc.get(i).setLinkUcoin(selectedCoin.getLinkUcoin());
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
            FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlPath+"EditS.fxml"));
            Stage stageEdit = new Stage();
            stageEdit.initModality(Modality.APPLICATION_MODAL);
            stageEdit.setTitle("Coin Searcher");
            stageEdit.getIcons().add(new Image("file:resources/images/icon1.png"));
            stageEdit.setScene(new Scene(fxmlLoader.load(), 650, 520));

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

    private void coinDetails(Coin coin) {
        if (coin != null) {
            lcountry.setText(coin.getCountry());
            lyear.setText(coin.getYears());
            lcost.setText(coin.getCost());
            lcurrency.setText(coin.getCurrency());
            lvalue.setText(coin.getValue());
            lcategory.setText(coin.getCategory());
            lmint.setText(coin.getMint());
            ldate.setText(coin.getDate());
            llink.setText(coin.getLinkUcoin());
            editButton.setDisable(false);
            deleteButton.setDisable(false);

            llink.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        desktop.browse(new URL("https://" +llink.getText()).toURI());
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }

    @FXML
    protected void goSearch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(fxmlPath+"FirstS.fxml"));
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resources/images/icon1.png"));
        stage.setScene(new Scene(fxmlLoader.load(), 1200, 750));
        FirstScene controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setAccount(nickname);
        controller.setCollection(collectionBase);
    }

    @FXML
    private void save() throws IOException{
         localCollectionBase.addCollection(collection);
         fileWork.write(localCollectionBase,nickname);
    }

    @FXML
    private void save1(){

    }
    public void setLanguage() throws IOException {
        PropertyConnection property=new PropertyConnection(translation);
        this.language=property.open().getProperty("language");
        property.close();
    }


}

