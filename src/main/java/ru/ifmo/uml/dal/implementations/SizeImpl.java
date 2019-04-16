package ru.ifmo.uml.dal.implementations;

import ru.ifmo.uml.dal.dto.Size;
import ru.ifmo.uml.dal.interfaces.SizeDao;
import ru.ifmo.uml.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SizeImpl implements SizeDao {

    @Override
    public void add(Size size) {
        String query = "INSERT INTO SIZE(SIZENAME) VALUES(?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, size.getSizeName());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Size> getAll() {
        String query = "SELECT * FROM SIZE";
        List<Size> sizes = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Size size = new Size();
                try {
                    size.setSizeId(rs.getInt("SIZEID"));
                    size.setSizeName(rs.getString("SIZENAME"));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                sizes.add(size);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sizes;
    }

    @Override
    public Size getById(Integer id) {
        String query = "SELECT * FROM SIZE WHERE SIZEID = ?";
        Size size = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                size = new Size();
                size.setSizeId(rs.getInt("SIZEID"));
                size.setSizeName(rs.getString("SIZENAME"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return size;
    }

    @Override
    public void update(Size size) {
        String query = "UPDATE SIZE SET SIZENAME = ? WHERE SIZEID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, size.getSizeName());
            preparedStatement.setInt(2, size.getSizeId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Size size) {
        String query = "DELETE FROM SIZE WHERE SIZEID = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, size.getSizeId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Integer getByName(String sizeName) {
        String query = "SELECT * FROM SIZE WHERE SIZENAME = ?";
        Size size = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sizeName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                size = new Size();
                size.setSizeId(rs.getInt("SIZEID"));
                size.setSizeName(rs.getString("SIZENAME"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return size.getSizeId();
    }
}
