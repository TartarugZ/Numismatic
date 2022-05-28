package com.coursework.Controllers;

import com.coursework.Collection;
import com.coursework.CollectionBase;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AddCollectionScene {
    @FXML private TableView<Collection> tv1;
    @FXML private TableColumn<Collection,String> names;
    @FXML private Button add;
    @FXML private Button cancel;
    private CollectionBase localCollectionBase;
    private CollectionBase collectionBase;
    private Collection collection;
    private Stage stage;
    private ArrayList<Collection> collections=new ArrayList<>();
    private ObservableList<Collection> collections2= FXCollections.observableArrayList(collections);
    private boolean closed= false;

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
            collectionBase.addCollection(tv1.getSelectionModel().getSelectedItem());
            collection=tv1.getSelectionModel().getSelectedItem();
            this.stage.close();
        }
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void canceling(){
        this.stage.close();
    }

    public void setCollectionBase(CollectionBase localCollectionBase,CollectionBase collectionBase){
        this.localCollectionBase=localCollectionBase;
        this.collectionBase=collectionBase;
        this.collections=localCollectionBase.getAllCollections();
        names.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNameCollection()));
        collections2.addAll(collections);
        tv1.setItems(collections2);
    }
    public boolean isClosed(){
        return closed;
    }

}

