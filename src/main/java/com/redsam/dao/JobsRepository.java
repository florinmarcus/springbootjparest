package com.redsam.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.redsam.model.Jobs;

@RepositoryRestResource(collectionResourceRel = "jobs", path = "jobs")
public interface JobsRepository  extends PagingAndSortingRepository<Jobs, Long> {

}
