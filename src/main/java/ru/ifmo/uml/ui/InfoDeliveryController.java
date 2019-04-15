package ru.ifmo.uml.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ru.ifmo.uml.dal.dto.*;
import ru.ifmo.uml.dal.implementations.CustomerImpl;
import ru.ifmo.uml.dal.implementations.OrderImpl;
import ru.ifmo.uml.dal.implementations.ProductImpl;
import ru.ifmo.uml.dal.implementations.ProductOrderImpl;
import ru.ifmo.uml.ui.controllers.ProductListCell;
import ru.ifmo.uml.ui.controllers.ProductListForLogistCell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoDeliveryController {

    @FXML
    private Button btnLogout;

    @FXML
    private ListView<Product> productlistview;

    @FXML
    private Button backButton;

    @FXML
    private Label orderID;

    @FXML
    private Label deliveryType;

    @FXML
    private Label customerName;

    @FXML
    private Label address;

    @FXML
    private Label email;

    @FXML
    private Label phoneNumber;

    @FXML
    private TextField productName;

    @FXML
    private TextField type;

    @FXML
    private TextField productId;

    @FXML
    private TextField price;

    @FXML
    private TextField size;

    @FXML
    private TextField color;

    @FXML
    private TextField count;

    @FXML
    private ComboBox<String> statusBox;

    Map<Product, Integer> productCount;

    ObservableList<Product> productList = FXCollections.observableArrayList();

    private Integer id;


    public void initialize() {
        productCount = new HashMap<>();

        backButton.setOnAction(event -> {
            MainApp.clearInfo();
            MainApp.showLogistPage();
        });

        btnLogout.setOnAction(event -> {
            MainApp.clearAll();
            MainApp.showMainPage();
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
        CustomerImpl customerImpl = new CustomerImpl();
        Customer customer = customerImpl.getById(order.getCustomerId());
        ArrayList<Integer> count = new ArrayList<>();


        List<ProductOrder> productOrders = productOrderImpl.getByOrderId(order);
        for (ProductOrder p : productOrders) {
            product = productImpl.getById(p.getProductId());

            productCount.put(product, p.getCount());
            productList.add(product);
        }
        createProductList(productList);

        deliveryType.setText(order.getDeliveryType());
        orderID.setText(Integer.toString(order.getOrderId()));
        customerName.setText(customer.getFirstName() + " " + customer.getSecondName());
        address.setText(customer.getAddress());
        email.setText(customer.getEmail());
        phoneNumber.setText(customer.getPhoneNumber());

        statusBox.getItems().clear();
        if (order.getDeliveryType().equals("pickup")) {
            statusBox.getItems().addAll("closed", "canceled");
        } else if (order.getDeliveryType().equals("delivery")) {
            statusBox.getItems().addAll("transferred to delivery service", "closed", "canceled");
        }
        statusBox.setValue(order.getStatus());

        statusBox.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    order.setStatus(newValue);
                    orderImpl.update(order);
                    MainApp.clearAll();
                });
    }

    public void createProductList(ObservableList productList) {
        productlistview.setCellFactory(param -> new ProductListForLogistCell() {
            @Override
            protected void updateItem(Product item, boolean empty) {
                super.updateItem(item, empty);
                setCountCustomer(productCount);
            }
        });
        productlistview.setItems(productList);

    }
}
