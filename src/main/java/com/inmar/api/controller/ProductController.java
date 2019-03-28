package com.inmar.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inmar.api.config.Mappings;
import com.inmar.api.model.Product;
import com.inmar.api.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping(value = Mappings.GET_PRODUCTS, method = RequestMethod.GET)
	public ResponseEntity<Object> getProducts(HttpServletRequest request) {

		String location = request.getParameter("location");
		String department = request.getParameter("department");
		String category = request.getParameter("category");
		String subCategory = request.getParameter("subCategory");
		List<Product> departments = productService.getProducts(location, department, category, subCategory);

		return new ResponseEntity<Object>(departments, HttpStatus.OK);
	}
}
