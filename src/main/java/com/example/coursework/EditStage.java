package com.example.coursework;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditStage {

    @FXML private TextField country;
    @FXML private TextField year;
    @FXML private TextField price;
    @FXML private TextField currency;
    @FXML private Button otmena;
    @FXML private Label countryLabel;
    @FXML private Label yearLabel;
    @FXML private Label priceLabel;
    @FXML private Label currencyLabel;

    private Stage dialogStage;
    private Coin coin;
    private boolean okClicked = false;

    @FXML
    public void initialize() {
        if(LanguageSelectionScene.language=="ru"){
            otmena.setText("Назад");
            countryLabel.setText("Страна");
            yearLabel.setText("Год");
            priceLabel.setText("Цена");
            currencyLabel.setText("Номинал");
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
        country.setText(coin.getCountry());
        year.setText(coin.getYears());
        price.setText(coin.getPrice());
        currency.setText(coin.getCurrency());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void oKButton() {
        if (isInputValid()) {
            coin.setCountry(country.getText());
            coin.setYears(year.getText());
            coin.setPrice(price.getText());
            coin.setCurrency(currency.getText());
            okClicked=true;
            dialogStage.close();
        }
    }

    @FXML
    private void cancelButton() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (country.getText() == null || country.getText().length() == 0) {
            errorMessage += "No valid country!\n";
        }
        if (year.getText() == null || year.getText().length() == 0) {
            errorMessage += "No valid year!\n";
        }
        if (price.getText() == null || price.getText().length() == 0) {
            errorMessage += "No valid price!\n";
        }
        if (currency.getText() == null || currency.getText().length() == 0) {
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
