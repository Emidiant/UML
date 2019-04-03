package ru.ifmo.uml.dal.implementations;

import ru.ifmo.uml.dal.interfaces.ColorDao;
import ru.ifmo.uml.utils.ConnectionUtil;
import ru.ifmo.uml.entity.Color;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ColorImpl implements ColorDao {
    @Override
    public void add(Color color) {
        String query = "INSERT INTO COLOR(COLORNAME) VALUES(?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, color.getColorName());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Color> getAll() {
        String query = "SELECT * FROM COLOR";
        List<Color> colors = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Color color = new Color();
                try {
                    color.setColorId(rs.getInt("COLORID"));
                    color.setColorName(rs.getString("COLORNAME"));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                colors.add(color);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colors;
    }

    @Override
    public Color getById(Integer id) {
        String query = "SELECT * FROM COLOR WHERE COLORID = ?";
        Color color = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                color = new Color();
                color.setColorId(rs.getInt("COLORID"));
                color.setColorName(rs.getString("COLORNAME"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return color;
    }

    @Override
    public void update(Color color) {
        String query = "UPDATE COLOR SET COLORNAME = ? WHERE COLORID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, color.getColorName());
            preparedStatement.setInt(2, color.getColorId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Color color) {
        String query = "DELETE FROM COLOR WHERE COLORID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, color.getColorId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
