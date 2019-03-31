package com.inmar.api.service;

import java.util.List;
import java.util.Map;

import com.inmar.api.model.Product;

public interface ProductService {

	List<Product> getProducts(String location);

	Product saveProduct(Map<String, Object> payload);

	Product deleteProduct(int productId);

	List<Object> getMetaData();

}
