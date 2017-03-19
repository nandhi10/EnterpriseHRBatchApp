/**
 * 
 */
package com.nandhi.myworks.hrbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.nandhi.myworks.hrbatch.domain.Employee;
import com.nandhi.myworks.hrbatch.processor.EnterpriseHRBatchProcessor;
import com.nandhi.myworks.hrbatch.reader.EnterpriseHRBatchReader;
import com.nandhi.myworks.hrbatch.writer.EnterpriseHRBatchWriter;

/**
 * @author Nandhi
 *
 */
@Configuration
@EnableBatchProcessing
@ComponentScan(basePackages = "com.nandhi.myworks.hrbatch")
@Import(value = { EmployeeDBConfig.class, JobRepoConfig.class })
public class EnterpriseHRBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilder;
	@Autowired
	private StepBuilderFactory stepBuilder;
	
	@Autowired
	private EnterpriseHRBatchReader reader;
	
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(EnterpriseHRBatchConfig.class);

	@Bean
	public Job employeeJob(JobBuilderFactory jobBuilderFactory) {
		try {
			LOG.debug("EnterpriseHRBatchConfig : Start of Spring Batch Job ");
			return jobBuilder.get("employeeJob").incrementer(new RunIdIncrementer()).start(employeeStep()).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Bean
	public Step employeeStep() throws Exception {
		LOG.debug("Starting spring batch step : employeeStep");
		try {
			return stepBuilder.get("employeeStep").<Employee, Employee>chunk(10).reader(empReader()).processor(empProcess())
					.writer(employeeWriter()).build();
		} catch (Exception e) {
			LOG.debug("Exception in processing step " + e.toString());
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	@Bean
	public FlatFileItemReader<Employee> empReader() throws Exception {
		return reader.csvFileItemReader();
	}

	@Bean
	public ItemWriter<Employee> employeeWriter() {
		return new EnterpriseHRBatchWriter();
	}

	@Bean
	public ItemProcessor<Employee, Employee> empProcess() {
		return new EnterpriseHRBatchProcessor();
	}

}
