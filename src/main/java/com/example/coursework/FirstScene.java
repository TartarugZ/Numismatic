package com.example.coursework;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;


public class FirstScene implements Initializable {
    @FXML  private Label welcomeText;
    @FXML  private TextArea TF1;
    @FXML  private TextArea TF2;
    @FXML  private TextField TA;
    @FXML  private TextField TA1;
    @FXML  private TableView<String> tableviewCountry;
    @FXML  private TableColumn<String, String> Country;
    @FXML  private TableColumn<Coin,String> Years;
    @FXML  private TableColumn<Coin,String> toilet;
    @FXML  private ComboBox<String> ComboBox;
    @FXML  private ChoiceBox<String> ChoiceBox;
    @FXML  private Label lbl1;
    @FXML  private Label lbl2;


    private final ObservableList<String> dataListCounty = FXCollections.observableArrayList();
    private final ObservableList<String> BoxCheck= FXCollections.observableArrayList();
    private ArrayList<String> CC= new ArrayList( CoinSearcher.getCountry());

    @FXML
    protected void onHelloButtonClick() throws IOException, StringIndexOutOfBoundsException{

        welcomeText.setText("Welcome to Coin Searcher Application!");
        for(int i=0;i<CC.size();i++){
            TF1.appendText(CC.get(i)+"\n");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Collections.sort(CC);
        Country.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));

        dataListCounty.addAll(CC);
        BoxCheck.addAll(CC);
        ChoiceBox.setItems(BoxCheck);
        ComboBox.setItems(BoxCheck);
        ComboBox.setOnAction(event -> lbl2.setText(ComboBox.getValue()));
        ChoiceBox.setOnAction(event -> lbl1.setText(ChoiceBox.getValue()));


        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<String> filteredDataCountry = new FilteredList<>(dataListCounty, b -> true);


        // 2. Set the filter Predicate whenever the filter changes.
        TA.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataCountry.setPredicate(coin -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                if(newValue.length()>coin.length()) return false;
                String lowerCaseFilter = newValue.toLowerCase();



                if (coin.toLowerCase().substring(0,lowerCaseFilter.length()).equals(lowerCaseFilter) ) {
                    return true; // Filter matches first name.
                }else
                    return false; // Does not match.
            });
        });
       /* TA1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataYears.setPredicate(coin -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                if(coin.getYears().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                }else
                    return false; // Does not match.
            });
        });*/

        SortedList<String> sortedDataCountry = new SortedList<>(filteredDataCountry);

        sortedDataCountry.comparatorProperty().bind(tableviewCountry.comparatorProperty());

        tableviewCountry.setItems(sortedDataCountry);


    }


}
