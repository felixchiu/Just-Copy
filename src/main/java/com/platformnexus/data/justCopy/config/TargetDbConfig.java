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
        basePackages = "com.platformnexus.data.justCopy.data.target.repository",
        entityManagerFactoryRef = "targetEntityManager",
        transactionManagerRef = "targetTransactionManager")
public class TargetDbConfig {
    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean targetEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(targetDataSource());
        em.setPackagesToScan(new String[]{"com.platformnexus.data.justCopy.data.target.entity"});
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("target.jdbc.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("target.jdbc.hibernate.dialect"));
        properties.put("hibernate.dbcp.testWhileIdle", env.getProperty("target.jdbc.hibernate.dbcp.testWhileIdle"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public DataSource targetDataSource() {

        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("target.jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("target.jdbc.url"));
        dataSource.setUsername(env.getProperty("target.jdbc.username"));
        dataSource.setPassword(env.getProperty("target.jdbc.password"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager targetTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(targetEntityManager().getObject());
        return transactionManager;
    }

    @Bean("target_jdbc")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(targetDataSource());
    }
}
