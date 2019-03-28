package com.inmar.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inmar.api.dao.CategoryDao;
import com.inmar.api.dao.SubCategoryDao;
import com.inmar.api.model.Category;
import com.inmar.api.model.SubCategory;

@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService {
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
		if (payload.get("subCategoryId") != null) {
			int subCategoryId = (int) payload.get("subCategoryId");
			subCategory = subCategoryDao.findById(subCategoryId);
		} else {
			subCategory = new SubCategory();
		}
		Category category = categoryDao.findById(categoryId);
		subCategory.setCategory(category);

		subCategory.setSubCategory(payload.get("subCategory").toString());
		subCategoryDao.saveOrUpdate(subCategory);
		return subCategory;
	}
}
