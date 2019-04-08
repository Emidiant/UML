package ru.ifmo.uml.dal.implementations;

import ru.ifmo.uml.dal.dto.Product;
import ru.ifmo.uml.dal.interfaces.ProductDao;

import ru.ifmo.uml.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements ProductDao {
    @Override
    public void add(Product product) {
        String query = "INSERT INTO PRODUCT(NAME, TYPE, SIZEID, COLORID, PRICE, COUNT, SPECIFICATION, ARTICLE,IMAGE) VALUES(?, ?, ?, ?, ?, ?, ?,?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getType());
            preparedStatement.setInt(3, product.getSizeId());
            preparedStatement.setInt(4, product.getColorId());
            preparedStatement.setDouble(5, product.getPrice());
            preparedStatement.setInt(6, product.getCount());
            preparedStatement.setString(7, product.getSpecification());
            preparedStatement.setInt(8,product.getArticle());
            preparedStatement.setString(9,product.getImageUrl());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM PRODUCT";
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Product product = new Product();
                try {
                    product.setProductId(rs.getInt("PRODUCTID"));
                    product.setName(rs.getString("NAME"));
                    product.setType(rs.getString("TYPE"));
                    product.setSizeId(rs.getInt("SIZEID"));
                    product.setColorId(rs.getInt("COLORID"));
                    product.setPrice(rs.getDouble("PRICE"));
                    product.setCount(rs.getInt("COUNT"));
                    product.setSpecification(rs.getString("SPECIFICATION"));
                    product.setArticle(rs.getInt("ARTICLE"));
                    product.setImageUrl(rs.getString("IMAGE"));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getById(Integer id) {
        String query = "SELECT * FROM PRODUCT WHERE PRODUCTID = ?";
        Product product = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("PRODUCTID"));
                product.setName(rs.getString("NAME"));
                product.setType(rs.getString("TYPE"));
                product.setSizeId(rs.getInt("SIZEID"));
                product.setColorId(rs.getInt("COLORID"));
                product.setPrice(rs.getDouble("PRICE"));
                product.setCount(rs.getInt("COUNT"));
                product.setSpecification(rs.getString("SPECIFICATION"));
                product.setArticle(rs.getInt("ARTICLE"));
                product.setImageUrl(rs.getString("IMAGE"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }

    @Override
    public void update(Product product) {
        String query = "UPDATE PRODUCT SET NAME = ?, TYPE = ?, SIZEID = ?, COLORID = ?, PRICE = ?, COUNT = ?, SPECIFICATION = ?, ARTICLE = ?, IMAGE = ? WHERE PRODUCTID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getType());
            preparedStatement.setInt(3, product.getSizeId());
            preparedStatement.setInt(4, product.getColorId());
            preparedStatement.setDouble(5, product.getPrice());
            preparedStatement.setInt(6, product.getCount());
            preparedStatement.setString(7, product.getSpecification());
            preparedStatement.setInt(8,product.getArticle());
            preparedStatement.setString(9,product.getImageUrl());
            preparedStatement.setInt(10, product.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Product product) {
        String query = "DELETE FROM PRODUCT WHERE PRODUCTID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
