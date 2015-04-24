package com.grzk.elearning.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
@ComponentScan({"com.grzk.elearning.model","com.grzk.elearnig.repository"})
@EnableJpaRepositories("com.grzk.elearning.repository")
public class JPAConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource source = new DriverManagerDataSource();
		source.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		source.setUrl(env.getProperty("jdbc.url"));
		source.setUsername(env.getProperty("jdbc.username"));
		source.setPassword(env.getProperty("jdbc.password"));
		return source;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter(){
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(env.getProperty("hibernate.show_sql", boolean.class));
		adapter.setGenerateDdl(env.getProperty("database.ddl",boolean.class));
		adapter.setDatabase(org.springframework.orm.jpa.vendor.Database.valueOf(env.getProperty("database.db")));
		return adapter;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(jpaVendorAdapter());
		factory.setPackagesToScan("com.grzk.elearning.model");
		return  factory;
	}
	
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory(){
		HibernateJpaSessionFactoryBean sessionFactory = new HibernateJpaSessionFactoryBean();
		sessionFactory.setEntityManagerFactory(entityManagerFactory().getObject());
		return sessionFactory;
	}
	
	
	@Bean
	public JpaTransactionManager transactionManager(){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setDataSource(dataSource());
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslator(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	
}
