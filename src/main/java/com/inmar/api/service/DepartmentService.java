package com.inmar.api.service;

import java.util.List;
import java.util.Map;

import com.inmar.api.model.Department;

public interface DepartmentService {

	List<Department> getDepartments(int locationId);

	Department saveDepartment(int locationId, Map<String, Object> payload, int userId);

	List<Department> getAllDepartments();

	Department deleteDepartment(int locationId, int departmentId);

}
