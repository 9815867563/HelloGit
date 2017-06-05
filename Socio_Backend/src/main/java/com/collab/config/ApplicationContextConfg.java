package com.collab.config;

import java.util.Properties;

import javax.sql.DataSource;



import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;

import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.collab.domain.User;

@Configuration
@ComponentScan("com.collab.*")
@EnableTransactionManagement
public class ApplicationContextConfg {

	@Bean(name = "datasource")
	public DataSource getMysqlDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:niitdb2");
		ds.setUsername("sys");
		ds.setPassword("rahulrana");
		return ds;

	}

	private Properties getHibernateProperties() {
		Properties p = new Properties();
		p.put("hibernate.show_sql", "true");
		p.put("hibenate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		p.put("hibernate.hbm2ddl.auto", "create");
		return p;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addProperties(getHibernateProperties());
		return sessionBuilder.buildSessionFactory();
	}

	// @SuppressWarnings("deprecation")
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager gettransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}

}
