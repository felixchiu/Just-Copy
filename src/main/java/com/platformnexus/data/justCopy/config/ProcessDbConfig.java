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
        basePackages = "com.platformnexus.data.justCopy.data.process.repository",
        entityManagerFactoryRef = "processEntityManager",
        transactionManagerRef = "processTransactionManager")
public class ProcessDbConfig {
    @Autowired
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean processEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(processDataSource());
        em.setPackagesToScan(new String[]{"com.platformnexus.data.justCopy.data.process.entity"});
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("process.jdbc.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("process.jdbc.hibernate.dialect"));
        properties.put("hibernate.dbcp.testWhileIdle", env.getProperty("process.jdbc.hibernate.dbcp.testWhileIdle"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean
    public DataSource processDataSource() {

        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("process.jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("process.jdbc.url"));
        dataSource.setUsername(env.getProperty("process.jdbc.username"));
        dataSource.setPassword(env.getProperty("process.jdbc.password"));

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager processTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(processEntityManager().getObject());
        return transactionManager;
    }

    @Primary
    @Bean("process_jdbc")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(processDataSource());
    }
}
