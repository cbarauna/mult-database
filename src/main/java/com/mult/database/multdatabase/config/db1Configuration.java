package com.mult.database.multdatabase.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "db1EntityManagerFactory",
        transactionManagerRef = "db1TransactionManager",
        basePackages = {"com.mult.database.multdatabase.db1.repository"}
)
public class db1Configuration {

    @Autowired
    Environment env;

    @Primary
    @Bean(name = "db1DataSource")
    @ConfigurationProperties(prefix = "spring.db1")
    public DataSource db1DataSource() {
        return DataSourceBuilder.create()
                .driverClassName(env.getProperty("spring.datasource.db1.driver-class-name"))
                .username(env.getProperty("spring.datasource.db1.username"))
                .password(env.getProperty("spring.datasource.db1.password"))
                .url(env.getProperty("spring.datasource.db1.url"))
                .build();
    }

    @Primary
    @Bean(name = "db1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("db1DataSource") DataSource db1EntityManagerFactory) {
        return builder
                .dataSource(db1EntityManagerFactory)
                .packages("br.com.tassioauad.myapp.model.sqlserver.entity")
                .persistenceUnit("sqlServerPU")
                .build();
    }

    @Primary
    @Bean(name = "db1TransactionManager")
    public PlatformTransactionManager db1TransactionManager(@Qualifier("db1EntityManagerFactory")
                                                                    EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
