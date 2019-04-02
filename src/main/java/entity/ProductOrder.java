package entity;

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

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
