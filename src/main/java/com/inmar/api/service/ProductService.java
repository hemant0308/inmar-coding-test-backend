package com.inmar.api.service;

import java.util.List;

import com.inmar.api.model.Product;

public interface ProductService {

	List<Product> getProducts(String location, String department, String category, String subCategory);

}
