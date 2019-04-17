package ru.ifmo.uml.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.ifmo.uml.dal.dto.Customer;
import ru.ifmo.uml.dal.dto.Order;
import ru.ifmo.uml.dal.dto.ProductOrder;
import ru.ifmo.uml.dal.implementations.CustomerImpl;
import ru.ifmo.uml.dal.implementations.OrderImpl;
import ru.ifmo.uml.dal.implementations.ProductOrderImpl;
import ru.ifmo.uml.entity.Cart;
import ru.ifmo.uml.ui.MainApp;

import java.util.Map;

public class AddCustomerController {

    @FXML
    private TextField warningTxt;

    @FXML
    private TextField secondName;

    @FXML
    private TextField firstName;

    @FXML
    private TextField email;

    @FXML
    private TextField address;

    @FXML
    private ComboBox<String> deliveryType;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button btnBack;

    private Map<Integer, Integer> products;

    public void setCart(Cart cart){
        products = cart.getProducts();
    }

    public void initialize() {
        createDeliveryTypeInfo();

        btnBack.setOnAction(event -> {
            MainApp.showCartPage();
        });
    }

    void createDeliveryTypeInfo(){
        deliveryType.getItems().addAll("pickup", "delivery");

        deliveryType.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue.equals("pickup")){
                        address.setText("С-Пб, Кирочная 8Б");
                        address.setDisable(true);
                    }else {
                        address.clear();
                        address.setDisable(false);
                    }
                });
    }

    @FXML
    void btnFinishClicked() {
        Customer customer = new Customer();
        CustomerImpl customerImpl = new CustomerImpl();

        Order order = new Order();
        OrderImpl orderImpl = new OrderImpl();

        ProductOrder productOrder = new ProductOrder();
        ProductOrderImpl productOrderImpl = new ProductOrderImpl();

        try {
            if (secondName.getText().equals("")
                    || firstName.getText().equals("")
                    || address.getText().equals("")
                    || email.getText().equals("")
                    || phoneNumber.getText().equals("")
                    || address.getText().equals(""))
                throw new Exception();
            customer.setSecondName(secondName.getText());
            customer.setAddress(address.getText());
            customer.setFirstName(firstName.getText());
            customer.setEmail(email.getText());
            customer.setPhoneNumber(phoneNumber.getText());
            customerImpl.add(customer);

            //todo i don't like this id getting!!! (emi)
            order.setDeliveryType(deliveryType.getValue());
            order.setStatus("not confirmed");
            order.setCustomerId(customerImpl.getLastCustomerId());
            orderImpl.add(order);
            int orderId = orderImpl.getLastOrderId();

            for (Integer productId : products.keySet()) {
                productOrder.setCount(products.get(productId));
                productOrder.setOrderId(orderId);
                productOrder.setProductId(productId);
                productOrderImpl.add(productOrder);
            }

            MainApp.showFinishOrderPage(orderId);
        }catch (Exception e){
            warningTxt.setText("You must fill in all fields");
        }


    }
}
