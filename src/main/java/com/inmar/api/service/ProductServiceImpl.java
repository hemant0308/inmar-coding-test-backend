package com.inmar.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inmar.api.dao.CategoryDao;
import com.inmar.api.dao.DepartmentDao;
import com.inmar.api.dao.LocationDao;
import com.inmar.api.dao.ProductDao;
import com.inmar.api.dao.SubCategoryDao;
import com.inmar.api.model.Category;
import com.inmar.api.model.Department;
import com.inmar.api.model.Location;
import com.inmar.api.model.Product;
import com.inmar.api.model.SubCategory;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;
	@Autowired
	LocationDao locationDao;
	@Autowired
	DepartmentDao departmentDao;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	SubCategoryDao subCategoryDao;

	@Override
	public List<Product> getProducts(String locationName, String departmentName, String categoryName,
			String subCategoryName) {
		Location location = locationDao.findByLocation(locationName);
		Department department = departmentDao.findByDepartment(departmentName);
		Category category = categoryDao.findByCategory(categoryName);
		SubCategory subCategory = subCategoryDao.findBySubCategory(subCategoryName);
		List<Product> products = productDao.getProducts(location, department, category, subCategory);
		return products;
	}

}
