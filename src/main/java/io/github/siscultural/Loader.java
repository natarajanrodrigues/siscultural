package io.github.siscultural;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import java.net.URI;
import java.net.URISyntaxException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@SpringBootApplication
public class Loader extends SpringBootServletInitializer {

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    @Bean
    @Profile("prod")
    public DataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        DataSource basicDataSource = new DataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setDriverClassName("org.postgresql.Driver");
        basicDataSource.setTestOnBorrow(true); 
        basicDataSource.setTestWhileIdle(true); 
        basicDataSource.setTestOnReturn(true); 
        basicDataSource.setValidationQuery("SELECT 1");

        return basicDataSource;
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Loader.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Loader.class);
    }

}
