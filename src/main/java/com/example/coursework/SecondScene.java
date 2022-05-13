package com.example.coursework;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class SecondScene  {

    @FXML private TableView<Coin> coinTableView;
    @FXML private TableColumn<Coin, String> countryColumn;
    @FXML private TableColumn<Coin, String> yearColumn;
    @FXML private Label lcountry;
    @FXML private Label lyear;
    @FXML private Label lprice;
    @FXML private Label lcurrency;
    @FXML private Label collectionNameLabel;
    @FXML private Label detailsLabel;
    @FXML private Label country;
    @FXML private Label year;
    @FXML private Label price;
    @FXML private Label currency;
    @FXML private Button goSearchButton;
    @FXML private Button createButton;
    @FXML private Button editButton;
    @FXML private Button deleteButton;
    @FXML private TextField tf1;
    @FXML private Button saveButton1;
    @FXML private Button saveButton;


    private Stage stage;
    private ArrayList<Coin> cc = new ArrayList<>();
    private ObservableList<Coin> cc2= FXCollections.observableArrayList(cc);
    private CollectionBase collectionBase;
    private String nickname;

    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void setCollectionBase(CollectionBase collectionBase) {
        this.collectionBase = collectionBase;
    }
    public void setNickname(String string){
        this.nickname=string;
    }

    public void setCC(Collection collection){
        this.cc =collection.getCollection();
        refreshTable();
    }

    public void setCollectionNameLabel(String string){
        this.collectionNameLabel.setText(string);
    }


    @FXML
    public void initialize() {
        setLanguage();

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

    private void setLanguage(){
        if(LanguageSelectionScene.language.equals("ru")){
            collectionNameLabel.setText("Название коллекции");
            detailsLabel.setText("Подробнее о монете:");
            country.setText("Страна");
            year.setText("Год");
            price.setText("Цена");
            currency.setText("Номинал");
            goSearchButton.setText("К поиску");
            createButton.setText("Добавить");
            editButton.setText("Изменить");
            deleteButton.setText("Удалить");
            countryColumn.setText("Страна");
            yearColumn.setText("Год");
            tf1.setPromptText("Поиск");
            saveButton.setText("Сохранить локально");
            saveButton1.setText("Сохранить на аккаунте");
        }
    }

    @FXML
    protected void deleteItem(){

        if(coinTableView.getSelectionModel().getSelectedIndex()>=0) {
            for(int i=0;i<cc.size();i++){
                if(coinExists(cc.get(i))) {
                    cc.remove(i);
                }
            }
            coinTableView.getItems().remove(coinTableView.getSelectionModel().getSelectedItem());
        }

    }
  private boolean coinExists(Coin coin){
        int y=0;
        if(coin.getCountry().equals(coinTableView.getSelectionModel().getSelectedItem().getCountry()))y++;
        if(coin.getYears().equals(coinTableView.getSelectionModel().getSelectedItem().getYears()))y++;
        if(coin.getPrice().equals(coinTableView.getSelectionModel().getSelectedItem().getPrice()))y++;
        if(coin.getCurrency().equals(coinTableView.getSelectionModel().getSelectedItem().getCurrency()))y++;
        return y == 4;
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
                        cc.get(i).setPrice(selectedCoin.getPrice());
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
    @FXML
    protected void createItem(){
        Coin tempCoin = new Coin("");
        boolean okClicked = showEditStage(tempCoin);
        if (okClicked) {
            coinTableView.getItems().add(tempCoin);
            cc.add(tempCoin);
        }

    }

    private boolean showEditStage(Coin coin) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditS.fxml"));
            Stage stageEdit = new Stage();
            stageEdit.initModality(Modality.APPLICATION_MODAL);
            stageEdit.setTitle("Coin Searcher");
            stageEdit.getIcons().add(new Image("file:resources/images/icon1.png"));
            stageEdit.setScene(new Scene(fxmlLoader.load(), 600, 600));

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
            lprice.setText(coin.getPrice());
            lcurrency.setText(coin.getCurrency());
            editButton.setDisable(false);
            deleteButton.setDisable(false);
        } else {
            lcountry.setText("");
            lyear.setText("");
            lprice.setText("");
            lcurrency.setText("");
        }
    }

    @FXML
    protected void goSearch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FirstS.fxml"));
        stage.setTitle("Coin Searcher");
        stage.getIcons().add(new Image("file:resources/images/icon1.png"));
        stage.setScene(new Scene(fxmlLoader.load(), 1000, 600));
        FirstScene controller = fxmlLoader.getController();
        controller.setStage(stage);
        controller.setAccount(nickname);
        controller.setCollection(collectionBase);
    }

    @FXML
    private void save(){

    }

    @FXML
    private void save1(){

    }

    }

