package com.redsam.model.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.redsam.model.Departments;


@Projection(name = "departmentsDTO", types = Departments.class)
public interface DepartmentsDTO {
	
	public Long getDepartmentId();

	public String getDepartmentName() ;
		
	 @Value("#{target.location.city}")
	public String getCity();
	 
	 @Value("#{target.managerName}")
	 public String getManagerName(); 
	
}
