package ru.ifmo.uml.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import ru.ifmo.uml.dal.dto.Order;
import ru.ifmo.uml.dal.dto.Product;
import ru.ifmo.uml.dal.dto.ProductOrder;
import ru.ifmo.uml.dal.implementations.OrderImpl;
import ru.ifmo.uml.dal.implementations.ProductImpl;
import ru.ifmo.uml.dal.implementations.ProductOrderImpl;
import ru.ifmo.uml.ui.controllers.ProductListCell;

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
    private Button backButton;


    @FXML
    private ComboBox<String> statusBox;

    Map<Product, Integer> productCount;

    ObservableList<Product> productList = FXCollections.observableArrayList();

    Stage prevStage;
    private Integer id;


    public void setStage(Stage stage) {
        this.prevStage = stage;
    }


    public void initialize() {
        productCount = new HashMap<>();

        backButton.setOnAction(event -> {
            MainApp.showCoordPage();
        });

    }

    public void setId(Integer id) {
        this.id = id;
        createInfo(id);
    }

    public void createInfo(Integer id) {
        productList.clear();

        OrderImpl orderImpl = new OrderImpl();
        Order order = orderImpl.getById(id);
        ProductImpl productImpl = new ProductImpl();
        Product product = new Product();
        ProductOrderImpl productOrderImpl = new ProductOrderImpl();
        ArrayList<Integer> count = new ArrayList<>();


        List<ProductOrder> productOrders = productOrderImpl.getByOrderId(order);
        for (ProductOrder p : productOrders) {
            product = productImpl.getById(p.getProductId());

            productCount.put(product, p.getCount());
            productList.add(product);
        }
        createProductList(productList);

        deliveryType.setText(order.getDeliveryType());
        orderId.setText(Integer.toString(order.getOrderId()));


        statusBox.setValue(order.getStatus());
        if (order.getStatus().equals("not confirmed") || order.getStatus().equals("confirmed")){
            statusBox.getItems().addAll("not confirmed", "confirmed", "canceled");

            statusBox.valueProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        order.setStatus(newValue);
                        orderImpl.update(order);
                        MainApp.clearAll();
                    });
        }


    }

    public void createProductList(ObservableList productList) {
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
