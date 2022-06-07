package com.coursework.functions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/** класс дляпомощи в использовании ComboBox: использование стрелочек на клавиатуре и поиск по содержимому
 * @param <T> объект, который будет содержать в себе ComboBox
 */
public class ComboBoxListener<T> implements EventHandler<KeyEvent>{
    private ComboBox<T> comboBox;
    private ObservableList<T> data;
    private boolean moveToPosition = false;
    private int position;

    /** конструктор для ComboBoxListener
     * @param comboBox необходимый для улучшения ComboBox
     */
    public ComboBoxListener(final ComboBox<T> comboBox) {
        this.comboBox = comboBox;
        data = comboBox.getItems();
        this.comboBox.setEditable(true);
        this.comboBox.setOnKeyPressed(t -> comboBox.hide());
        this.comboBox.setOnKeyReleased(ComboBoxListener.this);
    }

    @Override
    public void handle(KeyEvent event) {

        if(event.getCode() == KeyCode.UP) {
            position = -1;
            moveTo(comboBox.getEditor().getText().length());
            return;
        } else if(event.getCode() == KeyCode.DOWN) {
            if(!comboBox.isShowing()) {
                comboBox.show();
            }
            position = -1;
            moveTo(comboBox.getEditor().getText().length());
            return;
        } else if(event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE) {
            moveToPosition = true;
            position = comboBox.getEditor().getCaretPosition();
        }

        if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
                || event.isControlDown() || event.getCode() == KeyCode.HOME
                || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
            return;
        }

        ObservableList<T> list = FXCollections.observableArrayList();
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).toString().toLowerCase().startsWith(
                    comboBox.getEditor().getText().toLowerCase().trim())) {
                list.add(data.get(i));
            }
        }
        String t = comboBox.getEditor().getText();

        comboBox.setItems(list);
        if(!moveToPosition) {
            position = -1;
        }
        moveTo(t.length());
        if(!list.isEmpty()) {
            comboBox.show();
        }
    }

    private void moveTo(int textLength) {
        if(position == -1) {
            comboBox.getEditor().positionCaret(textLength);
        } else {
            comboBox.getEditor().positionCaret(position);
        }
        moveToPosition = false;
    }
}
