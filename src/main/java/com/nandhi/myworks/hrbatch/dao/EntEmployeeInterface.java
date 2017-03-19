/**
 * 
 */
package com.nandhi.myworks.hrbatch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nandhi.myworks.hrbatch.domain.Employee;

/**
 * @author Nandhi
 *
 */
@Repository
@Transactional(readOnly = true, transactionManager = "transactionManager")
public interface EntEmployeeInterface extends JpaRepository<Employee, Integer> {

	public Employee findById(int empID);

}
