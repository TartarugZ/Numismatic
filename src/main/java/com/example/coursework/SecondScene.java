package com.example.coursework;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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


    private Stage stage;
    private ArrayList<Coin> cc = new ArrayList<>();
    private ObservableList<Coin> cc2;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCC(Collection collection){
        this.cc =collection.getCollection();

    }

    public void setCollectionNameLabel(String string){
        this.collectionNameLabel.setText(string);
    }


    @FXML
    private void initialize() {
        cc2= FXCollections.observableArrayList(cc);
        setLanguage();

        countryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCountry()));
        yearColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getYears()));

        coinTableView.setItems(cc2);
        FilteredList<Coin> filteredCollections = new FilteredList<>(cc2, b -> true);

        coinDetails(null);
        coinTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> coinDetails(newValue));

        editButton.setDisable(true);
        deleteButton.setDisable(true);

        tf1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredCollections.setPredicate(coin -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if(coin.getYears().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                }else if(coin.getCountry().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else return false; // Does not match.
            });
        });

        SortedList<Coin> sortedCollections = new SortedList<>(filteredCollections);

        sortedCollections.comparatorProperty().bind(coinTableView.comparatorProperty());

        coinTableView.setItems(sortedCollections);
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
        }
    }

    @FXML
    protected void deleteItem(){
        int selectedIndex = coinTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            coinTableView.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void editItem(){
        Coin selectedCoin = coinTableView.getSelectionModel().getSelectedItem();
        if (selectedCoin != null) {
            boolean okClicked = showEditStage(selectedCoin);
            if (okClicked) {
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
            cc.add(tempCoin);
        }

    }

    private boolean showEditStage(Coin coin) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditS.fxml"));
            Stage stageEdit = new Stage();
            stageEdit.initModality(Modality.APPLICATION_MODAL);
            stageEdit.setTitle("Coin Searcher");
            stageEdit.getIcons().add(new Image("file:resourses/images/icon1.png"));
            stageEdit.setScene(new Scene(fxmlLoader.load(), 600, 600));


            EditStage controller = fxmlLoader.getController();
            controller.setDialogStage(stageEdit);
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
        stage.getIcons().add(new Image("file:resourses/images/icon1.png"));
        stage.setScene(new Scene(fxmlLoader.load(), 1000, 600));
    }

    }

