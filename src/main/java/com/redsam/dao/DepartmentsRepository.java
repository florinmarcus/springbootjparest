package com.redsam.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.redsam.model.Departments;

@RepositoryRestResource(collectionResourceRel = "departments", path = "departments")
public interface DepartmentsRepository extends PagingAndSortingRepository<Departments, Long> {

	
	
	
	

}
