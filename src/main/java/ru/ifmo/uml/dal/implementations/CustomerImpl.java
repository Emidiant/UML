package ru.ifmo.uml.dal.implementations;

import ru.ifmo.uml.dal.interfaces.CustomerDao;
import ru.ifmo.uml.entity.Customer;
import ru.ifmo.uml.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerImpl implements CustomerDao {
    @Override
    public void add(Customer customer) {
        String query = "INSERT INTO CUSTOMER(SECONDNAME, FIRSTNAME, EMAIL, ADDRESS, PHONENUMBER) VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customer.getSecondName());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAll() {
        String query = "SELECT * FROM CUSTOMER";
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Customer customer = new Customer();
                try {
                    customer.setCustomerID(rs.getInt("CUSTOMERID"));
                    customer.setSecondName(rs.getString("SECONDNAME"));
                    customer.setFirstName(rs.getString("FIRSTNAME"));
                    customer.setEmail(rs.getString("EMAIL"));
                    customer.setAddress(rs.getString("ADDRESS"));
                    customer.setPhoneNumber(rs.getString("PHONENUMBER"));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer getById(Integer id) {
        String query = "SELECT * FROM CUSTOMER WHERE CUSTOMERID = ?";
        Customer customer = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerID(rs.getInt("CUSTOMERID"));
                customer.setSecondName(rs.getString("SECONDNAME"));
                customer.setFirstName(rs.getString("FIRSTNAME"));
                customer.setEmail(rs.getString("EMAIL"));
                customer.setAddress(rs.getString("ADDRESS"));
                customer.setPhoneNumber(rs.getString("PHONENUMBER"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customer;
    }

    @Override
    public void update(Customer customer) {
        String query = "UPDATE CUSTOMER SET SECONDNAME = ?, FIRSTNAME = ?, EMAIL = ?, ADDRESS = ?, PHONENUMBER = ? WHERE CUSTOMERID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customer.getSecondName());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setInt(6, customer.getCustomerID());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Customer customer) {
        String query = "DELETE FROM CUSTOMER WHERE CUSTOMERID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customer.getCustomerID());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
