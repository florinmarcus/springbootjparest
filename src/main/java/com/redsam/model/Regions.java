package com.redsam.model;

import javax.persistence.*;


@Entity
public class Regions {
	
	@Id
    Long regionId;
	
    String regionName;
    public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	

}
