package com.redsam.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.redsam.model.Employees;

@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
public interface EmployeesRepository extends PagingAndSortingRepository<Employees, Long> {
	

}
