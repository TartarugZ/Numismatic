package com.example.coursework;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AddCoinScene {
    @FXML private TableView<Collection> tv1;
    @FXML private TableColumn<Collection,String> names;
    @FXML private Button add;
    @FXML private Button cancel;
    private Coin coin;
    private CollectionBase collectionBase;
    private Stage stage;
    private ArrayList<Collection> collections=new ArrayList<>();
    private ObservableList<Collection> collections2= FXCollections.observableArrayList(collections);

    public void initialize(){
        if(LanguageSelectionScene.language.equals("ru")){
            names.setText("Ваши коллекции");
            add.setText("Добавить");
            cancel.setText("Назад");
        }

    }
    @FXML
    private void adding(){
        if(tv1.getSelectionModel().getSelectedIndex()>=0) {
            tv1.getSelectionModel().getSelectedItem().addToCollection(coin);
            this.stage.close();
        }
    }
    @FXML
    private void canceling(){
        this.stage.close();
    }
    @FXML
    private void viewing(){

    }
    public void setCollectionBase(CollectionBase collectionBase,Coin coin,Stage stage){
        this.collectionBase=collectionBase;
        this.collections=collectionBase.getAllCollections();
        this.coin=coin;
        this.stage=stage;
        names.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNameCollection()));
        collections2.addAll(collections);
        tv1.setItems(collections2);
    }

}
