package com.inmar.api.service;

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
import com.inmar.api.model.Category;
import com.inmar.api.model.Department;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	static Logger log = LogManager.getLogger(LocationController.class);

	@Autowired
	DepartmentDao departmentDao;
	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<Category> getCategories(int locationId, int departmentId) {
		Department department = departmentDao.findById(departmentId);
		return categoryDao.getCategories(department);
	}

	@Override
	public Category saveCategory(int locationId, int departmentId, Map<String, Object> payload, int userId) {
		Category category;
		if (payload.get("id") != null) {
			log.debug("Id found in payload.Updating data");
			int categoryId = (int) payload.get("id");
			category = categoryDao.findById(categoryId);
		} else {
			log.debug("Id not found in payload.Creating new Object");
			category = new Category();
		}
		Department department = departmentDao.findById(departmentId);
		category.setDepartment(department);
		category.setName(payload.get("name").toString());
		categoryDao.saveOrUpdate(category, userId);
		return category;
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.findAll();
	}

	@Override
	public Category deleteCategory(int locationId, int departmentId, int categoryId) {
		Category category = categoryDao.findById(categoryId);
		categoryDao.delete(category);
		return category;
	}

}
