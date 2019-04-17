package ru.ifmo.uml.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import ru.ifmo.uml.dal.dto.Order;
import ru.ifmo.uml.dal.implementations.OrderImpl;


public class LogistPageController {

    @FXML
    private Button btnLogout;

    @FXML
    private ListView<Order> orderslistview;

    ObservableList<Order> ordersList = FXCollections.observableArrayList();

    @FXML
    private Tab orders;

    public void initialize(){
        createTabOrdersView();

        btnLogout.setOnAction(event -> {
            MainApp.clearAll();
            MainApp.showMainPage();
        });
    }

    public void createTabOrdersView() {
        OrderImpl ordersImpl = new OrderImpl();

        ordersList.clear();
        for (Order ord : ordersImpl.getAll()) {
            if (ord.getStatus().equals("transferred to delivery service")
                    || ord.getStatus().equals("confirmed"))
                ordersList.add(ord);
        }

        orderslistview.setCellFactory(param -> {
                    OrdersListCell ordersListCell = new OrdersListCell();

                    ordersListCell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                        if (event.getButton() == MouseButton.PRIMARY && (!ordersListCell.isEmpty())) {
                            Order item = ordersListCell.getItem();
                            showDetails(item);
                        }
                    });
                    return ordersListCell;
                }
        );
        orderslistview.setItems(ordersList);

    }

    public void showDetails(Order item){
        MainApp.showInfoDeliveryPage(item.getOrderId());
    }
}
