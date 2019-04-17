package ru.ifmo.uml.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class FinishOrderPageController {

    @FXML
    private Button btnExit;

    @FXML
    private Text orderId;

    public void setOrderId(Integer id){
        orderId.setText(id.toString());
    }

    public void initialize(){
        btnExit.setOnAction(event -> {
            System.exit(0);
        });

    }

    @FXML
    void btnBackToTheShop() {
        //todo clean cart
        MainApp.clearAll();
        MainApp.showMainPage();
    }
}
