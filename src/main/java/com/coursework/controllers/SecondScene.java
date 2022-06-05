package com.coursework.controllers;
import com.coursework.objects.Collection;
import com.coursework.objects.CollectionBase;
import com.coursework.functions.PropertyConnection;
import com.coursework.serialization.FileWork;
import com.coursework.serverConnection.CoinDTO;
import com.coursework.serverConnection.ServerWork;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.LinkedHashMap;

import static com.coursework.controllers.LanguageSelectionScene.FXML_PATH;
import static com.coursework.controllers.LanguageSelectionScene.TRANSLATION;

public class SecondScene  {

    @FXML private TableView<CoinDTO> coinTableView;
    @FXML private TableColumn<CoinDTO, String> countryColumn;
    @FXML private TableColumn<CoinDTO, String> yearColumn;
    @FXML private TableColumn<CoinDTO, String> valueColumn;
    @FXML private TableColumn<CoinDTO, String> currencyColumn;
    @FXML private TableColumn<CoinDTO, String> costColumn;
    @FXML private TableColumn<CoinDTO, String> mintColumn;
    @FXML private Label ldate;
    @FXML private Hyperlink llink;
    @FXML private Label collectionNameLabel;
    @FXML private Label detailsLabel;
    @FXML private Label dateOf;
    @FXML private Label link;
    @FXML private Button goSearchButton;
    @FXML private Button deleteButton;
    @FXML private Button saveButton1;
    @FXML private Button saveButton;
    @FXML private TextArea info;
    private FileWork fileWork=new FileWork();
    private String language;

    private Stage stage;
    private ArrayList<CoinDTO> cc = new ArrayList<>();
    private ObservableList<CoinDTO> cc2= FXCollections.observableArrayList(cc);
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

        deleteButton.setDisable(true);
    }

    private  void refreshTable(){
        countryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCountry()));
        yearColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getYears().toString()));
        valueColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValue()));
        currencyColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCurrency()));
        costColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCost()));
        mintColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMint()));
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
           Iterator<CoinDTO> iterator =cc.iterator();
           System.out.println(cc.size());
           while (iterator.hasNext())
           {
               CoinDTO coin=iterator.next();
               System.out.println(coin);
               if(coinExists(coin)){
                   iterator.remove();
               }
           }
           System.out.println(cc.size());
           coinTableView.getItems().remove(coinTableView.getSelectionModel().getSelectedItem());
       }
    }

  private boolean coinExists(CoinDTO coin){
      return coin == coinTableView.getSelectionModel().getSelectedItem();
  }

    private void visible(boolean a){
        ldate.setVisible(a);
        llink.setVisible(a);
        dateOf.setVisible(a);
        link.setVisible(a);
        info.setVisible(a);
    }

    private void coinDetails(CoinDTO coin) {
        if (coin != null) {
            visible(true);
            ldate.setText(coin.getDataOfCreate().toString());
            llink.setText("https://"+language+".ucoin.net" +coin.getLinkUcoin());
            deleteButton.setDisable(false);
            info.setText(coin.getInfo());
            info.setEditable(false);
            llink.setOnMouseClicked(mouseEvent -> {
                try {
                    desktop.browse(new URL(llink.getText()).toURI());
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
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

