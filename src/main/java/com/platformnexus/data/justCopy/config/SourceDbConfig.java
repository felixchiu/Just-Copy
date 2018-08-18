package com.platformnexus.data.justCopy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.platformnexus.data.justCopy.data.source.repository",
        entityManagerFactoryRef = "sourceEntityManager",
        transactionManagerRef = "sourceTransactionManager")
public class SourceDbConfig {
    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean sourceEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(sourceDataSource());
        em.setPackagesToScan(new String[]{"com.platformnexus.data.justCopy.data.source.entity"});
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("source.jdbc.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("source.jdbc.hibernate.dialect"));
        properties.put("hibernate.dbcp.testWhileIdle", env.getProperty("source.jdbc.hibernate.dbcp.testWhileIdle"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public DataSource sourceDataSource() {

        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("source.jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("source.jdbc.url"));
        dataSource.setUsername(env.getProperty("source.jdbc.username"));
        dataSource.setPassword(env.getProperty("source.jdbc.password"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager sourceTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(sourceEntityManager().getObject());
        return transactionManager;
    }

    @Bean("source_jdbc")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(sourceDataSource());
    }
}
