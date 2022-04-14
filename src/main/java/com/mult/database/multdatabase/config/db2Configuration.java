package com.mult.database.multdatabase.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "db2EntityManagerFactory",
        basePackages = {"com.mult.database.multdatabase.db2.repository"}

)
public class db2Configuration {

    @Bean
    @ConfigurationProperties(prefix = "spring.db2")
    public DataSource db2DataSource() {
        log.info("Criando datasource: spring.dd2");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    anrEntityManagerFactory(EntityManagerFactoryBuilder builder,
                            @Qualifier("db2DataSource") DataSource dataSource
    ) {
        log.info("Obtendo: db1EntityManagerFactory");
        return
                builder
                        .dataSource(dataSource)
                        .packages("com.mult.database.multdatabase.db2.model")
                        .build();
    }
}
