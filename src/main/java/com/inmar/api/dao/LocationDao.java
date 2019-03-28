package com.inmar.api.dao;

import com.inmar.api.model.Location;

public interface LocationDao extends BaseDao<Location> {

	Location findByLocation(String locationName);

}
