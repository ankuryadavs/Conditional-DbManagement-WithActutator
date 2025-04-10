package org.example.mysql.config;

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
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        basePackages = "org.example.mysql.repo",
        transactionManagerRef = "primaryTransactionManager"
)
public class BookRepoConfig {

    @Autowired
    private MySqlDataSourceProperties mySqlDataSourceProperties;

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }

    @Bean("primaryDataSource")
    public DataSource dataSource()
    {
        return DataSourceBuilder.create()
                .url(mySqlDataSourceProperties.getUrl())
                .password(mySqlDataSourceProperties.getPassword())
                .driverClassName(mySqlDataSourceProperties.getDriverClassName())
                .username(mySqlDataSourceProperties.getUsername())
                .build();
    }

    @Bean("primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(
            @Qualifier("primaryDataSource") DataSource dataSource, EntityManagerFactoryBuilder entityManagerFactoryBuilder
    )
    {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .properties(properties)
                .packages("org.example.mysql.entity")
                .persistenceUnit("primaryDatabase")
                .build();
    }

    @Bean("primaryTransactionManager")
    public PlatformTransactionManager platformTransactionManager(@Qualifier("primaryEntityManagerFactory") EntityManagerFactory entityManagerFactory)
    {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
