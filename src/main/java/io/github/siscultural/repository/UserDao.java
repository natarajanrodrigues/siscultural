/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repository;

import io.github.siscultural.entity.User;
import io.github.siscultural.interfaces.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public class UserDao implements Dao<User, Integer> {

    @Override
    public boolean save(User object) {

        Connection connection;
        PreparedStatement statement;

        int result = 0;

        try {

            String sql = "INSERT INTO " + getTableName()
                    + " (name, password, email, userType) VALUES (?, ?, ?, ?)";

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.prepareCall(sql);

            int count = 1;

            statement.setString(count++, object.getName());
            statement.setString(count++, object.getPassword());
            statement.setString(count++, object.getEmail());
            statement.setInt(count++, object.getType());

            result = statement.executeUpdate();

            statement.close();
            connection.close();

        } catch (SQLException ex) {
            System.err.println(ex);
            ex.printStackTrace();
        }

        return result != 0;
    }

    @Override
    public boolean delete(User object) {

        Connection connection;
        PreparedStatement statement;

        int result = 0;

        try {

            String sql = "DELETE FROM " + getTableName()
                    + " WHERE id = ?";

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.prepareCall(sql);

            int count = 1;

            statement.setInt(count++, object.getId());

            result = statement.executeUpdate();

            statement.close();
            connection.close();

        } catch (SQLException ex) {
            System.err.println(ex);
            ex.printStackTrace();
        }

        return result != 0;
    }

    @Override
    public boolean update(User object) {

        Connection connection;
        PreparedStatement statement;

        int result = 0;

        try {

            String sql = "UPDATE " + getTableName()
                    + " SET name = ?, password = ?, email = ?, userType = ?) WHERE id = ?";

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.prepareCall(sql);

            int count = 1;

            statement.setInt(count++, object.getId());

            result = statement.executeUpdate();

            statement.close();
            connection.close();

        } catch (SQLException ex) {
            System.err.println(ex);
            ex.printStackTrace();
        }

        return result != 0;
    }

    @Override
    public User searchById(Integer id) {

        Connection connection;
        PreparedStatement statement;

        ResultSet resultSet;
        User user = null;

        try {

            String sql = "SELECT * FROM " + getTableName()
                    + " WHERE id = ?";

            connection = ConnectionProvider.getInstance().getConnection();
            statement = connection.prepareCall(sql);

            int count = 1;

            statement.setInt(count++, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = fillObject(resultSet);
            }

            statement.close();
            connection.close();

        } catch (SQLException ex) {
            System.err.println(ex);
            ex.printStackTrace();
        }

        return user;
    }

    @Deprecated
    @Override
    public List<User> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User fillObject(ResultSet rs) {

        User user = new User();

        try {
            user.setEmail(rs.getString("email"));
            user.setId(rs.getInt("id"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setType(rs.getInt("userType"));
        } catch (SQLException ex) {

        }

        return user;
    }

    @Override
    public String getTableName() {
        return "SystemUser";
    }

}
