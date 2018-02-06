package com.redsam.dao;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.redsam.model.Departments;
import com.redsam.model.dto.DepartmentsDTO;
import com.redsam.model.dto.DepartmentsDTOImpl;

@RepositoryRestResource(collectionResourceRel = "departments", path = "departments")
public interface DepartmentsRepository extends PagingAndSortingRepository<Departments, Long> {

	
	
	// this approach is working when accessing spring data from Java, but it fails when trying to read it through REST 
	@Query("SELECT new com.redsam.model.dto.DepartmentsDTOImpl( d.departmentId, d.departmentName, l.city) FROM Departments d JOIN  d.location l")
	Page<DepartmentsDTOImpl> listDepartments(Pageable pageable);
	
	/*// this approach is working when accessing spring data from Java, but it fails when trying to read it through REST 
		@Query("SELECT new com.redsam.model.dto.DepartmentsDTOImpl( d.departmentId, d.departmentName, l.city) FROM Departments d JOIN  d.location l")
		Page<DepartmentsDTOImpl> listDepartments2();
	*/
		// this approach is working when accessing spring data from Java, but it fails when trying to read it through REST 
		@Query("SELECT new com.redsam.model.dto.DepartmentsDTOImpl( d.departmentId, d.departmentName, l.city) FROM Departments d JOIN  d.location l")
		Collection listDepartments3();
	

	
	
	
	

}
