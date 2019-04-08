package ru.ifmo.uml.dal.dto;

public class ProductOrder {

    private int productId;
    private int orderId;
    private int count;

    public ProductOrder() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int bookingId) {
        this.orderId = bookingId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "productId=" + productId +
                ", orderId=" + orderId +
                ", count=" + count +
                '}';
    }
}
