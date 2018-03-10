package com.redsam.model.dto;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.redsam.model.Employees;


@Projection(name = "employeesDTO", types = Employees.class)
public interface EmployeesDTO {
	
	
	public Long getEmployeeId();

	public String getFirstName();

	public String getLastName() ;
	
	public Date getHireDate();
	
	public Long getSalary();

	@Value("#{target.job.jobTitle}")
	public String getJobTitle();

	


}
