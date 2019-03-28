package com.inmar.api.dao;

import java.util.List;

import com.inmar.api.model.Department;
import com.inmar.api.model.Location;

public interface DepartmentDao extends BaseDao<Department> {

	List<Department> getDepartments(Location location);

	Department findByDepartment(String departmentName);

}
