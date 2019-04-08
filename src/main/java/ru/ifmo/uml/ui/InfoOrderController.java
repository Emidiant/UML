package ru.ifmo.uml.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ru.ifmo.uml.dal.implementations.OrderImpl;
import ru.ifmo.uml.dal.implementations.ProductImpl;
import ru.ifmo.uml.entity.Order;
import ru.ifmo.uml.entity.Product;
import ru.ifmo.uml.ui.controllers.InfoOrderListCell;
import ru.ifmo.uml.ui.controllers.OrdersListCell;
import ru.ifmo.uml.ui.controllers.StockListCell;

public class InfoOrderController {
    @FXML
    private ListView<Order> orderslistview;

    @FXML
    private Button fuckingId;

    ObservableList<Order> ordersList = FXCollections.observableArrayList();

    ObservableList<Product> productList = FXCollections.observableArrayList();

    //ObservableList<Customer> customerList = FXCollections.observableArrayList();

    Stage prevStage;
    private Integer id;


    public void setStage(Stage stage){
        this.prevStage = stage;
    }


    public void initialize(){
        System.out.println("normal" + id);
        fuckingId.setOnAction(event -> {
            createInfoTabView(id);
        });

    }

    public void createInfoTabView(Integer id) {
        OrderImpl orderImpl = new OrderImpl();

        ordersList.clear();
        ordersList.add(orderImpl.getById(id));

        orderslistview.setCellFactory(param -> new InfoOrderListCell());
        orderslistview.setItems(ordersList);
    }

    public void setId(Integer id){
        this.id = id;
        System.out.println(id+" aaa");
    }
}
