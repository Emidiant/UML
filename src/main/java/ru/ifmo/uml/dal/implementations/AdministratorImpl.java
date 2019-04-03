package ru.ifmo.uml.dal.implementations;

import ru.ifmo.uml.dal.interfaces.AdministratorDao;
import ru.ifmo.uml.entity.Administrator;
import ru.ifmo.uml.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorImpl implements AdministratorDao {
    @Override
    public void add(Administrator administrator) {
        String query = "INSERT INTO ADMINISTRATION(SECONDNAME, FIRSTNAME, EMAIL, LEVEL, LOGIN, PASSWORD) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, administrator.getSecondName());
            preparedStatement.setString(2, administrator.getFirstName());
            preparedStatement.setString(3, administrator.getEmail());
            preparedStatement.setString(4, administrator.getLevel());
            preparedStatement.setString(5, administrator.getLogin());
            preparedStatement.setString(6, administrator.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Administrator> getAll() {
        String query = "SELECT * FROM ADMINISTRATION";
        List<Administrator> administrators = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Administrator administrator = new Administrator();
                try {
                    administrator.setAdministratorId(rs.getInt("ADMINISTRATIONID"));
                    administrator.setSecondName(rs.getString("SECONDNAME"));
                    administrator.setFirstName(rs.getString("FIRSTNAME"));
                    administrator.setEmail(rs.getString("EMAIL"));
                    administrator.setLevel(rs.getString("LEVEL"));
                    administrator.setLogin(rs.getString("LOGIN"));
                    administrator.setPassword(rs.getString("PASSWORD"));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                administrators.add(administrator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administrators;
    }

    @Override
    public Administrator getById(Integer id) {
        String query = "SELECT * FROM ADMINISTRATION WHERE ADMINISTRATIONID = ?";
        Administrator administrator = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                administrator = new Administrator();
                administrator.setAdministratorId(rs.getInt("ADMINISTRATIONID"));
                administrator.setSecondName(rs.getString("SECONDNAME"));
                administrator.setFirstName(rs.getString("FIRSTNAME"));
                administrator.setEmail(rs.getString("EMAIL"));
                administrator.setLevel(rs.getString("LEVEL"));
                administrator.setLogin(rs.getString("LOGIN"));
                administrator.setPassword(rs.getString("PASSWORD"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return administrator;
    }

    @Override
    public void update(Administrator administrator) {
        String query = "UPDATE ADMINISTRATION SET SECONDNAME = ?, FIRSTNAME = ?, EMAIL = ?, LEVEL = ?, LOGIN = ?, PASSWORD = ? WHERE ADMINISTRATIONID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, administrator.getSecondName());
            preparedStatement.setString(2, administrator.getFirstName());
            preparedStatement.setString(3, administrator.getEmail());
            preparedStatement.setString(4, administrator.getLevel());
            preparedStatement.setString(5, administrator.getLogin());
            preparedStatement.setString(6, administrator.getPassword());
            preparedStatement.setInt(7, administrator.getAdministratorId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Administrator administrator) {
        String query = "DELETE FROM ADMINISTRATION WHERE ADMINISTRATIONID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, administrator.getAdministratorId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
