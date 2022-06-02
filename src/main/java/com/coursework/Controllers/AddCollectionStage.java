package com.coursework.Controllers;

import com.coursework.Objects.Collection;
import com.coursework.Objects.CollectionBase;
import com.coursework.Functions.PropertyConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.coursework.Controllers.LanguageSelectionScene.translation;

public class AddCollectionStage {
    @FXML private TableView<Collection> tv1;
    @FXML private TableColumn<Collection,String> names;
    @FXML private Button add;
    @FXML private Button cancel;
    private CollectionBase collectionBase;
    private Stage stage;
    private ArrayList<Collection> collections=new ArrayList<>();
    private ObservableList<Collection> collections2= FXCollections.observableArrayList(collections);
    private boolean closed= false;
    private String language;

    public void initialize() throws IOException {
        setTranslation();
    }

    private void setTranslation() throws IOException {
        setLanguage();
        PropertyConnection p=new PropertyConnection(new File("")
                .getAbsolutePath()+"/src/main/resources/translation_"+language+".properties");
        names.setText(p.open().getProperty("namesCol"));
        add.setText(p.open().getProperty("addCol"));
        cancel.setText(p.open().getProperty("cancelCol"));
        p.close();
    }

    @FXML
    private void adding(){
        if(tv1.getSelectionModel().getSelectedIndex()>=0) {
            collectionBase.addCollection(tv1.getSelectionModel().getSelectedItem());
            closed=true;
            stage.close();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void canceling(){
        this.stage.close();
    }

    public void setCollectionBase(CollectionBase localCollectionBase,CollectionBase collectionBase){
        this.collectionBase=collectionBase;
        this.collections=localCollectionBase.getAllCollections();
        names.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNameCollection()));
        collections2.addAll(collections);
        tv1.setItems(collections2);
    }
    public boolean isClosed(){
        return closed;
    }

    public void setLanguage() throws IOException {
        PropertyConnection property=new PropertyConnection(translation);
        this.language=property.open().getProperty("language");
        property.close();
    }

}

