package fr.treeptik.jpasample.dto;

import java.io.Serializable;

public class Query19DTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String employeeLName;
	private String employeeFName;
	private String managerLName;
	
	
	
	
	public Query19DTO(String employeeLName, String employeeFName, String managerLName) {
		this.employeeLName = employeeLName;
		this.employeeFName = employeeFName;
		this.managerLName = managerLName;
	}
	public String getEmployeeLName() {
		return employeeLName;
	}
	public void setEmployeeLName(String employeeLName) {
		this.employeeLName = employeeLName;
	}
	public String getEmployeeFName() {
		return employeeFName;
	}
	public void setEmployeeFName(String employeeFName) {
		this.employeeFName = employeeFName;
	}
	public String getManagerLName() {
		return managerLName;
	}
	public void setManagerLName(String managerLName) {
		this.managerLName = managerLName;
	}
	
	
	

}
