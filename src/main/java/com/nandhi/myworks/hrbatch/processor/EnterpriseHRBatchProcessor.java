/**
 * 
 */
package com.nandhi.myworks.hrbatch.processor;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nandhi.myworks.hrbatch.domain.Employee;
import com.nandhi.myworks.hrbatch.service.EnterpriseHRBatchRepoService;

/**
 * @author Nandhi
 *
 */
@Component
public class EnterpriseHRBatchProcessor implements ItemProcessor<Employee, Employee> {

	private static final Logger logger = Logger.getLogger(EnterpriseHRBatchProcessor.class);

	@Autowired
	EnterpriseHRBatchRepoService empService;

	@Override
	public Employee process(Employee item) throws Exception {
		Employee emp = empService.readEmployee(item.getId());
		if (null != emp) {
			logger.debug("Employee " + item.getId() + " is already available in the system");
		}
		return emp;
	}

}
