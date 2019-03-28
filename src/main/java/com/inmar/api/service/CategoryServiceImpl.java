package com.inmar.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inmar.api.dao.CategoryDao;
import com.inmar.api.dao.DepartmentDao;
import com.inmar.api.model.Category;
import com.inmar.api.model.Department;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

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
	public Category saveCategory(int locationId, int departmentId, Map<String, Object> payload) {
		Category category;
		if (payload.get("categoryId") != null) {
			int categoryId = (int) payload.get("categoryId");
			category = categoryDao.findById(categoryId);
		} else {
			category = new Category();
		}
		Department department = departmentDao.findById(departmentId);
		category.setDepartment(department);
		category.setCategory(payload.get("category").toString());
		categoryDao.saveOrUpdate(category);
		return category;
	}

}
