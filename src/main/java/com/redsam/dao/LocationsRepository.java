package com.redsam.dao;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.redsam.model.Locations;

@RepositoryRestResource(collectionResourceRel = "locations", path = "locations")
public interface LocationsRepository extends PagingAndSortingRepository<Locations, Long>{
}
