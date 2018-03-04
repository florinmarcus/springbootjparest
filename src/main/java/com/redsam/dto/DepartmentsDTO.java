package com.redsam.dto;

import org.springframework.data.rest.core.config.Projection;

import com.redsam.model.Departments;
import com.redsam.model.Employees;
import com.redsam.model.Locations;

@Projection(name = "DepartmentsDTO", types = Departments.class)
public interface DepartmentsDTO {
	
	public Long getDepartmentId();

    public String getDepartmentName();

	/*public Employees getManager() {
		return manager;
	}

	public void setManager(Employees manager) {
		this.manager = manager;
	}

	public Locations getLocation() {
		return location;
	}

	public void setLocation(Locations location) {
		this.location = location;
	}*/
	
	

}
