package com.nandhi.myworks.hrbatch.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.nandhi.myworks.hrbatch.domain.Employee;

/**
 * 
 */

/**
 * @author Nandhi
 *
 */
@Component
public class EnterpriseHRBatchReader {
	@Bean
	public FlatFileItemReader<Employee> csvFileItemReader() throws Exception {

		FlatFileItemReader<Employee> csvFileReader = new FlatFileItemReader<>();
		csvFileReader.setResource(new ClassPathResource("data/input/Employees.txt"));
		csvFileReader.setLinesToSkip(1);

		DefaultLineMapper<Employee> employeeLineMapper = new DefaultLineMapper<>();
		employeeLineMapper = createEmployeeLineMapper();

		csvFileReader.setLineMapper(lineMapper());

		csvFileReader.afterPropertiesSet();

		return csvFileReader;
	}

	DefaultLineMapper<Employee> createEmployeeLineMapper() {
		DefaultLineMapper<Employee> EmployeeLineMapper = new DefaultLineMapper<Employee>();

		DelimitedLineTokenizer EmployeeLineTokenizer = createEmployeeLineTokenizer();
		EmployeeLineMapper.setLineTokenizer(EmployeeLineTokenizer);

		FieldSetMapper<Employee> EmployeeInformationMapper = createEmployeeInformationMapper();
		EmployeeLineMapper.setFieldSetMapper(EmployeeInformationMapper);

		return EmployeeLineMapper;
	}

	DelimitedLineTokenizer createEmployeeLineTokenizer() {
		DelimitedLineTokenizer EmployeeLineTokenizer = new DelimitedLineTokenizer();
		EmployeeLineTokenizer.setDelimiter(",");
		EmployeeLineTokenizer.setNames(new String[] { "empFirstName", "empLastName", "empDept" });
		return EmployeeLineTokenizer;
	}

	BeanWrapperFieldSetMapper<Employee> createEmployeeInformationMapper() {
		BeanWrapperFieldSetMapper<Employee> EmployeeInformationMapper = new BeanWrapperFieldSetMapper<>();
		EmployeeInformationMapper.setTargetType(Employee.class);
		return EmployeeInformationMapper;
	}

	@Bean
	public LineMapper<Employee> lineMapper() {
		DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<Employee>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "name", "email" });
		// lineTokenizer.setIncludedFields(new int[]{0,2});
		BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<Employee>();
		fieldSetMapper.setTargetType(Employee.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}

}
