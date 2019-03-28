package com.inmar.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inmar.api.dao.DepartmentDao;
import com.inmar.api.dao.LocationDao;
import com.inmar.api.model.Department;
import com.inmar.api.model.Location;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

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
		if (payload.get("departmentId") != null) {
			int departmentId = (int) payload.get("departmentId");
			department = departmentDao.findById(departmentId);
		} else {
			department = new Department();
		}
		Location location = locationDao.findById(locationId);
		department.setLocation(location);
		department.setDepartment(payload.get("department").toString());
		departmentDao.saveOrUpdate(department);
		return department;
	}

}
