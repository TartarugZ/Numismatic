package com.coursework.controllers;

import com.coursework.objects.Collection;
import com.coursework.functions.PropertyConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static com.coursework.controllers.LanguageSelectionScene.TRANSLATION;

/**
 * Класс, отвечающий за окно создания коллекции
 */
public class CreateCollectionStage {

    @FXML private TextField tf;
    @FXML private Label name;
    private Stage stage;
    private Collection collection;
    private boolean closed = false;
    private String language;

    /** Присваивает окно для отображения
     * @param stage окно для вывода
     */
    public void setStage(Stage stage){
        this.stage=stage;
    }

    /** Присваивает окно для отображения
     * @param collection новая коллекция
     */
 public void setCollection(Collection collection){
        this.collection=collection;
 }

    public void initialize() throws IOException {
        setTranslation();
    }

    private void setTranslation() throws IOException {
        setLanguage();
        PropertyConnection p=new PropertyConnection(new File("")
                .getAbsolutePath()+"/src/main/resources/translation_"+language+".properties");
        name.setText(p.open().getProperty("nameC"));
        p.close();
    }

    @FXML
    private void enter(){
        if (tf.getText() == null || tf.getText().length() == 0) {
            String errorMessage = "Please, enter name of new collection or close this window\n";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }
        else {
            String string = tf.getText();
            collection.setNameCollection(string);
            closed=true;
            stage.close();
        }
    }

    /** вызывается для определения статуса переменной closed, которая меняется на false только в случае успешного добавления
     * @return true or false
     */
    public boolean isClosed(){
        return closed;
    }

    /**Определение языка из session.properties
     * @throws IOException ошибка при чтении property
     */
    public void setLanguage() throws IOException {
        PropertyConnection property=new PropertyConnection(TRANSLATION);
        this.language=property.open().getProperty("language");
        property.close();
    }
}
