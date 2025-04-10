package org.example.postgres.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "postgres.datasource")
@Setter
@Getter
public class PostgresDataSourceProperties {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
