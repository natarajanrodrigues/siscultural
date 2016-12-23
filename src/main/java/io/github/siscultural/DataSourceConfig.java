package io.github.siscultural;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by natarajan on 22/12/16.
 */
@Configuration
public class DataSourceConfig {

    Logger log = LoggerFactory.getLogger(getClass());

    @Bean
    @Profile("prod")
    public DataSource postgresDataSource() {
        String databaseUrl = System.getenv("DATABASE_URL");
        log.info("Initializing PostgreSQL database: {}", databaseUrl);

        URI dbUri;
        try {
            dbUri = new URI(databaseUrl);
        }
        catch (URISyntaxException e) {
            log.error(String.format("Invalid DATABASE_URL: %s", databaseUrl), e);
            return null;
        }

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':'
                + dbUri.getPort() + dbUri.getPath();

        org.apache.tomcat.jdbc.pool.DataSource dataSource
                = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnReturn(true);
        dataSource.setValidationQuery("SELECT 1");
        return dataSource;
    }

}