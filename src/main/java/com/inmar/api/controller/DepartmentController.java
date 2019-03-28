package com.inmar.api.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

	@Autowired
	DepartmentService departmentService;

	@RequestMapping(value = Mappings.GET_DEPARTMENTS, method = RequestMethod.GET)
	public ResponseEntity<Object> getDepartments(@PathVariable int locationId, HttpServletRequest request) {

		List<Department> departments = departmentService.getDepartments(locationId);

		return new ResponseEntity<Object>(departments, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.SAVE_DEPARTMENT, method = RequestMethod.POST)
	public ResponseEntity<Object> saveDepartment(@PathVariable int locationId, @RequestBody Map<String, Object> payload,
			HttpServletRequest request) {

		Department department = departmentService.saveDepartment(locationId, payload);

		return new ResponseEntity<Object>(department, HttpStatus.OK);
	}
}
