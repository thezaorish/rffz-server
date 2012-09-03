package com.bunker.rffz.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.bunker.rffz.service"})
@EnableTransactionManagement
@ImportResource("classpath:applicationContext.xml") // temp
@PropertySource(value = { "properties/hibernate.properties", "properties/database.properties"})
public class PersistenceContextConfig {

	 private static final String PROPERTY_NAME_DATABASE_DRIVER = "jdbc.driverClassName";	
	 private static final String PROPERTY_NAME_DATABASE_URL = "jdbc.url";
     private static final String PROPERTY_NAME_DATABASE_USERNAME = "jdbc.username";
     private static final String PROPERTY_NAME_DATABASE_PASSWORD = "jdbc.password";
    
     private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
     private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
     private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
     private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
     private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
     private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	
     @Resource
     private Environment environment;
    
     @Bean
     public DataSource dataSource() {
    	 DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	 dataSource.setDriverClassName(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
    	 dataSource.setUrl(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
    	 dataSource.setUsername(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
    	 dataSource.setPassword(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
    	 return dataSource;
     }
    
     @Bean
     public JpaTransactionManager transactionManager() {
    	 JpaTransactionManager transactionManager = new JpaTransactionManager();
    	 transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
    	 return transactionManager;
     }
    
     @Bean
     public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
    	 LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

    	 entityManagerFactoryBean.setDataSource(dataSource());
    	 entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
    	 entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);

    	 Properties jpaProterties = new Properties();
    	 jpaProterties.put(PROPERTY_NAME_HIBERNATE_DIALECT, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
    	 jpaProterties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
    	 jpaProterties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
    	 jpaProterties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
    	 jpaProterties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));

    	 entityManagerFactoryBean.setJpaProperties(jpaProterties);

    	 return entityManagerFactoryBean;
     }
	
}
