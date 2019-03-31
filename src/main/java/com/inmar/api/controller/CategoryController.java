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
import com.inmar.api.model.Category;
import com.inmar.api.service.CategoryService;

@RestController
public class CategoryController {

	private static final Logger log = LogManager.getLogger(CategoryController.class);

	@Autowired
	CategoryService categoryService;

	@RequestMapping(value = Mappings.GET_CATEGORIES, method = RequestMethod.GET)
	public ResponseEntity<Object> getCategories(@PathVariable int locationId, @PathVariable int departmentId,
			HttpServletRequest request) {
		log.debug("Request received for : " + Mappings.GET_CATEGORIES);

		List<Category> categories = categoryService.getCategories(locationId, departmentId);

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("categories", categories);

		log.debug("Sending categories to response");

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.GET_ALL_CATEGORIES, method = RequestMethod.GET)
	public ResponseEntity<Object> getAllCategories(HttpServletRequest request) {

		log.debug("Request received for : " + Mappings.GET_ALL_CATEGORIES);

		List<Category> categories = categoryService.getAllCategories();

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("categories", categories);

		log.debug("Sending categories to response");

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.SAVE_CATEGORY, method = RequestMethod.POST)
	public ResponseEntity<Object> getCategories(@PathVariable int locationId, @PathVariable int departmentId,
			@RequestBody Map<String, Object> payload, HttpServletRequest request) {
		log.debug("Request received for : " + Mappings.SAVE_CATEGORY);

		Category category = categoryService.saveCategory(locationId, departmentId, payload);

		log.debug("Category saved.Identity is : " + category.getId());

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("category", category);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.DELETE_CATEGORY, method = RequestMethod.POST)
	public ResponseEntity<Object> deleteCategory(@PathVariable int locationId, @PathVariable int departmentId,
			@PathVariable int categoryId, HttpServletRequest request) {

		log.debug("Request received for : " + Mappings.DELETE_CATEGORY);

		Category category = categoryService.deleteCategory(locationId, departmentId, categoryId);

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("category", category);

		log.debug("Deleted Category with id :" + category.getId());

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
