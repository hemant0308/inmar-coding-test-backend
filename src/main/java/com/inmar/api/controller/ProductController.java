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
import com.inmar.api.model.Product;
import com.inmar.api.service.ProductService;

@RestController
public class ProductController {
	static Logger log = LogManager.getLogger(LocationController.class);

	@Autowired
	ProductService productService;

	@RequestMapping(value = Mappings.GET_ALL_PRODUCTS, method = RequestMethod.GET)
	public ResponseEntity<Object> getProducts(HttpServletRequest request) {
		log.debug("GET Request received for : " + Mappings.GET_ALL_PRODUCTS);

		String filter = request.getParameter("filter");

		List<Product> products = productService.getProducts(filter);

		log.debug("Sending products to response");

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("products", products);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.SAVE_PRODUCT, method = RequestMethod.POST)
	public ResponseEntity<Object> saveProduct(@RequestBody Map<String, Object> payload, HttpServletRequest request) {
		log.debug("POST Request received for : " + Mappings.SAVE_PRODUCT);
		int userId = (int) request.getAttribute("userId");

		Product product = productService.saveProduct(payload, userId);

		log.debug("Saving product with identity : " + product.getId());

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("product", product);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.DELETE_PRODUCT, method = RequestMethod.POST)
	public ResponseEntity<Object> deleteProduct(@PathVariable int productId, HttpServletRequest request) {
		log.debug("POST Request received for : " + Mappings.DELETE_PRODUCT);

		Product product = productService.deleteProduct(productId);

		log.debug("Deleting product with id : " + product.getId());

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("product", product);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@RequestMapping(value = Mappings.GET_META_DATA, method = RequestMethod.GET)
	public ResponseEntity<Object> getMetaData(HttpServletRequest request) {
		log.debug("GET Request received for : " + Mappings.GET_META_DATA);

		List<Object> locations = productService.getMetaData();

		log.debug("Sending metdata to response");

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("locations", locations);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
