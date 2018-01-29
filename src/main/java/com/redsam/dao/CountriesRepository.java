package com.redsam.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.redsam.model.Countries;


@RepositoryRestResource(collectionResourceRel = "countries", path = "countries")
public interface CountriesRepository  extends PagingAndSortingRepository<Countries, Long>{ 

}
