package dal.implementations;

import dal.interfaces.ProductOrderDao;
import entity.ProductBooking;
import javafx.util.Pair;
import utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductBookingImpl implements ProductOrderDao {
    @Override
    public void add(ProductBooking productOrder) {
        String query = "INSERT INTO PRODUCTBOOKING(PRODUCTID, BOOKINGID, COUNT) VALUES(?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productOrder.getProductId());
            preparedStatement.setInt(2, productOrder.getBookingId());
            preparedStatement.setInt(3, productOrder.getCount());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<ProductBooking> getAll() {
        String query = "SELECT * FROM PRODUCTBOOKING";
        List<ProductBooking> productOrders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                ProductBooking productOrder = new ProductBooking();
                try {
                    productOrder.setProductId(rs.getInt("PRODUCTID"));
                    productOrder.setOrderId(rs.getInt("BOOKINGID"));
                    productOrder.setCount(rs.getInt("COUNT"));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                productOrders.add(productOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productOrders;
    }

    @Override
    public ProductBooking getById(Pair<Integer, Integer> ids) {
        String query = "SELECT * FROM PRODUCTBOOKING WHERE PRODUCTID = ? AND BOOKINGID = ?";
        ProductBooking productOrder = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ids.getKey());
            preparedStatement.setInt(2, ids.getValue());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                productOrder = new ProductBooking();
                productOrder.setProductId(rs.getInt("PRODUCTID"));
                productOrder.setOrderId(rs.getInt("BOOKINGID"));
                productOrder.setCount(rs.getInt("COUNT"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productOrder;
    }

    @Override
    public void update(ProductBooking productOrder) {
        String query = "UPDATE PRODUCTBOOKING SET COUNT = ? WHERE PRODUCTID = ? AND BOOKINGID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productOrder.getCount());
            preparedStatement.setInt(2, productOrder.getProductId());
            preparedStatement.setInt(3, productOrder.getBookingId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(ProductBooking productOrder) {
        String query = "DELETE FROM PRODUCTBOOKING WHERE PRODUCTID = ? AND BOOKINGID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productOrder.getProductId());
            preparedStatement.setInt(2, productOrder.getBookingId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
