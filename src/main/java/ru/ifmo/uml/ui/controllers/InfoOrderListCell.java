package ru.ifmo.uml.ui.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.ifmo.uml.entity.Order;
import javafx.fxml.FXML;
import ru.ifmo.uml.entity.Product;

import java.io.IOException;

public class InfoOrderListCell extends ListCell<Order> {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label orderId;

    @FXML
    private Label customerId;

    @FXML
    private Label deliveryType;


    private FXMLLoader fxmlLoader;


    @Override
    protected void updateItem(Order item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null){
            setText(null);
            setGraphic(null);

        } else {
            if (fxmlLoader == null){
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/infoorderlistcell.fxml"));
                fxmlLoader.setController(this);
                try{
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            deliveryType.setText(item.getDeliveryType());
            orderId.setText(Integer.toString(item.getOrderId()));
            customerId.setText(Integer.toString(item.getCustomerId()));

            setGraphic(anchorPane);
        }
    }
}