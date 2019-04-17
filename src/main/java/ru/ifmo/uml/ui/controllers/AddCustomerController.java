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

        CustomerImpl customerImpl = new CustomerImpl();


        email.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Customer customer = new Customer();
                    if (customerImpl.getCustomerIdByEmail(email.getText()) != 0){
                        customer = customerImpl.getById(customerImpl.getCustomerIdByEmail(email.getText()));
                        secondName.setText(customer.getSecondName());
                        firstName.setText(customer.getFirstName());
                        phoneNumber.setText(customer.getPhoneNumber());
                        address.setText(customer.getAddress());
                    }else{
                        secondName.clear();
                        firstName.clear();
                        phoneNumber.clear();
                        address.clear();
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
                    || address.getText().equals("")
                    || deliveryType.getValue().equals(null))

                throw new Exception();
            customer.setSecondName(secondName.getText());
            customer.setAddress(address.getText());
            customer.setFirstName(firstName.getText());
            customer.setEmail(email.getText());
            customer.setPhoneNumber(phoneNumber.getText());

            int customerId;

            if (customerImpl.getCustomerIdByEmail(email.getText()) != 0){
                customerId = customerImpl.getCustomerIdByEmail(email.getText());
                //менять имя, email, фамилию нельзя, телефон, address
            }else{
                customerImpl.add(customer);
                customerId = customerImpl.getLastCustomerId();
            }


            order.setDeliveryType(deliveryType.getValue());
            order.setStatus("not confirmed");
            order.setCustomerId(customerId);
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
