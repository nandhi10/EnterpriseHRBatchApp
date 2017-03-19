package com.nandhi.myworks.hrbatch.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the department database table.
 * 
 */
@Entity
//@NamedQuery(name="Department.findAll", query="SELECT d FROM Department d")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	public Department() {
	}
	@Id
	@Column(name = "deptid")
	private int deptid;
	@Column(name = "deptname")
	private String deptname;
	@Column(name = "deptinfo")
	private String deptinfo;

	public int getDeptID() {
		return deptid;
	}
	public void setDeptID(int deptID) {
		this.deptid = deptID;
	}
	public String getDeptName() {
		return deptname;
	}
	public void setDeptName(String deptName) {
		this.deptname = deptName;
	}
	public String getDeptInfo() {
		return deptinfo;
	}
	public void setDeptInfo(String deptInfo) {
		this.deptinfo = deptInfo;
	}

}