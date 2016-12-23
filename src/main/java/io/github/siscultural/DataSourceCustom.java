package io.github.siscultural;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by natarajan on 23/12/16.
 */
@Configuration
public class DataSourceCustom {
//
//    @Bean
//    @Primary
//    @Profile("prod")
//    public BasicDataSource dataSource() throws URISyntaxException {
//        URI dbUri = new URI(System.getenv("DATABASE_URL"));
//
//        String username = dbUri.getUserInfo().split(":")[0];
//        String password = dbUri.getUserInfo().split(":")[1];
//        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
//
//        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setUrl(dbUrl);
//        basicDataSource.setUsername(username);
//        basicDataSource.setPassword(password);
//        basicDataSource.setDriverClassName("org.postgresql.Driver");
//        basicDataSource.setTestOnBorrow(true);
//        basicDataSource.setTestWhileIdle(true);
//        basicDataSource.setTestOnReturn(true);
//        basicDataSource.setValidationQuery("SELECT 1");
//
//        return basicDataSource;
//    }
}
