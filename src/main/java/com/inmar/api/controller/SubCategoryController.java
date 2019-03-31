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
import com.inmar.api.model.SubCategory;
import com.inmar.api.service.SubCategoryService;

@RestController
public class SubCategoryController {
	static Logger log = LogManager.getLogger(LocationController.class);

	@Autowired
	SubCategoryService subCategoryService;

	@RequestMapping(value = Mappings.GET_SUB_CATEGORIES, method = RequestMethod.GET)
	public ResponseEntity<Object> getSubCategories(@PathVariable int locationId, @PathVariable int departmentId,
			@PathVariable int categoryId, HttpServletRequest request) {
		log.debug("GET Request received for : " + Mappings.GET_SUB_CATEGORIES);

		List<SubCategory> subCategories = subCategoryService.getSubCategories(locationId, departmentId, categoryId);

		log.debug("Sending subCategories to response");

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("subCategories", subCategories);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.GET_ALL_SUB_CATEGORIES, method = RequestMethod.GET)
	public ResponseEntity<Object> getAllSubCategories(HttpServletRequest request) {
		log.debug("GET Request received for : " + Mappings.GET_ALL_SUB_CATEGORIES);

		List<SubCategory> subCategories = subCategoryService.getAllSubCategories();

		log.debug("Sending subCategories to response");

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("subCategories", subCategories);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.SAVE_SUB_CATEGORY, method = RequestMethod.POST)
	public ResponseEntity<Object> saveSubCategory(@PathVariable int locationId, @PathVariable int departmentId,
			@PathVariable int categoryId, @RequestBody Map<String, Object> payload, HttpServletRequest request) {
		log.debug("POST Request received for : " + Mappings.SAVE_SUB_CATEGORY);

		SubCategory subCategory = subCategoryService.saveSubCategory(locationId, departmentId, categoryId, payload);

		log.debug("Save subCategory with identity : " + subCategory.getId());

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("subCategory", subCategory);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.DELETE_SUB_CATEGORY, method = RequestMethod.POST)
	public ResponseEntity<Object> deleteSubCategory(@PathVariable int locationId, @PathVariable int departmentId,
			@PathVariable int categoryId, @PathVariable int subCategoryId, HttpServletRequest request) {
		log.debug("POST Request received for : " + Mappings.DELETE_SUB_CATEGORY);

		SubCategory subCategory = subCategoryService.deleteSubCategory(locationId, departmentId, categoryId,
				subCategoryId);

		log.debug("Deleting subCategory with id :" + subCategory.getId());

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("subCategory", subCategory);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
