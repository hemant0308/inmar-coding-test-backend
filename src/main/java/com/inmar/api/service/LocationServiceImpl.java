package com.inmar.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inmar.api.dao.CategoryDao;
import com.inmar.api.dao.DepartmentDao;
import com.inmar.api.dao.LocationDao;
import com.inmar.api.dao.SubCategoryDao;
import com.inmar.api.model.Location;

@Transactional
@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	CategoryDao categoryDao;
	@Autowired
	DepartmentDao departmentDao;
	@Autowired
	LocationDao locationDao;
	@Autowired
	SubCategoryDao subCategoryDao;

	@Override
	public List<Location> getLocations() {
		List<Location> locations = locationDao.findAll();
		return locations;
	}

	@Override
	public Location saveLocation(Map<String, Object> payload) {
		Location location;
		if (payload.get("locationId") != null) {
			int locationId = (int) payload.get("locationId");
			location = locationDao.findById(locationId);
		} else {
			location = new Location();
		}
		location.setLocation(payload.get("location").toString());
		locationDao.saveOrUpdate(location);
		return location;
	}

}
