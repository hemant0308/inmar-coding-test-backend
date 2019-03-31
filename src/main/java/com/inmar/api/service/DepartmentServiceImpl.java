package com.inmar.api.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inmar.api.controller.LocationController;
import com.inmar.api.dao.DepartmentDao;
import com.inmar.api.dao.LocationDao;
import com.inmar.api.model.Department;
import com.inmar.api.model.Location;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	static Logger log = LogManager.getLogger(LocationController.class);

	@Autowired
	LocationDao locationDao;
	@Autowired
	DepartmentDao departmentDao;

	@Override
	public List<Department> getDepartments(int locationId) {
		Location location = locationDao.findById(locationId);
		return departmentDao.getDepartments(location);
	}

	@Override
	public Department saveDepartment(int locationId, Map<String, Object> payload) {
		Department department;
		if (payload.get("id") != null) {
			int departmentId = (int) payload.get("id");
			department = departmentDao.findById(departmentId);
		} else {
			log.debug("Id not found in payload.Creating new Object");

			department = new Department();
		}
		Location location = locationDao.findById(locationId);
		department.setLocation(location);
		department.setName(payload.get("name").toString());
		departmentDao.saveOrUpdate(department);
		return department;
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentDao.findAll();
	}

	@Override
	public Department deleteDepartment(int locationId, int departmentId) {
		Department department = departmentDao.findById(departmentId);
		departmentDao.delete(department);
		return department;
	}

}
