package com.example.coursework;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SecondScene {
    @FXML
    private TableView<Coin> coinTableView;
    @FXML
    private TableColumn<Coin, String> country;
    @FXML
    private TableColumn<Coin, String> year;

    @FXML
    private Label Lcountry;
    @FXML
    private Label Lyear;
    @FXML
    private Label Lprice;
    @FXML
    private Label Lcurrency;
    @FXML
    private Button DeleteButton;
    @FXML
    private Button EditButton;

    private ArrayList<Coin> CC= new ArrayList<>();
    private ObservableList<Coin> CC2= FXCollections.observableArrayList(CC);
    @FXML
    private void initialize() {
        country.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCountry()));
        year.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getYears()));

        coinTableView.setItems(CC2);

        CoinDetails(null);
        coinTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> CoinDetails(newValue));

        EditButton.setDisable(true);
        DeleteButton.setDisable(true);
    }

    @FXML
    protected void DeleteItem()throws IOException {
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
    protected void EditItem()throws IOException{
        Coin selectedCoin = coinTableView.getSelectionModel().getSelectedItem();
        if (selectedCoin != null) {
            boolean okClicked = showEditStage(selectedCoin);
            if (okClicked) {
                CoinDetails(selectedCoin);
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
    protected void CreateItem()throws IOException{
        Coin tempCoin = new Coin("a");
        boolean okClicked = showEditStage(tempCoin);
        if (okClicked) {
            CC.add(tempCoin);
        }

    }

    public boolean showEditStage(Coin coin) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditS.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setOpacity(1);
            stage.setTitle("Searching Coins");
            stage.getIcons().add(new Image("file:resourses/images/icon1.png"));
            stage.setScene(new Scene(root, 600, 600));


            EditStage controller = fxmlLoader.getController();
            controller.setDialogStage(stage);
            controller.setCoin(coin);

            stage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void CoinDetails(Coin coin) {
        if (coin != null) {
            Lcountry.setText(coin.getCountry());
            Lyear.setText(coin.getYears());
            Lprice.setText(coin.getPrice());
            Lcurrency.setText(coin.getCurrency());
            EditButton.setDisable(false);
            DeleteButton.setDisable(false);
        } else {
            Lcountry.setText("");
            Lyear.setText("");
            Lprice.setText("");
            Lcurrency.setText("");
        }
    }


    }

