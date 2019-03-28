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
import com.inmar.api.model.SubCategory;
import com.inmar.api.service.SubCategoryService;

@RestController
public class SubCategoryController {

	@Autowired
	SubCategoryService subCategoryService;

	@RequestMapping(value = Mappings.GET_SUB_CATEGORIES, method = RequestMethod.GET)
	public ResponseEntity<Object> getSubCategories(@PathVariable int locationId, @PathVariable int departmentId,
			@PathVariable int categoryId, HttpServletRequest request) {

		List<SubCategory> subCategories = subCategoryService.getSubCategories(locationId, departmentId, categoryId);

		return new ResponseEntity<Object>(subCategories, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.SAVE_SUB_CATEGORY, method = RequestMethod.POST)
	public ResponseEntity<Object> saveSubCategory(@PathVariable int locationId, @PathVariable int departmentId,
			@PathVariable int categoryId, @RequestBody Map<String, Object> payload, HttpServletRequest request) {

		SubCategory subCategory = subCategoryService.saveSubCategory(locationId, departmentId, categoryId, payload);

		return new ResponseEntity<Object>(subCategory, HttpStatus.OK);
	}
}
