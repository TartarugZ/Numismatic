package com.example.coursework;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditStage {

    @FXML
    private TextField Country;
    @FXML
    private TextField Year;
    @FXML
    private TextField Price;
    @FXML
    private TextField Currency;



    private Stage dialogStage;
    private Coin coin;
    private boolean okClicked = false;


    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;

        Country.setText(coin.getCountry());
        Year.setText(coin.getYears());
        Price.setText(coin.getPrice());
        Currency.setText(coin.getCurrency());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void OKButton() {
        if (isInputValid()) {
            coin.setCountry(Country.getText());
            coin.setYears(Year.getText());
            coin.setPrice(Price.getText());
            coin.setCurrency(Currency.getText());

            dialogStage.close();
        }
    }

    @FXML
    private void CancelButton() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (Country.getText() == null || Country.getText().length() == 0) {
            errorMessage += "No valid country!\n";
        }
        if (Year.getText() == null || Year.getText().length() == 0) {
            errorMessage += "No valid year!\n";
        }
        if (Price.getText() == null || Price.getText().length() == 0) {
            errorMessage += "No valid price!\n";
        }
        if (Currency.getText() == null || Currency.getText().length() == 0) {
            errorMessage += "No valid currency!\n";
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
