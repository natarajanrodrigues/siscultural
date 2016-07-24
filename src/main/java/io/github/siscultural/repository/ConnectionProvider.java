package io.github.siscultural.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public final class ConnectionProvider {

    private static ConnectionProvider connectionProvider;

    private BasicDataSource connectionPool;

    private ConnectionProvider() {
    }

    public static synchronized ConnectionProvider getInstance() {

        if (connectionProvider == null) {
            connectionProvider = new ConnectionProvider();
        }

        return connectionProvider;
    }

    public Connection getConnection() throws SQLException {

        if (connectionPool == null || connectionPool.isClosed()) {

            try {

                Properties properties = new Properties();
                properties.load(new FileInputStream(getClass().getResource("/database.properties").toURI().getPath()));

                String url      = properties.getProperty("url");
                String user     = properties.getProperty("user");
                String password = properties.getProperty("password");
                String driver   = properties.getProperty("driver");

                connectionPool = new BasicDataSource();

                connectionPool.setUsername(user);
                connectionPool.setPassword(password);
                connectionPool.setDriverClassName(driver);
                connectionPool.setUrl(url);
                
            } catch (IOException | URISyntaxException ex) {
                connectionPool.close();
                return null;
            }

        }

        return connectionPool.getConnection();
    }

}
