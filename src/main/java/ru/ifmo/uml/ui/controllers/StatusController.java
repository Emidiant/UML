package ru.ifmo.uml.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class StatusController {
    public TextField editId;
    public ListView listview;
    public Label txtPrice;
    public Label txtAddress;
    @FXML
    void initialize(){
        listview.setCellFactory(param -> new OrderStatusListCell());
    }
    public void btnSearchClicked(ActionEvent actionEvent) {
        //TODO this shit
    }
}
