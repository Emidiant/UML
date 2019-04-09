package ru.ifmo.uml.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Pair;
import ru.ifmo.uml.dal.dto.Order;
import ru.ifmo.uml.dal.dto.Product;
import ru.ifmo.uml.dal.dto.ProductOrder;
import ru.ifmo.uml.dal.implementations.OrderImpl;
import ru.ifmo.uml.dal.implementations.ProductImpl;
import ru.ifmo.uml.dal.implementations.ProductOrderImpl;



import ru.ifmo.uml.ui.controllers.OrdersListCell;
import ru.ifmo.uml.ui.controllers.StockListCell;

import java.util.ArrayList;
import java.util.List;

public class InfoOrderController {
    @FXML
    private ListView<Order> orderslistview;

    @FXML
    private Button fuckingId;

    @FXML
    private Label orderId;

    @FXML
    private Label deliveryType;

    @FXML
    private Label customerId;

    @FXML
    private Button saveStatus;

    @FXML
    private ChoiceBox<String> choiceStatus;

    //ObservableList<Order> ordersList = FXCollections.observableArrayList();

    //ObservableList<Product> productList = FXCollections.observableArrayList();

    //ObservableList<Customer> customerList = FXCollections.observableArrayList();

    Stage prevStage;
    private Integer id;


    public void setStage(Stage stage){
        this.prevStage = stage;
    }


    public void initialize(){
        fuckingId.setOnAction(event -> createInfo(id));

    }

    public void setId(Integer id){
        this.id = id;
    }

    public void createInfo(Integer id){
        OrderImpl orderImpl = new OrderImpl();
        Order order = orderImpl.getById(id);
        ProductImpl productImpl = new ProductImpl();
        Product product = new Product();
        ProductOrderImpl productOrderImpl = new ProductOrderImpl();
        Pair<Integer, Integer> pair = new Pair<>(order.getOrderId(), order.getCustomerId());

        List<ProductOrder> productOrders = productOrderImpl.getByOrderId(order);
        for (ProductOrder p : productOrders){
            //TODO change to repository
            product = productImpl.getById(p.getProductId());
            System.out.println(product.toString());
        }
        deliveryType.setText(order.getDeliveryType());
        orderId.setText(Integer.toString(order.getOrderId()));
        customerId.setText(Integer.toString(order.getCustomerId()));

        choiceStatus.getItems().addAll("not confirmed", "confirmed", "transferred to delivery service","closed", "canceled");
        choiceStatus.setValue(order.getStatus());

        saveStatus.setOnAction(e -> {
            OrderImpl orderImpl1 = new OrderImpl();
            order.setStatus(choiceStatus.getValue());
            System.out.println(order.getStatus() + " " + order.getOrderId());
            orderImpl1.update(order);
        });
    }
}
