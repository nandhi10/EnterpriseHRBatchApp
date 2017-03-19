package com.nandhi.myworks.hrbatch.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;

import com.nandhi.myworks.hrbatch.domain.Employee;

/**
 * 
 */

/**
 * @author Nandhi
 *
 */
public class EnterpriseHRBatchWriter extends FlatFileItemWriter<Employee> {

	@Bean
	ItemWriter<Employee> databaseCsvItemWriter() {
		FlatFileItemWriter<Employee> csvFileWriter = new FlatFileItemWriter<>();

		String exportFilePath = "/data/output/Employees.csv";
		csvFileWriter.setResource(new FileSystemResource(exportFilePath));

		LineAggregator<Employee> lineAggregator = createEmployeeLineAggregator();
		csvFileWriter.setLineAggregator(lineAggregator);

		return csvFileWriter;
	}

	private LineAggregator<Employee> createEmployeeLineAggregator() {
		DelimitedLineAggregator<Employee> lineAggregator = new DelimitedLineAggregator<>();
		lineAggregator.setDelimiter(";");

		FieldExtractor<Employee> fieldExtractor = createEmployeeFieldExtractor();
		lineAggregator.setFieldExtractor(fieldExtractor);

		return lineAggregator;
	}

	private FieldExtractor<Employee> createEmployeeFieldExtractor() {
		BeanWrapperFieldExtractor<Employee> extractor = new BeanWrapperFieldExtractor<>();
		extractor.setNames(new String[] { "name", "emailAddress", "purchasedPackage" });
		return extractor;
	}

}
