package entity;

public class ProductBooking {

    private int productId;
    private int bookingId;
    private int count;

    public ProductBooking() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setOrderId(int bookingId) {
        this.bookingId = bookingId;
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
                ", bookingId=" + bookingId +
                ", count=" + count +
                '}';
    }
}
