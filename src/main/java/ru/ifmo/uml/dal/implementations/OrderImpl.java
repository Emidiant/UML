package ru.ifmo.uml.dal.implementations;

import ru.ifmo.uml.dal.dto.Order;
import ru.ifmo.uml.dal.interfaces.OrderDao;
import ru.ifmo.uml.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderImpl implements OrderDao {
    @Override
    public void add(Order order) {
        String query = "INSERT INTO \"ORDER\"(CUSTOMERID, DELIVERYTYPE, STATUS) VALUES(?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setString(2, order.getDeliveryType());
            preparedStatement.setString(3, order.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM \"ORDER\"";
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Order order = new Order();
                try {
                    order.setOrderId(rs.getInt("ORDERID"));
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
    public Order getById(Integer id) {
        String query = "SELECT * FROM \"ORDER\" WHERE ORDERID = ?";
        Order order = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                order = new Order();
                order.setOrderId(rs.getInt("ORDERID"));
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
    public void update(Order order) {
        String query = "UPDATE \"ORDER\" SET CUSTOMERID = ?, DELIVERYTYPE = ?, STATUS = ? WHERE ORDERID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, order.getCustomerId());
            preparedStatement.setString(2, order.getDeliveryType());
            preparedStatement.setString(3, order.getStatus());
            preparedStatement.setInt(4, order.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Order order) {
        String query = "DELETE FROM \"ORDER\" WHERE ORDERID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
