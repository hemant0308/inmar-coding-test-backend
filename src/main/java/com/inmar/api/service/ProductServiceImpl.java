package com.inmar.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inmar.api.controller.LocationController;
import com.inmar.api.dao.CategoryDao;
import com.inmar.api.dao.DepartmentDao;
import com.inmar.api.dao.LocationDao;
import com.inmar.api.dao.ProductDao;
import com.inmar.api.dao.SubCategoryDao;
import com.inmar.api.helper.Utilities;
import com.inmar.api.model.Category;
import com.inmar.api.model.Department;
import com.inmar.api.model.Location;
import com.inmar.api.model.Product;
import com.inmar.api.model.SubCategory;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	static Logger log = LogManager.getLogger(LocationController.class);

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
	public List<Product> getProducts(String filterString) {
		Location location = null;
		Department department = null;
		Category category = null;
		SubCategory subCategory = null;
		Map<String, Object> filter;
		List<Product> products = null;
		try {
			filter = Utilities.getMapFromString(filterString);
			if (filter.get("locationId") != null) {
				int locationId = (int) filter.get("locationId");
				location = locationDao.findById(locationId);
			}
			if (filter.get("departmentId") != null) {
				int departmentId = (int) filter.get("departmentId");
				department = departmentDao.findById(departmentId);
			}

			if (filter.get("categoryId") != null) {
				int categoryId = (int) filter.get("categoryId");
				category = categoryDao.findById(categoryId);
			}
			if (filter.get("subCategoryId") != null) {
				int subCategoryId = (int) filter.get("subCategoryId");
				subCategory = subCategoryDao.findById(subCategoryId);
			}

			products = productDao.getProducts(location, department, category, subCategory);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public Product saveProduct(Map<String, Object> payload, int userId) {

		Product product;
		if (payload.get("id") != null) {
			product = productDao.findById((int) payload.get("id"));
		} else {
			product = new Product();
		}
		int categoryId = (int) payload.get("categoryId");
		int departmentId = (int) payload.get("departmentId");
		int locationId = (int) payload.get("locationId");
		int subCategoryId = (int) payload.get("subCategoryId");
		String name = (String) payload.get("name");
		String sku = (String) payload.get("sku");
		product.setSku(sku);
		product.setName(name);
		product.setLocation(locationDao.findById(locationId));
		product.setDepartment(departmentDao.findById(departmentId));
		product.setCategory(categoryDao.findById(categoryId));
		product.setSubCategory(subCategoryDao.findById(subCategoryId));
		productDao.saveOrUpdate(product, userId);
		return product;
	}

	@Override
	public Product deleteProduct(int productId) {
		Product product = productDao.findById(productId);
		productDao.delete(product);
		return product;
	}

	@Override
	public List<Object> getMetaData() {
		List<Object> locationsList = new ArrayList<Object>();
		for (Location location : locationDao.findAll()) {
			List<Object> departmentList = new ArrayList<Object>();
			Map<String, Object> locationMap = new HashMap<String, Object>();
			locationMap.put("name", location.getName());
			locationMap.put("id", location.getId());
			locationMap.put("departments", departmentList);
			locationsList.add(locationMap);
			for (Department department : location.getDepartments()) {
				Map<String, Object> departmentMap = new HashMap<String, Object>();
				List<Object> categoryList = new ArrayList<Object>();
				departmentMap.put("id", department.getId());
				departmentMap.put("name", department.getName());
				departmentMap.put("categories", categoryList);
				departmentList.add(departmentMap);
				for (Category category : department.getCategories()) {
					Map<String, Object> categoryMap = new HashMap<String, Object>();
					List<Object> subCategoryList = new ArrayList<Object>();
					categoryMap.put("id", category.getId());
					categoryMap.put("name", category.getName());
					categoryMap.put("subCategories", subCategoryList);
					categoryList.add(categoryMap);
					for (SubCategory subCategory : category.getSubCategories()) {
						Map<String, Object> subCategoryMap = new HashMap<String, Object>();
						subCategoryMap.put("name", subCategory.getName());
						subCategoryMap.put("id", subCategory.getId());
						subCategoryList.add(subCategoryMap);
					}
				}
			}

		}
		return locationsList;

	}

}
