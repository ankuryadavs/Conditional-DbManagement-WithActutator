package org.example.postgres.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        basePackages = "org.example.postgres.repo",
        transactionManagerRef = "secondaryTransactionManager"

)
public class ProductRepoConfig {

    @Autowired
    private PostgresDataSourceProperties dataSourceProperties;
    @Bean("secondaryDatasource")
    public DataSource dataSource()
    {

        return DataSourceBuilder.create()
                .url(dataSourceProperties.getUrl())
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .driverClassName(dataSourceProperties.getDriverClassName())
                .build();
    }

    @Bean("secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localEntityManagerFactoryBean(
            @Qualifier("secondaryDatasource") DataSource dataSource,
            EntityManagerFactoryBuilder entityManagerFactoryBuilder
    )
    {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        return entityManagerFactoryBuilder
                  .dataSource(dataSource)
                  .packages("org.example.postgres.entity")
                  .persistenceUnit("secondaryDatabaseUnit")
                  .properties(properties)
                  .build();
    }

    @Bean("secondaryTransactionManager")
    public PlatformTransactionManager platformTransactionManager(
            @Qualifier("secondaryEntityManagerFactory") EntityManagerFactory entityManagerFactory
            )
    {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
