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
    private Button saveStatus;

    private FXMLLoader fxmlLoader;

    @FXML
    private ChoiceBox<String> choiceStatus;

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

            //todo итемы плодятся как кролики после перехода с одного tab на другой, спасите
            choiceStatus.getItems().addAll("not confirmed", "confirmed", "transferred to delivery service","closed", "canceled");
            choiceStatus.setValue(item.getStatus());



            //todo так блет, где это действие надо писать, какие-то стремные ошибки иногда проскакивают, но работает

            saveStatus.setOnAction(event -> {
                OrderImpl orderImpl = new OrderImpl();
                item.setStatus(choiceStatus.getValue());
                System.out.println(item.getStatus() + " " + item.getOrderId());
                orderImpl.update(item);
            });



            deliveryType.setText(item.getDeliveryType());
            orderId.setText(Integer.toString(item.getOrderId()));
            customerId.setText(Integer.toString(item.getCustomerId()));

            setGraphic(anchorPane);
        }
    }

}
