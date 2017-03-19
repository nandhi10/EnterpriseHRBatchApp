/**
 * 
 */
package com.nandhi.myworks.hrbatch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author Nandhi
 *
 */
@Component
@Configuration
@EnableBatchProcessing
@PropertySource(value = "batchconfig.properties")
public class JobRepoConfig implements BatchConfigurer {

	@Value("${jobRepo.spring.datasource.url}")
	private String url;
	@Value("${jobRepo.spring.datasource.username}")
	private String userName;
	@Value("${jobRepo.spring.datasource.password}")
	private String password;
	@Value("${jobRepo.spring.datasource.driver-class-name}")
	private String driverClass;

	@Bean
	public DataSource getDataSource() {
		return DataSourceBuilder.create().url(url).username(userName).password(password).driverClassName(driverClass)
				.build();
	}

	@Override
	public JobRepository getJobRepository() throws Exception {
		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
		factory.setDataSource(getDataSource());
		factory.setTransactionManager(getTransactionManager());
		factory.setIsolationLevelForCreate("ISOLATION_DEFAULT");
		factory.afterPropertiesSet();
		return factory.getObject();

	}

	@Override
	public PlatformTransactionManager getTransactionManager() throws Exception {
		return new DataSourceTransactionManager(getDataSource());
	}

	@Override
	public JobLauncher getJobLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(getJobRepository());
		jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
		jobLauncher.afterPropertiesSet();
		return jobLauncher;

	}

	@Override
	public JobExplorer getJobExplorer() throws Exception {
		JobExplorerFactoryBean factory = new JobExplorerFactoryBean();
		factory.setDataSource(getDataSource());
		factory.afterPropertiesSet();
		return factory.getObject();
	}

}
