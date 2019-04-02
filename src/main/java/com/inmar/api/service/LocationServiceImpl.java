package com.inmar.api.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inmar.api.controller.LocationController;
import com.inmar.api.dao.CategoryDao;
import com.inmar.api.dao.DepartmentDao;
import com.inmar.api.dao.LocationDao;
import com.inmar.api.dao.ProductDao;
import com.inmar.api.dao.SubCategoryDao;
import com.inmar.api.model.Location;
import com.inmar.api.model.Product;

@Transactional
@Service
public class LocationServiceImpl implements LocationService {
	static Logger log = LogManager.getLogger(LocationController.class);

	@Autowired
	CategoryDao categoryDao;
	@Autowired
	DepartmentDao departmentDao;
	@Autowired
	LocationDao locationDao;
	@Autowired
	SubCategoryDao subCategoryDao;
	@Autowired
	ProductDao productDao;

	@Override
	public List<Location> getLocations() {
		List<Location> locations = locationDao.findAll();
		return locations;
	}

	@Override
	public Location saveLocation(Map<String, Object> payload, int userId) {
		Location location;
		if (payload.get("id") != null) {
			int locationId = (int) payload.get("id");
			location = locationDao.findById(locationId);
		} else {
			log.debug("Id not found in payload.Creating new Object");

			location = new Location();
		}
		location.setName(payload.get("name").toString());
		locationDao.saveOrUpdate(location, userId);
		return location;
	}

	@Override
	public Location deleteLocation(int locationId) throws Exception {
		Location location = locationDao.findById(locationId);
		List<Product> products = productDao.getProducts(location, null, null, null);
		if (products.size() > 0) {
			throw new Exception("Cannot delete Location.There are products tagged to this location");
		}
		locationDao.delete(location);
		return location;
	}

}
