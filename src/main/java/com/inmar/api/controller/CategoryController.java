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
import com.inmar.api.model.Category;
import com.inmar.api.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@RequestMapping(value = Mappings.GET_CATEGORIES, method = RequestMethod.GET)
	public ResponseEntity<Object> getCategories(@PathVariable int locationId, @PathVariable int departmentId,
			HttpServletRequest request) {

		List<Category> categories = categoryService.getCategories(locationId, departmentId);

		return new ResponseEntity<Object>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.SAVE_CATEGORY, method = RequestMethod.POST)
	public ResponseEntity<Object> getCategories(@PathVariable int locationId, @PathVariable int departmentId,
			@RequestBody Map<String, Object> payload, HttpServletRequest request) {

		Category category = categoryService.saveCategory(locationId, departmentId, payload);

		return new ResponseEntity<Object>(category, HttpStatus.OK);
	}
}
