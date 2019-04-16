package ru.ifmo.uml.ui.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ru.ifmo.uml.entity.Order;
import ru.ifmo.uml.entity.ProductWithAmount;
import ru.ifmo.uml.ui.MainApp;
import ru.ifmo.uml.ui.OrderManager;

public class StatusController {
    public TextField editId;
    public ListView listview;
    public Label txtPrice;
    public Label txtAddress;
    public Label txtStatus;
    public Label txtMessage;
    private OrderManager orderManager;
    @FXML
    void initialize(){
        listview.setCellFactory(param -> new ProductOrderStatusCell());
    }
    public void setOrderManager(OrderManager orderManager){
        this.orderManager = orderManager;
    }
    public void btnSearchClicked(ActionEvent actionEvent) {
        clean();
        int id;
        try{
            id = Integer.parseInt(editId.getText());
            if (orderManager == null){
                throw new NullPointerException();
            }
        } catch (NumberFormatException e){
            txtMessage.setText("Not number");
            return;
        } catch (NullPointerException e){
            System.err.println("Order Manager not set");
            return;
        }
        Order order = orderManager.getById(id);
        if (order != null) {
            listview.setItems(FXCollections.observableArrayList(order.getProductsWithAmount()));
            double total = 0;
            for (ProductWithAmount product : order.getProductsWithAmount()) {
                total += product.getAmount() * product.getProduct().getPrice();
            }
            txtPrice.setText(Double.toString(total));
            txtStatus.setText(order.getStatus());
        }
        else {
            txtMessage.setText("No order with that id.");
        }
    }
    public void clean(){
        listview.getItems().clear();
        txtPrice.setText("");
        txtMessage.setText("");
        txtStatus.setText("");
        txtAddress.setText("");
    }
    public void btnBackClicked(ActionEvent actionEvent) {
        editId.setText("");
        clean();
        MainApp.showMainPage();
    }
}
