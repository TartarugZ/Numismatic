package com.coursework.Controllers;

import com.coursework.Collection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateCollectionScene {

    @FXML private TextField tf;
    @FXML private Label name;
    private Stage stage;
    private Collection collection;
    private boolean closed = false;

    public void setStage(Stage stage){
        this.stage=stage;
    }
 public void setCollection(Collection collection){
        this.collection=collection;
 }
    public void initialize() {
        if(LanguageSelectionScene.language.equals("ru")){
            name.setText("Название коллекции");
        }
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
}
