package ru.ifmo.uml.entity;

import javafx.util.Pair;

import java.util.List;

public class Order {
    private int orderId;
    private int customerId;
    private String deliveryType;
    private String status;
    private List<ProductWithAmount> productsWithAmount;

    public Order(int orderId, int customerId, String deliveryType, String status, List<ProductWithAmount> productsWithAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.deliveryType = deliveryType;
        this.status = status;
        this.productsWithAmount = productsWithAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public String getStatus() {
        return status;
    }

    public List<ProductWithAmount> getProductsWithAmount() {
        return productsWithAmount;
    }
}
