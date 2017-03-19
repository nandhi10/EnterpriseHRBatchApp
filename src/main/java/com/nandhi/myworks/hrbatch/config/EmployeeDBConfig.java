/**
 * 
 */
package com.nandhi.myworks.hrbatch.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Nandhi
 *
 */
@PropertySource(value = "classpath:batchconfig.properties")
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager", basePackages = {
		"com.nandhi.myworks.hrbatch.*" })
@ComponentScan(basePackages={"com.nandhi.myworks.hrbatch.*"})
public class EmployeeDBConfig {

	@Value("${db.spring.datasource.url}")
	private String url;
	@Value("${db.spring.datasource.username}")
	private String userName;
	@Value("${db.spring.datasource.password}")
	private String password;
	@Value("${db.spring.datasource.driver-class-name}")
	private String driverClass;

	@Bean(name = "empDataSource")
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create().url(url).username(userName).password(password).driverClassName(driverClass)
				.build();
	}

	@Bean(name = "transactionManager")
	@Primary
	public JpaTransactionManager entHRBatchTransactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory entHRBatchEntityManagerFactory) {
		JpaTransactionManager jpaTransManager = new JpaTransactionManager();
		jpaTransManager.setEntityManagerFactory(entHRBatchEntityManagerFactory);
		return jpaTransManager;
	}

	@Bean(name = "entityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {

		return builder.dataSource(dataSource()).packages("com.nandhi.myworks.hrbatch.dao")
				.persistenceUnit("empPersistenceUnit").build();

	}

}
