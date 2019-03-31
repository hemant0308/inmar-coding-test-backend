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
import com.inmar.api.dao.SubCategoryDao;
import com.inmar.api.model.Category;
import com.inmar.api.model.SubCategory;

@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService {
	static Logger log = LogManager.getLogger(LocationController.class);

	@Autowired
	CategoryDao categoryDao;
	@Autowired
	SubCategoryDao subCategoryDao;

	@Override
	public List<SubCategory> getSubCategories(int locationId, int departmentId, int categoryId) {
		Category category = categoryDao.findById(categoryId);
		return subCategoryDao.getSubCategories(category);
	}

	@Override
	public SubCategory saveSubCategory(int locationId, int departmentId, int categoryId, Map<String, Object> payload) {
		SubCategory subCategory;
		if (payload.get("id") != null) {
			int subCategoryId = (int) payload.get("id");
			subCategory = subCategoryDao.findById(subCategoryId);
		} else {
			log.debug("Id not found in payload.Creating new Object");

			subCategory = new SubCategory();
		}
		Category category = categoryDao.findById(categoryId);
		subCategory.setCategory(category);

		subCategory.setName(payload.get("name").toString());
		subCategoryDao.saveOrUpdate(subCategory);
		return subCategory;
	}

	@Override
	public List<SubCategory> getAllSubCategories() {
		return subCategoryDao.findAll();
	}

	@Override
	public SubCategory deleteSubCategory(int locationId, int departmentId, int categoryId, int subCategoryId) {
		SubCategory subCategory = subCategoryDao.findById(subCategoryId);
		subCategoryDao.delete(subCategory);
		return subCategory;
	}
}
