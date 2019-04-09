package ru.ifmo.uml.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Pair;
import ru.ifmo.uml.dal.implementations.OrderImpl;
import ru.ifmo.uml.dal.implementations.ProductImpl;
import ru.ifmo.uml.dal.implementations.ProductOrderImpl;
import ru.ifmo.uml.entity.Order;
import ru.ifmo.uml.entity.Product;
import ru.ifmo.uml.entity.ProductOrder;
import ru.ifmo.uml.ui.controllers.OrdersListCell;
import ru.ifmo.uml.ui.controllers.ProductListCell;
import ru.ifmo.uml.ui.controllers.StockListCell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoOrderController {
    @FXML
    private ListView<Product> productlistview;

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
    Map<Product, Integer> productCount;

    ObservableList<Product> productList = FXCollections.observableArrayList();

    //ObservableList<Customer> customerList = FXCollections.observableArrayList();

    Stage prevStage;
    private Integer id;


    public void setStage(Stage stage) {
        this.prevStage = stage;
    }


    public void initialize() {
        productCount = new HashMap<>();

    }

    public void setId(Integer id) {
        this.id = id;
        createInfo(id);
    }

    public void createInfo(Integer id) {
        OrderImpl orderImpl = new OrderImpl();
        Order order = orderImpl.getById(id);
        ProductImpl productImpl = new ProductImpl();
        Product product = new Product();
        ProductOrderImpl productOrderImpl = new ProductOrderImpl();
        ArrayList<Integer> count = new ArrayList<>();


        List<ProductOrder> productOrders = productOrderImpl.getByOrderId(order);
        for (ProductOrder p : productOrders) {
            product = productImpl.getById(p.getProductId());

            //count.add(p.getCount());
            productCount.put(product, p.getCount());
            productList.add(product);
            //System.out.println(product.toString());
        }
        createProductList(productList);

        deliveryType.setText(order.getDeliveryType());
        orderId.setText(Integer.toString(order.getOrderId()));
        customerId.setText(Integer.toString(order.getCustomerId()));

        choiceStatus.getItems().addAll("not confirmed", "confirmed", "transferred to delivery service", "closed", "canceled");
        choiceStatus.setValue(order.getStatus());

        saveStatus.setOnAction(e -> {
            OrderImpl orderImpl1 = new OrderImpl();
            order.setStatus(choiceStatus.getValue());
            //System.out.println(order.getStatus() + " " + order.getOrderId());
            orderImpl1.update(order);
        });
    }

    public void createProductList(ObservableList productList) {
        //ProductListCell productListCell = new ProductListCell();
        //productListCell.setCountCustomer(count);
        productlistview.setCellFactory(param -> new ProductListCell() {
            @Override
            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);
                setCountCustomer(productCount);
            }
        });
        productlistview.setItems(productList);

    }
}
