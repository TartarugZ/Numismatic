package com.example.coursework;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateCollectionScene {

    @FXML private TextField tf;
    @FXML private Label name;
    @FXML private Button enterButton;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        if(LanguageSelectionScene.language=="ru"){
            name.setText("Название коллекции");
        }
    }

    @FXML
    private void enter(){
        String string = tf.getText();
        Collection collection=new Collection(string);
        FirstScene.getBase().addCollection(collection);
        stage.close();
    }
}
