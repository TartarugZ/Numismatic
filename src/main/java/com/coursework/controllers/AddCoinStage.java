package com.coursework.controllers;

import com.coursework.objects.Collection;
import com.coursework.objects.CollectionBase;
import com.coursework.functions.PropertyConnection;
import com.coursework.objects.Coin;
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

import static com.coursework.controllers.LanguageSelectionScene.TRANSLATION;

/**
 * Класс, отвечающий за окно добавления монеты в коллекцию
 */
public class AddCoinStage {
    @FXML private TableView<Collection> tv1;
    @FXML private TableColumn<Collection,String> names;
    @FXML private Button add;
    @FXML private Button cancel;
    private Coin coin;
    private Stage stage;
    private String language;

    public void initialize() throws IOException {
        setTranslation();
    }

    private void setTranslation() throws IOException {
        setLanguage();
        PropertyConnection p=new PropertyConnection(new File("")
                .getAbsolutePath()+"/src/main/resources/translation_"+language+".properties");
        names.setText(p.open().getProperty("namesCoin"));
        add.setText(p.open().getProperty("addCoin"));
        cancel.setText(p.open().getProperty("cancelCoin"));
        p.close();
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

    /** метод, передающий необходимые параметры в этот класс
     * @param collectionBase класс с массивом коллекций
     * @param coin монета
     * @param stage окно для вывода
     */
    public void setCollectionBase(CollectionBase collectionBase, Coin coin, Stage stage){
        ArrayList<Collection> collections = new ArrayList<>(collectionBase.getAllCollections());
        ObservableList<Collection> collections2 = FXCollections.observableArrayList(collections);
        this.coin=coin;
        this.stage=stage;
        names.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNameCollection()));
        tv1.setItems(collections2);
    }

    /** Определение языка из session.properties
     * @throws IOException ошибка при чтении property
     */
    public void setLanguage() throws IOException {
        PropertyConnection property=new PropertyConnection(TRANSLATION);
        this.language=property.open().getProperty("language");
        property.close();
    }

}
