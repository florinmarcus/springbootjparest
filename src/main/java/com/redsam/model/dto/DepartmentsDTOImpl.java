package com.redsam.model.dto;


public class DepartmentsDTOImpl {

	
	Long departmentId;
	String departmentName;
	String city;
	
	
	
	

	public DepartmentsDTOImpl(Long departmentId, String departmentName, String city) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
