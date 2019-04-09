package ru.ifmo.uml.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import ru.ifmo.uml.dal.implementations.OrderImpl;
import ru.ifmo.uml.entity.Order;

import java.io.IOException;

public class OrdersListCell extends ListCell<Order> {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label orderId;

    @FXML
    private Label customerId;

    @FXML
    private Label deliveryType;

    @FXML
    private Label status;

    private FXMLLoader fxmlLoader;


    @Override
    protected void updateItem(Order item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null){
            setText(null);
            setGraphic(null);

        } else {
            if (fxmlLoader == null){
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/orderslistcell.fxml"));
                fxmlLoader.setController(this);
                try{
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            status.setText(item.getStatus());
            deliveryType.setText(item.getDeliveryType());
            orderId.setText(Integer.toString(item.getOrderId()));
            customerId.setText(Integer.toString(item.getCustomerId()));

            setGraphic(anchorPane);
        }
    }

}
