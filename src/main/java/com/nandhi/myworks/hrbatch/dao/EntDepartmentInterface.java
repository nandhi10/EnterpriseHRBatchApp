/**
 * 
 */
package com.nandhi.myworks.hrbatch.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nandhi.myworks.hrbatch.domain.Department;

/**
 * @author Nandhi
 *
 */
@Repository
public interface EntDepartmentInterface extends CrudRepository<Department, Integer> {

}
