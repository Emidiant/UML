package ru.ifmo.uml.dal.implementations;

import ru.ifmo.uml.dal.dto.ProductOrder;
import ru.ifmo.uml.dal.interfaces.ProductOrderDao;
import javafx.util.Pair;
import ru.ifmo.uml.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductOrderImpl implements ProductOrderDao {
    @Override
    public void add(ProductOrder productOrder) {
        String query = "INSERT INTO PRODUCTORDER(PRODUCTID, ORDERID, COUNT) VALUES(?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productOrder.getProductId());
            preparedStatement.setInt(2, productOrder.getOrderId());
            preparedStatement.setInt(3, productOrder.getCount());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<ProductOrder> getAll() {
        String query = "SELECT * FROM PRODUCTORDER";
        List<ProductOrder> productOrders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                ProductOrder productOrder = new ProductOrder();
                try {
                    productOrder.setProductId(rs.getInt("PRODUCTID"));
                    productOrder.setOrderId(rs.getInt("ORDERID"));
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
    public ProductOrder getById(Pair<Integer, Integer> ids) {
        String query = "SELECT * FROM PRODUCTORDER WHERE PRODUCTID = ? AND ORDERID = ?";
        ProductOrder productOrder = null;

        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ids.getKey());
            preparedStatement.setInt(2, ids.getValue());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                productOrder = new ProductOrder();
                productOrder.setProductId(rs.getInt("PRODUCTID"));
                productOrder.setOrderId(rs.getInt("ORDERID"));
                productOrder.setCount(rs.getInt("COUNT"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productOrder;
    }

    @Override
    public void update(ProductOrder productOrder) {
        String query = "UPDATE PRODUCTORDER SET COUNT = ? WHERE PRODUCTID = ? AND ORDERID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productOrder.getCount());
            preparedStatement.setInt(2, productOrder.getProductId());
            preparedStatement.setInt(3, productOrder.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(ProductOrder productOrder) {
        String query = "DELETE FROM PRODUCTORDER WHERE PRODUCTID = ? AND ORDERID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, productOrder.getProductId());
            preparedStatement.setInt(2, productOrder.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<ProductOrder> getByOrderId(Order order) {
        String query = "SELECT * FROM PRODUCTORDER WHERE ORDERID = ?";
        ProductOrder productOrder = null;
        List<ProductOrder> productOrders = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, order.getOrderId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                productOrder = new ProductOrder();
                productOrder.setProductId(rs.getInt("PRODUCTID"));
                productOrder.setOrderId(rs.getInt("ORDERID"));
                productOrder.setCount(rs.getInt("COUNT"));
                productOrders.add(productOrder);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return productOrders;
    }
}
