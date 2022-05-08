package com.example.coursework;
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
    private ObservableList<Coin> cc2= FXCollections.observableArrayList(cc);;

    public void setStage(Stage stage) {
        this.stage = stage;
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

        countryColumn.setCellValueFactory(data -> data.getValue().getCountryProperty());
        yearColumn.setCellValueFactory(data -> data.getValue().getYearsProperty());

        coinTableView.setItems(cc2);
        FilteredList<Coin> filteredCoins = new FilteredList<>(cc2, b -> true);

        coinDetails(null);
        coinTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> coinDetails(newValue));

        editButton.setDisable(true);
        deleteButton.setDisable(true);

        tf1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredCoins.setPredicate(coin -> {
                if (newValue == null || newValue.isEmpty()) return true;
                if(newValue.length()>coin.getCountry().length()) return false;
                String lowerCaseFilter = newValue.toLowerCase();

                if (coin.getCountry().toLowerCase().startsWith(lowerCaseFilter) ) {
                    return true; // Filter matches first name.
                }else if(coin.getYears().toLowerCase().startsWith(lowerCaseFilter))
                    return true; // Does not match.
                else return false;
            });
        });
     refreshTable();
    }

    private  void refreshTable(){
        countryColumn.setCellValueFactory(data -> data.getValue().getCountryProperty());
        yearColumn.setCellValueFactory(data -> data.getValue().getYearsProperty());
        cc2= FXCollections.observableArrayList(cc);
        coinTableView.setItems(cc2);
        coinTableView.refresh();
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
        if(coinTableView.getSelectionModel().getSelectedIndex()>=0) {
            coinTableView.getItems().remove(coinTableView.getSelectionModel().getSelectedItem());
        }
        refreshTable();
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
            coinTableView.getItems().add(tempCoin);
            cc.add(tempCoin);
            System.out.println(cc.get(0).getCountry());
            System.out.println(cc.get(cc.size()-1).getCountry());
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

