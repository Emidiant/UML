package entity;

public class Booking {

    private int bookingId;
    private int customerId;
    private String deliveryType;
    private String status;

    public Booking() {
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "bookingId=" + bookingId +
                ", customerId=" + customerId +
                ", deliveryType='" + deliveryType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
