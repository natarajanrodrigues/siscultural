/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import org.springframework.stereotype.Repository;

/**
 *
 * @author victor
 */
@Repository
public class Dao<T> {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(T object) {
        
        entityManager.persist(object);
    }

    public T find(T object) {

        return (T) entityManager.find(object.getClass(), object);
    }

    private List<T> executeNativeQuery(String sqlQuery, Class<?> resultClass) {

        Query query = entityManager.createNativeQuery(sqlQuery, resultClass);

        return query.getResultList();
    }

    public List<T> findByAttributes(Class<?> entityClass, Map conditions) {

        Table table = entityClass.getAnnotation(Table.class);

        String tableName = "";

        try {
            tableName = table.name();
        } catch (NullPointerException ex) {
            System.err.println("Entity doesn't have tabe name annotation");
        }

        if (tableName == null || tableName.trim().isEmpty()) {

            tableName = entityClass.getSimpleName();
        }

        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM ");

        sqlBuilder.append(tableName).
                append(" t WHERE ");

        Set<String> keys = conditions.keySet();
        Iterator<String> it = keys.iterator();

        String key;

        while (it.hasNext()) {

            key = it.next();

            sqlBuilder.append("t.").
                    append(key).
                    append(" = ").
                    append("'").
                    append(conditions.get(key)).
                    append("'");

            if (it.hasNext()) {
                sqlBuilder.append(" AND ");
            }

        }

        return executeNativeQuery(sqlBuilder.toString(), entityClass);
    }

}
