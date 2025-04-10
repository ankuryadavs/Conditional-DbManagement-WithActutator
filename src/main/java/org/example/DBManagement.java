package org.example;

import org.example.mysql.config.MySqlDataSourceProperties;
import org.example.postgres.config.PostgresDataSourceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({PostgresDataSourceProperties.class, MySqlDataSourceProperties.class})
public class DBManagement {
    public static void main(String[] args) {
        SpringApplication.run(DBManagement.class,args);
    }
}