package com.nandhi.myworks.hrbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
@EnableAutoConfiguration
public class EnterpriseHrBatchAppApplication {
	
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(EnterpriseHrBatchAppApplication.class);

	public static void main(String[] args) {
		LOG.debug("Start of spring batch application");
		try{
		SpringApplication.run(EnterpriseHrBatchAppApplication.class, args);
		}catch(Throwable e){
			LOG.error("Exception Occurred " + e.toString());
			e.printStackTrace();
		}
		
	}
}
