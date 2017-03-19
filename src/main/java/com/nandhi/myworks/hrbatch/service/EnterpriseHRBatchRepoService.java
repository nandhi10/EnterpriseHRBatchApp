/**
 * 
 */
package com.nandhi.myworks.hrbatch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nandhi.myworks.hrbatch.dao.EntDepartmentInterface;
import com.nandhi.myworks.hrbatch.dao.EntEmployeeInterface;
import com.nandhi.myworks.hrbatch.domain.Department;
import com.nandhi.myworks.hrbatch.domain.Employee;

/**
 * @author Nandhi
 *
 */
@Service
@Transactional("transactionManager")
public class EnterpriseHRBatchRepoService {

	@Autowired
	EntEmployeeInterface employeeDAO;

	@Autowired
	EntDepartmentInterface deptDAO;

	@Transactional(readOnly = true)
	public Employee readEmployee(int empID) {
		return employeeDAO.findById(empID);
	}

	@Transactional(readOnly = true)
	public Department getEmpDepartment(int deptid) {
		return deptDAO.findOne(deptid);
	}

}
