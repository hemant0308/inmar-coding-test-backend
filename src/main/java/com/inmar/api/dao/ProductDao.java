package com.inmar.api.dao;

import java.util.List;

import com.inmar.api.model.Category;
import com.inmar.api.model.Department;
import com.inmar.api.model.Location;
import com.inmar.api.model.Product;
import com.inmar.api.model.SubCategory;

public interface ProductDao extends BaseDao<Product> {

	List<Product> getProducts(Location location, Department department, Category category, SubCategory subCategory);

	List<Object[]> getMetaData();

}
