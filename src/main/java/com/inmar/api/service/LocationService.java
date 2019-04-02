package com.inmar.api.service;

import java.util.List;
import java.util.Map;

import com.inmar.api.model.Location;

public interface LocationService {

	public List<Location> getLocations();

	public Location saveLocation(Map<String, Object> payload, int userId);

	public Location deleteLocation(int locationId) throws Exception;

}
