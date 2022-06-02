package com.coursework.Controllers;

import com.coursework.Objects.Coin;
import com.coursework.Functions.PropertyConnection;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static com.coursework.Controllers.LanguageSelectionScene.translation;

public class EditStage {

    @FXML private TextField country;
    @FXML private TextField year;
    @FXML private TextField price;
    @FXML private TextField currency;
    @FXML private TextField value;
    @FXML private TextField mint;
    @FXML private TextField date;
    @FXML private TextField link;
    @FXML private Button cancel;
    @FXML private Label countryLabel;
    @FXML private Label yearLabel;
    @FXML private Label priceLabel;
    @FXML private Label currencyLabel;
    @FXML private Label valueLabel;
    @FXML private Label mintLabel;
    @FXML private Label dateLabel;
    @FXML private Label linkLabel;
    @FXML private TextArea info;

    private Stage stage;
    private Coin coin= new Coin();
    private boolean okClicked = false;
    private String language;

    @FXML
    public void initialize() throws IOException {
        setTranslation();
    }

    private void setTranslation() throws IOException {
        setLanguage();
        PropertyConnection p=new PropertyConnection(new File("")
                .getAbsolutePath()+"/src/main/resources/translation_"+language+".properties");
        cancel.setText(p.open().getProperty("cancelE"));
        countryLabel.setText(p.open().getProperty("countryLabelE"));
        yearLabel.setText(p.open().getProperty("yearLabelE"));
        priceLabel.setText(p.open().getProperty("priceLabelE"));
        currencyLabel.setText(p.open().getProperty("currencyLabelE"));
        valueLabel.setText(p.open().getProperty("valueLabelE"));
        mintLabel.setText(p.open().getProperty("mintLabelE"));
        dateLabel.setText(p.open().getProperty("dateLabelE"));
        linkLabel.setText(p.open().getProperty("linkLabelE"));
        p.close();
    }

    public void setLanguage() throws IOException {
        PropertyConnection property=new PropertyConnection(translation);
        this.language=property.open().getProperty("language");
        property.close();
    }

    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
        country.setText(coin.getCountry());
        year.setText(coin.getYears());
        price.setText(coin.getCost());
        currency.setText(coin.getCurrency());
        value.setText(coin.getValue());
        mint.setText(coin.getMint());
        date.setText(coin.getDate());
        link.setText(coin.getLinkUcoin());
        info.setText(coin.getInfo());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void oKButton() {
        if (isInputValid()) {
            coin.setCountry(country.getText());
            coin.setYears(year.getText());
            coin.setCost(price.getText());
            coin.setCurrency(currency.getText());
            coin.setValue(value.getText());
            coin.setMint(mint.getText());
            coin.setDate(date.getText());
            coin.setLinkUcoin(link.getText());
            coin.setInfo(info.getText());
            okClicked=true;
            stage.close();
        }
    }

    @FXML
    private void cancelButton() {
        stage.close();
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
            alert.initOwner(stage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
