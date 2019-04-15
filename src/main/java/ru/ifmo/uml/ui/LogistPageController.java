package ru.ifmo.uml.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ru.ifmo.uml.dal.dto.Order;
import ru.ifmo.uml.dal.dto.Product;
import ru.ifmo.uml.dal.implementations.CustomerImpl;
import ru.ifmo.uml.dal.implementations.OrderImpl;
import ru.ifmo.uml.dal.implementations.ProductImpl;


import ru.ifmo.uml.ui.controllers.MainListCell;
import ru.ifmo.uml.ui.controllers.OrdersListCell;

import java.util.List;


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
