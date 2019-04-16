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
import ru.ifmo.uml.ui.controllers.StockListCell;

import java.util.List;


public class CoordPageController {

    @FXML
    private Button btnLogout;

    @FXML
    private Button addProduct;

    @FXML
    private ListView<Order> orderslistview;

    @FXML
    private ListView<Product> stocklistview;

    ObservableList<Order> ordersList = FXCollections.observableArrayList();

    ObservableList<Product> productList = FXCollections.observableArrayList();

    //ObservableList<Customer> customerList = FXCollections.observableArrayList();

    @FXML
    private Tab orders;

    @FXML
    private Tab stock;

    Stage prevStage;


    public void setStage(Stage stage){
        this.prevStage = stage;
    }


    public void initialize(){
        createTabOrdersView();

        stock.setOnSelectionChanged(event -> {
            createTabOrdersView();
        });

        orders.setOnSelectionChanged(event -> {
            createTabStockView();
        });

        btnLogout.setOnAction(event -> {
            MainApp.clearAll();
            MainApp.showMainPage();
        });

        addProduct.setOnAction(event -> {
            MainApp.showAddProduct();
        });

    }

    public void createTabOrdersView() {
        OrderImpl ordersImpl = new OrderImpl();

        ordersList.clear();
        ordersList.addAll(ordersImpl.getAll());

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

        /*List<Order> orders = ordersImpl.getAll();
        for (Order p : orders){
            System.out.println(p);
        }*/

        //poweroffButton.setOnMouseClicked(e -> System.exit(0));

    }

    public void createTabStockView() {
        // TODO maybe change to repository
        ProductImpl productImpl = new ProductImpl();
        productList.clear();
        productList.addAll(productImpl.getAll());
        stocklistview.setCellFactory(param -> new StockListCell());
        stocklistview.setItems(productList);
    }

    public void showDetails(Order item){
        MainApp.showInfoOrderPage(item.getOrderId());
    }
}
