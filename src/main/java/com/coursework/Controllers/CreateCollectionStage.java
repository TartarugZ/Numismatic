package com.coursework.Controllers;

import com.coursework.Objects.Collection;
import com.coursework.Functions.PropertyConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

import static com.coursework.Controllers.LanguageSelectionScene.translation;

public class CreateCollectionStage {

    @FXML private TextField tf;
    @FXML private Label name;
    private Stage stage;
    private Collection collection;
    private boolean closed = false;
    public String language;

    public void setStage(Stage stage){
        this.stage=stage;
    }

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

    public boolean isClosed(){
        return closed;
    }
    public void setLanguage() throws IOException {
        PropertyConnection property=new PropertyConnection(translation);
        this.language=property.open().getProperty("language");
        property.close();
    }
}
