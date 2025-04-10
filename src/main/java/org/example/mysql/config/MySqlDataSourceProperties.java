package org.example.mysql.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "mysql.datasource")
@Setter
@Getter
public class MySqlDataSourceProperties {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
