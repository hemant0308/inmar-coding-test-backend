package com.inmar.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inmar.api.config.Mappings;
import com.inmar.api.model.Department;
import com.inmar.api.service.DepartmentService;

@RestController
public class DepartmentController {

	private static final Logger log = LogManager.getLogger(CategoryController.class);

	@Autowired
	DepartmentService departmentService;

	@RequestMapping(value = Mappings.GET_DEPARTMENTS, method = RequestMethod.GET)
	public ResponseEntity<Object> getDepartments(@PathVariable int locationId, HttpServletRequest request) {
		log.debug("Request received for :" + Mappings.GET_DEPARTMENTS);

		List<Department> departments = departmentService.getDepartments(locationId);

		log.debug("Sending departments to response");

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("departments", departments);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.GET_ALL_DEPARTMENTS, method = RequestMethod.GET)
	public ResponseEntity<Object> getAllDepartments(HttpServletRequest request) {
		log.debug("Request received for : " + Mappings.GET_ALL_DEPARTMENTS);

		List<Department> departments = departmentService.getAllDepartments();

		log.debug("Sending departments to response");

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("departments", departments);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.SAVE_DEPARTMENT, method = RequestMethod.POST)
	public ResponseEntity<Object> saveDepartment(@PathVariable int locationId, @RequestBody Map<String, Object> payload,
			HttpServletRequest request) {
		log.debug("Request received for :" + Mappings.SAVE_DEPARTMENT);

		int userId = (int) request.getAttribute("userId");

		Department department = departmentService.saveDepartment(locationId, payload, userId);

		log.debug("Deparment Saved with identity : " + department.getId());

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("department", department);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.DELETE_DEPARTMENT, method = RequestMethod.POST)
	public ResponseEntity<Object> saveDepartment(@PathVariable int locationId, @PathVariable int departmentId,
			HttpServletRequest request) {
		log.debug("Request received for : " + Mappings.DELETE_DEPARTMENT);

		Department department = departmentService.deleteDepartment(locationId, departmentId);

		log.debug("Deleted department with id :" + department.getId());

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("department", department);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
