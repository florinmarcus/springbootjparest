package com.redsam.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.redsam.model.Regions;

@RepositoryRestResource(collectionResourceRel = "regions", path = "regions")

public interface RegionsRepository extends PagingAndSortingRepository<Regions, Long>{

	
	
	

}
