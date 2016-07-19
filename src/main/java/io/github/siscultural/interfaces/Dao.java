/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.interfaces;

import io.github.siscultural.repository.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 * @param <T> Object Type
 * @param <I> Object Id Type
 */
public interface Dao<T, I> {

    boolean save(T object);

    boolean delete(T object);

    boolean update(T object);

    T searchById(I id);

    default List<T> searchByAttribute(String key, String value) {

        Map<String, String> map = new HashMap<>();

        map.put(key, value);

        return searchByAttributes(map);
    }

    default List<T> searchByAttributes(Map<String, String> map) {

        List<T> results = new ArrayList<>();
        PreparedStatement ps;
        Connection connection;

        try {

            connection = ConnectionProvider.getInstance().getConnection();

            StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM ");

            sqlBuilder.append(getTableName()).
                    append(" WHERE ");

            Set<String> keys    = map.keySet();
            Iterator<String> it = keys.iterator();

            String key;

            while (it.hasNext()) {

                key = it.next();

                sqlBuilder.append(key).
                        append(" = ").
                        append("'").
                        append(map.get(key)).
                        append("'");

                if (it.hasNext()) {
                    sqlBuilder.append(" AND ");
                }

            }

            ps = connection.prepareStatement(sqlBuilder.toString());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                T object = fillObject(rs);

                results.add(object);
            }

            ps.close();
            connection.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        return results;
    }

    List<T> listAll();

    T fillObject(ResultSet rs);

    String getTableName();

}
