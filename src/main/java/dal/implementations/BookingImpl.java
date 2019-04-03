package dal.implementations;

import dal.interfaces.BookingDao;
import entity.Booking;
import utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookingImpl implements BookingDao {
    @Override
    public void add(Booking booking) {

        String query = "INSERT INTO BOOKING(CUSTOMERID, DELIVERYTYPE, STATUS) VALUES(?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, booking.getCustomerId());
            preparedStatement.setString(2, booking.getDeliveryType());
            preparedStatement.setString(3, booking.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Booking> getAll() {
        String query = "SELECT * FROM BOOKING";
        List<Booking> orders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Booking order = new Booking();
                try {
                    order.setBookingId(rs.getInt("BOOKINGID"));
                    order.setCustomerId(rs.getInt("CUSTOMERID"));
                    order.setDeliveryType(rs.getString("DELIVERYTYPE"));
                    order.setStatus(rs.getString("STATUS"));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Booking getById(Integer id) {
        String query = "SELECT * FROM BOOKING WHERE BOOKINGID = ?";
        Booking order = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                order = new Booking();
                order.setBookingId(rs.getInt("BOOKINGID"));
                order.setCustomerId(rs.getInt("CUSTOMERID"));
                order.setDeliveryType(rs.getString("DELIVERYTYPE"));
                order.setStatus(rs.getString("STATUS"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return order;
    }

    @Override
    public void update(Booking booking) {
        String query = "UPDATE BOOKING SET CUSTOMERID = ?, DELIVERYTYPE = ?, STATUS = ? WHERE BOOKINGID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, booking.getCustomerId());
            preparedStatement.setString(2, booking.getDeliveryType());
            preparedStatement.setString(3, booking.getStatus());
            preparedStatement.setInt(4, booking.getBookingId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Booking order) {
        String query = "DELETE FROM BOOKING WHERE BOOKINGID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, order.getBookingId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
